package com.northernarc.customerproductspringdatajpa.service;

import com.northernarc.customerproductspringdatajpa.dto.CustomerSummaryDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderItemRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderItemSummaryDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderRequestDTO;
import com.northernarc.customerproductspringdatajpa.dto.OrderResponseDTO;
import com.northernarc.customerproductspringdatajpa.dto.ProductSummaryDTO;
import com.northernarc.customerproductspringdatajpa.exceptions.CustomerNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.OrderNotFound;
import com.northernarc.customerproductspringdatajpa.exceptions.ProductNotFound;
import com.northernarc.customerproductspringdatajpa.model.Customer;
import com.northernarc.customerproductspringdatajpa.model.Order;
import com.northernarc.customerproductspringdatajpa.model.OrderItem;
import com.northernarc.customerproductspringdatajpa.security.CustomerPrincipal;
import com.northernarc.customerproductspringdatajpa.repository.CustomerRepository;
import com.northernarc.customerproductspringdatajpa.repository.OrderRepository;
import com.northernarc.customerproductspringdatajpa.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public OrderResponseDTO addOrder(OrderRequestDTO order) {
        Long authenticatedCustomerId = getAuthenticatedCustomerId();
        if (!authenticatedCustomerId.equals(order.getCustomerId())) {
            throw new AccessDeniedException("You can create orders only for your account");
        }

        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new CustomerNotFound("no customer found..."));

        Order newOrder = new Order();
        newOrder.setOrderDate(order.getOrderDate());
        newOrder.setCustomer(customer);
        newOrder.setOrderItemList(buildOrderItems(order.getOrderItems(), newOrder));
        return mapToResponse(orderRepository.save(newOrder));
    }

    private OrderResponseDTO mapToResponse(Order order){
        return new OrderResponseDTO(order.getOrder_id(), order.getOrderDate(), mapToCustomerSummary(order.getCustomer()), order.getOrderItemList().stream().map((orderItem)-> new OrderItemSummaryDTO(orderItem.getId(), orderItem.getQuantity(), new ProductSummaryDTO(orderItem.getProduct().getProduct_id(), orderItem.getProduct().getName(), orderItem.getProduct().getBrand(), orderItem.getProduct().getCost().doubleValue()))).toList());
    }

    private CustomerSummaryDTO mapToCustomerSummary(Customer customer){
        return new CustomerSummaryDTO(customer.getId(), customer.getFName(), customer.getLName());
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFound("no order found..."));
        ensureOrderAccess(order);
        return mapToResponse(order);
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFound("no order found..."));
        ensureOrderAccess(order);
        orderRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteAllOrders() {
        // Only admins can delete all orders
        orderRepository.deleteAll();
    }


    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateById(Long id, OrderRequestDTO order) {
        Order order1 = orderRepository.findById(id).orElseThrow(()->new OrderNotFound("no order found..."));
        ensureOrderAccess(order1);

        order1.setOrderDate(order.getOrderDate());
        order1.getOrderItemList().clear();
        order1.getOrderItemList().addAll(buildOrderItems(order.getOrderItems(), order1));
        orderRepository.save(order1);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponseDTO> findAllOrders() {
        // Only admins should see all orders
        // For users, create a separate method like findMyOrders(Long customerId)
        return orderRepository.findAll().stream().map((order)->mapToResponse(order)).toList();
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponseDTO> findMyOrders(Long customerId) {
        Long authenticatedCustomerId = getAuthenticatedCustomerId();
        if (!authenticatedCustomerId.equals(customerId)) {
            throw new AccessDeniedException("You can access only your own orders");
        }
        return orderRepository.findByCustomerId(customerId).stream().map((order)->mapToResponse(order)).toList();
    }

    private List<OrderItem> buildOrderItems(List<OrderItemRequestDTO> requestItems, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequestDTO requestItem : requestItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productRepository.findById(requestItem.getProductId())
                    .orElseThrow(() -> new ProductNotFound("no product found...")));
            orderItem.setQuantity(requestItem.getQuantity());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private Long getAuthenticatedCustomerId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomerPrincipal customerPrincipal) {
            return customerPrincipal.getId();
        }
        throw new AccessDeniedException("Invalid authenticated principal");
    }

    private void ensureOrderAccess(Order order) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return;
        }
        Long authenticatedCustomerId = getAuthenticatedCustomerId();
        if (!order.getCustomer().getId().equals(authenticatedCustomerId)) {
            throw new AccessDeniedException("You can access only your own orders");
        }
    }
}

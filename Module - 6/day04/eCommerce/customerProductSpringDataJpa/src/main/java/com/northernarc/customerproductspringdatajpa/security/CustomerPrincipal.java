package com.northernarc.customerproductspringdatajpa.security;

import com.northernarc.customerproductspringdatajpa.model.Customer;
import com.northernarc.customerproductspringdatajpa.model.CustomerRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomerPrincipal implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomerPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomerPrincipal fromCustomer(Customer customer) {
        CustomerRole role = customer.getRole() == null ? CustomerRole.USER : customer.getRole();
        return new CustomerPrincipal(
                customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role.name()))
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

import psycopg2


def get_connection():
	return psycopg2.connect(
		host="localhost",
		database="postgres",
		user="postgres",
		password="postgres",
		port=5432,
	)


def init_table():
	with get_connection() as conn:
		with conn.cursor() as cur:
			cur.execute(
				"""
				CREATE TABLE IF NOT EXISTS products (
					id SERIAL PRIMARY KEY,
					name VARCHAR(100) NOT NULL,
					price NUMERIC(10, 2) NOT NULL,
					quantity INTEGER NOT NULL
				)
				"""
			)


def add_product(name, price, quantity):
	with get_connection() as conn:
		with conn.cursor() as cur:
			cur.execute(
				"INSERT INTO products (name, price, quantity) VALUES (%s, %s, %s)",
				(name, price, quantity),
			)


def list_products():
	with get_connection() as conn:
		with conn.cursor() as cur:
			cur.execute("SELECT id, name, price, quantity FROM products ORDER BY id")
			rows = cur.fetchall()
			if not rows:
				print("No products found.")
				return
			for row in rows:
				print(f"ID: {row[0]}, Name: {row[1]}, Price: {row[2]}, Qty: {row[3]}")


def update_product(product_id, name, price, quantity):
	with get_connection() as conn:
		with conn.cursor() as cur:
			cur.execute(
				"""
				UPDATE products
				SET name = %s, price = %s, quantity = %s
				WHERE id = %s
				""",
				(name, price, quantity, product_id),
			)
			if cur.rowcount == 0:
				print("Product not found.")


def delete_product(product_id):
	with get_connection() as conn:
		with conn.cursor() as cur:
			cur.execute("DELETE FROM products WHERE id = %s", (product_id,))
			if cur.rowcount == 0:
				print("Product not found.")


def main():
	init_table()
	while True:
		print("\n1. Add Product")
		print("2. List Products")
		print("3. Update Product")
		print("4. Delete Product")
		print("5. Exit")
		choice = input("Enter choice: ").strip()

		if choice == "1":
			name = input("Name: ").strip()
			price = float(input("Price: ").strip())
			quantity = int(input("Quantity: ").strip())
			add_product(name, price, quantity)
			print("Product added.")
		elif choice == "2":
			list_products()
		elif choice == "3":
			product_id = int(input("Product ID to update: ").strip())
			name = input("New name: ").strip()
			price = float(input("New price: ").strip())
			quantity = int(input("New quantity: ").strip())
			update_product(product_id, name, price, quantity)
			print("Update completed.")
		elif choice == "4":
			product_id = int(input("Product ID to delete: ").strip())
			delete_product(product_id)
			print("Delete completed.")
		elif choice == "5":
			print("Exiting.")
			break
		else:
			print("Invalid choice.")


if __name__ == "__main__":
	main()

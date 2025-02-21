-- Insertar datos en la tabla category
INSERT INTO category (id, name, description) VALUES
    (NEXTVAL('category_seq'), 'Electronics', 'Devices and gadgets'),
    (NEXTVAL('category_seq'), 'Clothing', 'Apparel and accessories'),
    (NEXTVAL('category_seq'), 'Books', 'Printed and digital books'),
    (NEXTVAL('category_seq'), 'Furniture', 'Home and office furniture'),
    (NEXTVAL('category_seq'), 'Toys', 'Toys and games for kids');

-- Insertar productos en la categoría 'Electronics'
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
    (NEXTVAL('product_seq'), 'Laptop', 'High-performance laptop', 10, 1200.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (NEXTVAL('product_seq'), 'Smartphone', 'Latest generation smartphone', 20, 899.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (NEXTVAL('product_seq'), 'Wireless Headphones', 'Noise-canceling over-ear headphones', 15, 199.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (NEXTVAL('product_seq'), 'Smartwatch', 'Fitness and health tracking smartwatch', 25, 149.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (NEXTVAL('product_seq'), 'Tablet', '10-inch high-resolution tablet', 12, 329.99, (SELECT id FROM category WHERE name = 'Electronics'));

-- Insertar productos en la categoría 'Clothing'
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
    (NEXTVAL('product_seq'), 'T-Shirt', 'Cotton T-shirt in various sizes', 50, 19.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (NEXTVAL('product_seq'), 'Jeans', 'Slim fit blue jeans', 30, 49.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (NEXTVAL('product_seq'), 'Sneakers', 'Comfortable and stylish sneakers', 40, 79.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (NEXTVAL('product_seq'), 'Jacket', 'Waterproof winter jacket', 20, 129.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (NEXTVAL('product_seq'), 'Socks', 'Pack of 5 cotton socks', 60, 9.99, (SELECT id FROM category WHERE name = 'Clothing'));

-- Insertar productos en la categoría 'Books'
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
    (NEXTVAL('product_seq'), 'Science Fiction Book', 'Bestselling sci-fi novel', 100, 14.99, (SELECT id FROM category WHERE name = 'Books')),
    (NEXTVAL('product_seq'), 'Mystery Novel', 'Gripping mystery story', 80, 12.99, (SELECT id FROM category WHERE name = 'Books')),
    (NEXTVAL('product_seq'), 'Self-Help Guide', 'Improve your life with expert advice', 90, 17.99, (SELECT id FROM category WHERE name = 'Books')),
    (NEXTVAL('product_seq'), 'Cookbook', 'Delicious recipes from around the world', 50, 24.99, (SELECT id FROM category WHERE name = 'Books')),
    (NEXTVAL('product_seq'), 'History Book', 'Deep dive into ancient civilizations', 70, 19.99, (SELECT id FROM category WHERE name = 'Books'));

-- Insertar productos en la categoría 'Furniture'
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
    (NEXTVAL('product_seq'), 'Office Chair', 'Ergonomic office chair', 15, 249.99, (SELECT id FROM category WHERE name = 'Furniture')),
    (NEXTVAL('product_seq'), 'Dining Table', 'Solid wood dining table', 10, 499.99, (SELECT id FROM category WHERE name = 'Furniture')),
    (NEXTVAL('product_seq'), 'Bookshelf', 'Modern bookshelf with 5 shelves', 20, 199.99, (SELECT id FROM category WHERE name = 'Furniture')),
    (NEXTVAL('product_seq'), 'Sofa', 'Comfortable 3-seater sofa', 8, 699.99, (SELECT id FROM category WHERE name = 'Furniture')),
    (NEXTVAL('product_seq'), 'Bed Frame', 'Queen size wooden bed frame', 12, 399.99, (SELECT id FROM category WHERE name = 'Furniture'));

-- Insertar productos en la categoría 'Toys'
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
    (NEXTVAL('product_seq'), 'Action Figure', 'Popular superhero action figure', 40, 29.99, (SELECT id FROM category WHERE name = 'Toys')),
    (NEXTVAL('product_seq'), 'Board Game', 'Fun strategy board game for families', 25, 34.99, (SELECT id FROM category WHERE name = 'Toys')),
    (NEXTVAL('product_seq'), 'Dollhouse', 'Wooden dollhouse with accessories', 15, 79.99, (SELECT id FROM category WHERE name = 'Toys')),
    (NEXTVAL('product_seq'), 'RC Car', 'Remote-controlled car with rechargeable battery', 20, 59.99, (SELECT id FROM category WHERE name = 'Toys')),
    (NEXTVAL('product_seq'), 'Puzzle', '1000-piece jigsaw puzzle', 30, 14.99, (SELECT id FROM category WHERE name = 'Toys'));

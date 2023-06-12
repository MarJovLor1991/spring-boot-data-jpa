/* Populate tables */
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Doe', 'john.doe@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2023-05-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2023-05-02', '');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259.99, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123.49, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 149.99, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 379.90, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69.99, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 29.99, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 2, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Comoda', 'Alguna nota importante!', 2, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

/* Creamos algunos usuarios */
INSERT INTO usuarios (nombre,password,enabled) VALUES ('ADMIN', '$2a$10$sB7vBDpaFRB75EpYi87yYOxRJLtr8qbgjMpc.vrFByse7uIG4UlXq', true);
INSERT INTO usuarios (nombre,password,enabled) VALUES ('USER', '$2a$10$XiYc4rweI9HTBfd1pfiL1eRwWeRT64cxsDSqzaoMQHFLUuPyC5ZQC', false);

/* Añadimos roles */
INSERT INTO authorities (usuario_id,autority) VALUES (1,'ROLE_ADMIN');
INSERT INTO authorities (usuario_id,autority) VALUES (2,'ROLE_USER');



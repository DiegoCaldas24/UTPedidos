use utppedidos

CREATE TABLE categorias(
	id_categoria INT NOT NULL AUTO_INCREMENT, /*id autoincrementable*/
    nombre VARCHAR(30) NOT NULL, /*Nombre de la categoria BEBIDA SNACK MENU ECONOMICA*/
    PRIMARY KEY(id_categoria)
);
CREATE TABLE productos(
	id_producto INT NOT NULL AUTO_INCREMENT, /*id autoincrementable*/
    id_categoria INT NOT NULL, /*foreign key para relacionar categoria con el producto*/
    nombre VARCHAR(30) NOT NULL, /*nombre del producto*/
    precio DECIMAL(6,2) NOT NULL, /*precio del producto de 4 digitos antes de la coma y 2 digitos despues de la coma*/
    descripcion VARCHAR(255) NOT NULL, /*descripcion del producto*/
    stock SMALLINT NOT NULL, /*cantidad disponible del producto*/
    estado BOOLEAN NOT NULL DEFAULT 1, /*El estado del producto 0 = no disponible, 1 = disponible por default disponible*/
    PRIMARY KEY(id_producto),
    FOREIGN KEY(id_categoria) REFERENCES categorias(id_categoria)
);
CREATE TABLE menu_dia(
	id_menu_dia INT NOT NULL AUTO_INCREMENT, /**/
    id_producto INT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    PRIMARY KEY (id_menu_dia),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE carrito(
	id_carrito INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad SMALLINT NOT NULL,
    total DECIMAL(6,3) NOT NULL,
    PRIMARY KEY (id_carrito),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE ventas(
	id_venta INT NOT NULL AUTO_INCREMENT,
	id_usuario INT NOT NULL,
	total DECIMAL(6,2) NOT NULL,
	tipo_pago ENUM('yape', 'plin') NOT NULL,
	fecha TIMESTAMP NOT NULL,
	PRIMARY KEY (id_venta),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);
CREATE TABLE detalle_venta(
	id_venta INT NOT NULL,
	id_producto INT NOT NULL,
	cantidad SMALLINT NOT NULL,
	precio DECIMAL(6,2) NOT NULL,
	subtotal DECIMAL(6,2) NOT NULL,
	FOREIGN KEY (id_venta) REFERENCES ventas(id_venta),
	FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);
CREATE TABLE pedidos(
	id_pedido INT NOT NULL AUTO_INCREMENT,
	id_usuario INT NOT NULL,
	fecha_pedido TIMESTAMP NOT NULL,
	fecha_entrega TIMESTAMP NOT NULL,
	estado BOOLEAN NOT NULL,
	PRIMARY KEY (id_pedido),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE detalle_pedido(
	id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
	cantidad SMALLINT NOT NULL,
	precio DECIMAL(6,2) NOT NULL,
	subtotal DECIMAL(6,2) NOT NULL,
	FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
	FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE notificaciones(
	id_notificacion INT NOT NULL AUTO_INCREMENT,
	id_usuario INT NOT NULL,
	mensaje VARCHAR(50) NOT NULL,
	estado BOOLEAN NOT NULL,
    PRIMARY KEY (id_notificacion),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);


select * from usuarios;
INSERT INTO usuarios(nombre,correo,password,rol,fecha_ingreso) VALUES("Juan Diego","U22230019@utp.edu.pe","Caldas2407+","USUARIO", now());
INSERT INTO usuarios(nombre,correo,password,rol,fecha_ingreso) VALUES("Juan Diego","U22230020@utp.edu.pe","hash1245236","USUARIO", now());

INSERT INTO categorias(nombre) VALUES
("SNACK"),
("BEBIDAS"),
("POSTRES"),
("MENU ECONOMICO"),
("MENU EJECUTIVO");

select * from categorias;

INSERT INTO productos(id_categoria,nombre,precio,descripcion,stock) VALUES(2,"CocaCola",3.00,"Cocacola de 500ml sin azucar",300);
INSERT INTO productos(id_categoria,nombre,precio,descripcion,stock) VALUES(4,"ArrozConPollo",8.00,"Arroz con pollo y jugo",400);
SELECT* FROM categorias;


SELECT p.nombre,c.nombre as categoria FROM productos as p
INNER JOIN categorias as c
ON c.id_categoria = p.id_categoria
wHERE c.id_categoria = 2;


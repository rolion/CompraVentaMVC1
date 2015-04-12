USE VentaCompraParcialMySqlFinal
;




CREATE TABLE ReferenciaCliente
(
	IdReferenciaCliente INTEGER NOT NULL AUTO_INCREMENT,
	Telefono INTEGER NOT NULL,
	Correo CHAR(50),
	idCliente INTEGER,
	PRIMARY KEY (IdReferenciaCliente),
	UNIQUE UQ_ReferenciaCliente_Correo(Correo),
	UNIQUE UQ_ReferenciaCliente_IdReferenciaCliente(IdReferenciaCliente),
	UNIQUE UQ_ReferenciaCliente_Telefono(Telefono),
	KEY (idCliente)
) 
;


CREATE TABLE DetalleCompra
(
	IdDetaC INTEGER NOT NULL AUTO_INCREMENT,
	Cantidad INTEGER NOT NULL,
	PrecioXCant FLOAT(2) NOT NULL,
	IdProducto INTEGER,
	IdCompra INTEGER,
	PRIMARY KEY (IdDetaC),
	UNIQUE UQ_DetalleCompra_IdDetaC(IdDetaC),
	KEY (IdCompra)
) 
;


CREATE TABLE Proveedor
(
	IdProveedor INTEGER NOT NULL AUTO_INCREMENT,
	Nombre CHAR(50) NOT NULL,
	PRIMARY KEY (IdProveedor),
	UNIQUE UQ_Proveedor_IdProveedor(IdProveedor),
	UNIQUE UQ_Proveedor_Nombre(Nombre)
) 
;


CREATE TABLE Tienda_Sucursal
(
	IdTienda INTEGER NOT NULL AUTO_INCREMENT,
	Nombre CHAR(50) NOT NULL,
	Ubicacion CHAR(50) NOT NULL,
	IdProducto INTEGER,
	PRIMARY KEY (IdTienda),
	KEY (IdProducto)
) 
;


CREATE TABLE NotaCompra
(
	IdCompra INTEGER NOT NULL AUTO_INCREMENT,
	Fecha DATETIME NOT NULL,
	IdProveedor INTEGER,
	IdUsuario INTEGER,
	PRIMARY KEY (IdCompra),
	UNIQUE UQ_NotaCompra_IdCompra(IdCompra),
	KEY (IdProveedor),
	KEY (IdUsuario)
) 
;


CREATE TABLE Categoria
(
	IdCategoria INTEGER NOT NULL AUTO_INCREMENT,
	Nombre CHAR(50) NOT NULL,
	PRIMARY KEY (IdCategoria),
	UNIQUE UQ_Categoria_IdCategoria(IdCategoria),
	UNIQUE UQ_Categoria_Nombre(Nombre)
) 
;


CREATE TABLE Producto
(
	IdProducto INTEGER NOT NULL AUTO_INCREMENT,
	Nombre CHAR(50) NOT NULL,
	Precio FLOAT(2) NOT NULL,
	IdCategoria INTEGER,
	PRIMARY KEY (IdProducto),
	UNIQUE UQ_Producto_IdProducto(IdProducto),
	UNIQUE UQ_Producto_Nombre(Nombre),
	KEY (IdCategoria),
	KEY (IdCategoria)
) 
;


CREATE TABLE DetalleVenta
(
	IdDetalleV INTEGER NOT NULL AUTO_INCREMENT,
	Precio FLOAT(2) NOT NULL,
	IdNotaVenta INTEGER,
	IdProducto INTEGER,
	PRIMARY KEY (IdDetalleV),
	UNIQUE UQ_DetalleVenta_IdDetalleV(IdDetalleV),
	KEY (IdNotaVenta),
	KEY (IdProducto)
) 
;


CREATE TABLE NotaVenta
(
	IdNotaVenta INTEGER NOT NULL AUTO_INCREMENT,
	Fecha_Hora DATETIME NOT NULL,
	idCliente INTEGER,
	PRIMARY KEY (IdNotaVenta),
	UNIQUE UQ_NotaVenta_IdNotaVenta(IdNotaVenta),
	KEY (idCliente)
) 
;


CREATE TABLE Cliente
(
	IdCliente INTEGER NOT NULL AUTO_INCREMENT,
	Nit_CI INTEGER NOT NULL,
	Nombre CHAR(50) NOT NULL,
	Direccion CHAR(50) NOT NULL,
	PRIMARY KEY (IdCliente),
	UNIQUE UQ_Cliente_IdCliente(IdCliente),
	UNIQUE UQ_Cliente_Nit_CI(Nit_CI)
) 
;


CREATE TABLE Usuario
(
	IdUsuario INTEGER NOT NULL AUTO_INCREMENT,
	Nombre CHAR(50) NOT NULL,
	Loggin CHAR(50) NOT NULL,
	Password VARCHAR(50) NOT NULL,
	PRIMARY KEY (IdUsuario),
	UNIQUE UQ_Usuario_IdUsuario(IdUsuario),
	UNIQUE UQ_Usuario_Loggin(Loggin)
) 
;





ALTER TABLE ReferenciaCliente ADD CONSTRAINT FK_ReferenciaCliente_Cliente 
	FOREIGN KEY (idCliente) REFERENCES Cliente (IdCliente)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE DetalleCompra ADD CONSTRAINT FK_DetalleCompra_NotaCompra 
	FOREIGN KEY (IdCompra) REFERENCES NotaCompra (IdCompra)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE Tienda_Sucursal ADD CONSTRAINT FK_Tienda_Sucursal_Producto 
	FOREIGN KEY (IdProducto) REFERENCES Producto (IdProducto)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE NotaCompra ADD CONSTRAINT FK_NotaCompra_Proveedor 
	FOREIGN KEY (IdProveedor) REFERENCES Proveedor (IdProveedor)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE NotaCompra ADD CONSTRAINT FK_NotaCompra_Usuario 
	FOREIGN KEY (IdUsuario) REFERENCES Usuario (IdUsuario)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE Producto ADD CONSTRAINT FK_Producto_Categoria 
	FOREIGN KEY (IdCategoria) REFERENCES Categoria (IdCategoria)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE Producto ADD CONSTRAINT FK_Producto_DetalleCompra 
	FOREIGN KEY (IdCategoria) REFERENCES DetalleCompra (IdDetaC)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE DetalleVenta ADD CONSTRAINT FK_DetalleVenta_NotaVenta 
	FOREIGN KEY (IdNotaVenta) REFERENCES NotaVenta (IdNotaVenta)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE DetalleVenta ADD CONSTRAINT FK_DetalleVenta_Producto 
	FOREIGN KEY (IdProducto) REFERENCES Producto (IdProducto)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

ALTER TABLE NotaVenta ADD CONSTRAINT FK_NotaVenta_Cliente 
	FOREIGN KEY (idCliente) REFERENCES Cliente (IdCliente)
	ON DELETE NO ACTION ON UPDATE CASCADE
;

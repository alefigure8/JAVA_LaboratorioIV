create database bdBancos;
use bdBancos;

CREATE TABLE Usuarios (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Usuario VARCHAR(50) NOT NULL UNIQUE,
    Contrasena VARCHAR(100) NOT NULL,
    TipoAcceso ENUM('Administrador', 'Cliente') NOT NULL,
    Fechaalta date not null,
    Activo bit default 1
);

CREATE TABLE Provincias(
    IDProvincia int not null primary key AUTO_INCREMENT,
    NombreProvincia varchar(50) not null
);

CREATE TABLE Localidades(
    IDLocalidad int not null primary key AUTO_INCREMENT,
    NombreLocalidad varchar(150) not null,
    IDProvincia int not null,
    foreign key (IDProvincia) references Provincias(IDProvincia)
);

create table Direcciones(
    IdDireccion int not null primary key AUTO_INCREMENT,
    IdLocalidad int not null, 
    CodigoPostal int not null, 
    Calle varchar(30) not null,
	Numero int,
	TipoDireccion ENUM('Casa', 'Departamento') NOT NULL,
	NumeroDepartamento varchar(10) null,
    foreign key (IdLocalidad) references Localidades(IdLocalidad)
);

CREATE TABLE Clientes(
 	Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Id int not null, 
    Dni int not null UNIQUE,
	Cuil int not null,
	Sexo char(1) not null,
	Nacionalidad varchar(50) not null,
    FechaNacimiento DATE not null,
    Correo varchar(50) not null UNIQUE,
	Telefono int not null,
    IDDomicilio int null,
    PRIMARY KEY (Id),
    foreign key (Id) references Usuarios(Id),
    foreign key (IDDomicilio) references Direcciones (IdDireccion)
);

CREATE TABLE TiposCuenta (
    IdTipoCuenta INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50)
);

CREATE TABLE Estados (
	IdEstados INT PRIMARY KEY AUTO_INCREMENT,
    Descripcion VARCHAR(50)
);

CREATE TABLE Cuentas (
    NumeroCuenta int PRIMARY KEY AUTO_INCREMENT,
    CBU varchar(50) UNIQUE,
    Saldo DECIMAL(10, 2) NOT NULL,
    IdTipoCuenta int not null,
    IdCliente INT not null,
    fechaCreacion Date not null,
    Activo bit default 1,
    FOREIGN KEY (IdCliente) REFERENCES Clientes(Id),
    FOREIGN KEY (IdTipoCuenta) REFERENCES TiposCuenta(IdTipoCuenta)
    
);

ALTER TABLE Cuentas AUTO_INCREMENT = 1000000000;

CREATE TABLE TipoTasa (
    IdTipoInteres INT PRIMARY KEY AUTO_INCREMENT,
    CantCuotas INT NOT NULL,
	TasaInteres DECIMAL(10, 2) NOT NULL  
);

CREATE TABLE Prestamos (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    MontoPedido DECIMAL(10, 2) NOT NULL,
    IdTasaxCuotas int not null,
    IdEstados INT NOT NULL,
    Cancelado bit default 0 not null, 
    FechaPrestamo date not null,
    IdCliente INT not null,
    FOREIGN KEY (IdCliente) REFERENCES Usuarios(ID),
    FOREIGN KEY (IdEstados) REFERENCES Estados(IdEstados),
    FOREIGN KEY (IdTasaxCuotas) REFERENCES TipoTasa(IdTipoInteres)
);


CREATE TABLE CuotasPrestamo (
    ID INT AUTO_INCREMENT,
    IDPrestamo INT,
    NumeroCuota INT NOT NULL,
    MontoCuota DECIMAL(10, 2) NOT NULL,
    FechaVencimiento DATE not null, 
    FechaPagoCuota DATE null,
    Estado ENUM('Pendiente', 'Pagado') NOT NULL,
    PRIMARY KEY (ID, IDPrestamo),
    FOREIGN KEY (IDPrestamo) REFERENCES Prestamos(ID)
);

ALTER TABLE Prestamos
ADD COLUMN NumeroCuenta INT,
ADD FOREIGN KEY (NumeroCuenta) REFERENCES Cuentas(NumeroCuenta);

CREATE TABLE TiposMovimiento (
    IdTipoMovimiento INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50)
);

CREATE TABLE Movimientos (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    IdTipoMovimiento int NOT NULL,
    NumeroReferencia int NOT NULL,
    CBU varchar(50),
    Monto DECIMAL(10, 2) NOT NULL,
    Operacion ENUM('Entrada', 'Salida') NOT NULL,
    FechaMovimiento date not null,
    IdEstados INT NOT NULL,
    Concepto varchar(50),
    FOREIGN KEY (IdTipoMovimiento) REFERENCES TiposMovimiento (IdTipoMovimiento),
    FOREIGN KEY (CBU) REFERENCES Cuentas (CBU),
    FOREIGN KEY (IdEstados) REFERENCES Estados(IdEstados)
);

/******** INSERTS ********/
/******** PROVINCIAS  ********/
INSERT INTO Provincias (NombreProvincia) VALUES
('Buenos Aires'), ('Catamarca'), ('Chaco'), ('Chubut'), ('Córdoba'), ('Corrientes'), ('Entre Ríos'), 
('Formosa'), ('Jujuy'), ('La Pampa'), ('La Rioja'), ('Mendoza'), ('Misiones'), ('Neuquén'), ('Río Negro'), 
('Salta'), ('San Juan'), ('San Luis'), ('Santa Cruz'), ('Santa Fe'), ('Santiago del Estero'), ('Tierra del Fuego'), ('Tucumán');

/******** LOCALIDADES ********/
-- Insertar 3 localidades para Buenos Aires
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('La Plata', 1),
('Mar del Plata', 1),
('Quilmes', 1);

INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('San Fernando del Valle de Catamarca', 2),
('Santa María', 2),
('Andalgalá', 2);


/******** ADMIN ********/
-- Insertar usuario Administrador
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('admin', 'admin', 'Administrador', '2023-11-01');

/******** CLIENTE 1 ********/
-- Insertar usuario Cliente
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente1', 'cliente1', 'Cliente', '2023-11-01');

/******** DOMICILIO 1 ********/
-- Insertar primer domicilio
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (1, 1000, 'Calle 1', 123, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente1 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio1= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insertar datos del primer cliente en la tabla Clientes
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Juan', 'Pérez',@id_cliente1, 123456789, 987654321, 'M', 'Argentina', '1990-05-15', 'cliente1@email.com', 123456789, @id_domicilio1);

/******** CLIENTE 2 ********/
-- Insertar segundo cliente en la tabla Usuarios
INSERT INTO Usuarios ( Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente2', 'cliente2', 'Cliente', '2023-11-01');

/******** DOMICILIO 2 ********/
-- Insertar segundo domicilio
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (2, 2000, 'Calle 2', 456, 'Departamento', 'Apt 3B');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente2 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio2= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insertar datos del segundo cliente en la tabla Clientes
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('María', 'Gómez',@id_cliente2, 987654321, 123456789, 'F', 'México', '1995-08-20', 'cliente2@email.com', 987654321, @id_domicilio2);

-- Insertar Estados
insert into Estados (descripcion) values ("Aprobado"), ("Pendiente"), ("Rechazado");

-- Insertar Tipo Movimientos
insert into TiposMovimiento (descripcion) values ("Alta de cuenta"),("Alta de un prestamo"),("Pago prestamo"),("Transferencia");

-- InsertCuenta
insert into TiposCuenta (Descripcion) values ("Caja de Ahorro"), ("Cuenta Corriente");

-- Insert Cuenta
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, IdEstados, Activo) 
values ("123456789", 10000.00, 1, 2, NOW(), 1, 1),
("987654321", 10000.00, 2, 3, NOW(), 1, 1)
;

/******** SELECT TODOS LOS CLIENTES ********/
select * from Clientes C 
inner join Usuarios U on U.Id=C.Id
inner join Direcciones D on D.IdDireccion=C.IDDomicilio
inner join Localidades L on L.IdLocalidad=D.IdLocalidad
inner join Provincias P on P.IdProvincia=L.IdProvincia;

/* Estados */
select * from Estados;

/* Cuentas */
select * from Cuentas;

/* Tipo Movimientos */
select * from TiposMovimiento;

/* TIPO CUENTAS*/
select * from Cuentas;

/* Moivimientos */
select * from Movimientos;
SELECT * FROM Movimientos WHERE CBU = "123456789";
select * from Movimientos M 
inner join Estados E on M.IdEstados=E.IdEstados
inner join TiposMovimiento TM on TM.IdTipoMovimiento=C.IdTipoMovimiento;

/******** INSERT TIPOS DE CUENTA ********/
insert into TiposCuenta (descripcion) values ('Caja de ahorro');
insert into TiposCuenta (descripcion) values ('Cuenta corriente');


/********* INSERT TIPO TASA **********/
INSERT INTO TipoTasa (CantCuotas, TasaInteres) VALUES (12, 150.0), (24, 300.0), (36, 450.0);


/******************** MODIFICACIONES 1ERA ENTREGA *******************/
/********************** Modificar dni unique *************************/
ALTER TABLE Clientes
ADD CONSTRAINT UNIQUE (Dni);

ALTER TABLE Clientes
ADD CONSTRAINT UNIQUE (Correo);


/********************** Modificar usuario y cliente ***********************/
ALTER TABLE Usuarios
DROP COLUMN Nombre,
DROP COLUMN Apellido;

ALTER TABLE Clientes
ADD COLUMN Nombre VARCHAR(50) NOT NULL,
ADD COLUMN Apellido VARCHAR(50) NOT NULL;

/************* Modificar cuentas con idCliente, no con idUsuario *************/
ALTER TABLE Cuentas
DROP FOREIGN KEY cuentas_ibfk_1;



ALTER TABLE Cuentas
ADD FOREIGN KEY (IdCliente) REFERENCES Clientes(Id);

/******************** INSERT PRESTAMOS *******************/
/********************** Prestamos ***********************/

/*CAMBIARLO SEGUN EL NRO. DE CUENTA E ID DE CLIENTE*/

INSERT INTO Prestamos (MontoPedido, MontoConIntereses, IdTasaxCuotas, MontoXmes, IdEstados, Cancelado, FechaPrestamo, IdCliente)
VALUES (
    -- MontoPedido
    1000.00,
    
    -- MontoConIntereses 
    1000.00 * (SELECT TasaInteres FROM TipoTasa WHERE IdTipoInteres = 1) / 100 + 1000.00,
    
    -- IdTasaxCuotas
    1,
    
    -- MontoXmes (MontoConIntereses / Cantidad de cuotas obtenida de TipoTasa)
    (1000.00 * (SELECT TasaInteres FROM TipoTasa WHERE IdTipoInteres = 1) / 100 + 1000.00) /
        (SELECT CantCuotas FROM TipoTasa WHERE IdTipoInteres = 1),
    
    -- IdEstados (2 para representar el estado correspondiente)
    2,
    
    -- Cancelado (default en false, representado como 0)
    DEFAULT,
    
    -- FechaPrestamo 
    CURRENT_DATE,
    
    -- IdCliente
    16
);

/* CAMBIARLO SEGUN EL NRO. DE CUENTA E ID DE CLIENTE */ 
update prestamos set IdEstados=2 where id>1
update prestamos set numeroCuenta='1000000036' where id=6
update prestamos set numeroCuenta='1000000036' where id=5
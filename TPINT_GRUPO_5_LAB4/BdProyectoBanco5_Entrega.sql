DROP DATABASE bdBancos;
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
	Cuil bigint not null,
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
   MontoConIntereses DECIMAL(10, 2) NOT NULL, 
    MontoXmes DECIMAL(10, 2) NOT NULL,
    NumeroCuenta int not null,
    IdTasaxCuotas int not null,
    IdEstados INT NOT NULL,
    Cancelado bit default 0 not null, 
    FechaPrestamo date not null,
    IdCliente INT not null,
    FOREIGN KEY (IdCliente) REFERENCES Clientes(Id),
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

/******** LOCALIDADES ********/

-- Insertar 3 localidades para Chaco (IDProvincia = 3)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Resistencia', 3),
('Barranqueras', 3),
('Presidencia Roque Sáenz Peña', 3);

-- Insertar 3 localidades para Chubut (IDProvincia = 4)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Rawson', 4),
('Comodoro Rivadavia', 4),
('Trelew', 4);

-- Insertar 3 localidades para Córdoba (IDProvincia = 5)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Córdoba', 5),
('Villa María', 5),
('Río Cuarto', 5);

-- Insertar 3 localidades para Corrientes (IDProvincia = 6)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Corrientes', 6),
('Goya', 6),
('Mercedes', 6);

-- Insertar 3 localidades para Entre Ríos (IDProvincia = 7)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Paraná', 7),
('Concordia', 7),
('Gualeguaychú', 7);

/******** LOCALIDADES ********/

-- Insertar 3 localidades para Formosa (IDProvincia = 8)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Formosa', 8),
('Clorinda', 8),
('Pirané', 8);

-- Insertar 3 localidades para Jujuy (IDProvincia = 9)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('San Salvador de Jujuy', 9),
('Palpalá', 9),
('La Quiaca', 9);

-- Insertar 3 localidades para La Pampa (IDProvincia = 10)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Santa Rosa', 10),
('General Pico', 10),
('Toay', 10);

-- Insertar 3 localidades para La Rioja (IDProvincia = 11)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('La Rioja', 11),
('Chilecito', 11),
('Famatina', 11);

-- Insertar 3 localidades para Mendoza (IDProvincia = 12)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Mendoza', 12),
('San Rafael', 12),
('Godoy Cruz', 12);

/******** LOCALIDADES ********/

-- Insertar 3 localidades para Misiones (IDProvincia = 13)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Posadas', 13),
('Puerto Iguazú', 13),
('Oberá', 13);

-- Insertar 3 localidades para Neuquén (IDProvincia = 14)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Neuquén', 14),
('Cutral Có', 14),
('Plottier', 14);

-- Insertar 3 localidades para Río Negro (IDProvincia = 15)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Viedma', 15),
('General Roca', 15),
('Cipolletti', 15);

-- Insertar 3 localidades para Salta (IDProvincia = 16)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Salta', 16),
('San Ramón de la Nueva Orán', 16),
('Cafayate', 16);

-- Insertar 3 localidades para San Juan (IDProvincia = 17)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('San Juan', 17),
('Rivadavia', 17),
('Rawson', 17);
/******** LOCALIDADES ********/

-- Insertar 3 localidades para San Luis (IDProvincia = 18)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('San Luis', 18),
('Villa Mercedes', 18),
('La Toma', 18);

-- Insertar 3 localidades para Santa Cruz (IDProvincia = 19)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Río Gallegos', 19),
('Caleta Olivia', 19),
('Puerto San Julián', 19);

-- Insertar 3 localidades para Santa Fe (IDProvincia = 20)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Santa Fe', 20),
('Rosario', 20),
('Santo Tomé', 20);

-- Insertar 3 localidades para Santiago del Estero (IDProvincia = 21)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Santiago del Estero', 21),
('La Banda', 21),
('Termas de Río Hondo', 21);

-- Insertar 3 localidades para Tierra del Fuego (IDProvincia = 22)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('Ushuaia', 22),
('Río Grande', 22),
('Tolhuin', 22);

-- Insertar 3 localidades para Tucumán (IDProvincia = 23)
INSERT INTO Localidades (NombreLocalidad, IDProvincia) VALUES
('San Miguel de Tucumán', 23),
('Yerba Buena', 23),
('Concepción', 23);


/******** INSERT TIPOS DE CUENTA ********/
insert into TiposCuenta (descripcion) values ('Caja de ahorro');
insert into TiposCuenta (descripcion) values ('Cuenta corriente');

-- Insertar Estados
insert into Estados (descripcion) values ("Aprobado"), ("Pendiente"), ("Rechazado");

-- Insertar Tipo Movimientos
insert into TiposMovimiento (descripcion) values ("Alta de cuenta"),("Alta de un prestamo"),("Pago prestamo"),("Transferencia");

-- InsertCuenta
insert into TiposCuenta (Descripcion) values ("Caja de Ahorro"), ("Cuenta Corriente");

/********* INSERT TIPO TASA **********/
INSERT INTO TipoTasa (CantCuotas, TasaInteres) VALUES (12, 150.0), (24, 300.0), (36, 450.0);

/******** USUARIO ADMIN ********/
-- Insertar usuario Administrador
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('admin', 'admin', 'Administrador', '2023-11-01');


/******** USUARIO CLIENTE + CLIENTE ********/

/**** CLIENTE 1 ****/
-- Insertar usuario Cliente 1
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente1', 'cliente1', 'Cliente', '2023-11-01');

-- Insertar direccion 1
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (1, 1000, 'Calle 1', 123, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente1 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio1= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insertar Cliente 1
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Juan', 'Pérez',@id_cliente1, 12345678, 20123456781, 'M', 'Argentina', '1990-05-15', 'cliente1@email.com', 1123456789, @id_domicilio1);


/**** CLIENTE 2 ****/

-- Insertar usuario Cliente 2
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente2', 'cliente2', 'Cliente', '2023-12-01');

-- Insert direccion 2
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (2, 2000, 'Calle 2', 456, 'Departamento', 'A1');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente2 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio2= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insertar Cliente 2
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Carlos', 'Gómez', @id_cliente2, 23456789, 20234567890, 'M', 'Argentina','1961-05-15', 'cliente2@email.com', 1124561000, @id_domicilio2);


/**** CLIENTE 3 ****/

-- Insertar usuario Cliente 3
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente3', 'cliente3', 'Cliente', '2023-10-01');

-- Insert direccion 3
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (3, 3000, 'Calle 3', 789, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente3 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio3= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insertar Cliente 3
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Luis', 'Rodríguez', @id_cliente3, 34567890, 20345678901, 'M', 'Argentina', '1971-05-15', 'cliente3@email.com', 1145678901, @id_domicilio3);

/**** CLIENTE 4 ****/

-- Insertar usuario Cliente 4
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente4', 'cliente4', 'Cliente', '2023-09-01');

-- Insert Direccion 4
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (1, 1500, 'Calle 4', 321, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente4 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio4= (SELECT MAX(IdDireccion) FROM Direcciones);

INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Mario', 'Hernández', @id_cliente4, 45678912, 20456789121, 'M', 'Argentina', '1981-05-15', 'cliente4@email.com', 1145890012, @id_domicilio4);

/**** CLIENTE 5 ****/

-- Insertar usuario Cliente 5
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente5', 'cliente5', 'Cliente', '2023-11-15');

-- Insert direccion 5
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (2, 2500, 'Calle 5', 654, 'Departamento', 'B2');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente5 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio5= (SELECT MAX(IdDireccion) FROM Direcciones);


INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Jorge', 'López', @id_cliente5, 36890123, 20368901231, 'M', 'Argentina','2001-05-15' , 'cliente5@email.com', 1156789012, @id_domicilio5);

/**** CLIENTE 6 ****/

-- Insertar usuario Cliente 6
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente6', 'cliente6', 'Cliente', '2023-08-01');

-- Insert direccion 6
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (3, 3500, 'Calle 6', 987, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente6 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio6= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 6
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('María', 'Gómez', @id_cliente6, 54890124, 20548901240, 'F', 'Argentina','1980-09-22' , 'cliente6@email.com', 1154789012, @id_domicilio6);


/**** CLIENTE 7 ****/

-- Insertar usuario Cliente 7
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente7', 'cliente7', 'Cliente', '2023-11-22');

-- Insert Direccion 7
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (1, 1200, 'Calle 7', 555, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente7 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio7= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 7
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Laura', 'Martínez', @id_cliente7, 35789012, 20357890120, 'F', 'Argentina','1975-03-10' , 'cliente7@email.com', 1138890125, @id_domicilio7);

/**** CLIENTE 8 ****/

-- Insertar usuario Cliente 8
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente8', 'cliente8', 'Cliente', '2023-10-10');

-- Insert direccion 8
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (2, 2200, 'Calle 8', 888, 'Departamento', 'C3');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente8 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio8= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 8
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Ana', 'Rodríguez', @id_cliente8, 23890126, 20238901260, 'F', 'Argentina','1990-07-14' , 'cliente8@email.com', 1161230126, @id_domicilio8);


/**** CLIENTE 9 ****/

-- Insertar usuario Cliente 9
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente9', 'cliente9', 'Cliente', '2023-09-15');

-- Insert direccion 9
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (3, 3200, 'Calle 9', 111, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente9 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio9= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 9
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Carolina', 'López', @id_cliente9, 28090127, 20280901270, 'F', 'Argentina','1985-11-03' , 'cliente9@email.com', 1152890127, @id_domicilio9);

/**** CLIENTE 10 ****/

-- Insertar usuario Cliente 10
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente10', 'cliente10', 'Cliente', '2023-12-05');

-- Insert direccion 10
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (3, 3200, 'Calle 9', 111, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente10 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio10= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 10
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Susana', 'Fernández', @id_cliente10, 16890128, 20168901280, 'F', 'Argentina','1970-12-18' , 'cliente10@email.com', 1156789012, @id_domicilio10);


/**** CLIENTE 11 ****/


-- Insertar usuario Cliente 11
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente11', 'cliente11', 'Cliente', '2023-11-10');

-- Insert 11
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (4, 4000, 'Calle 12', 123, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente11 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio11= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 11
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Gabriela', 'Sánchez', @id_cliente11, 31890129, 20318901290, 'F', 'Argentina','1978-08-27' , 'cliente11@email.com', 1156750129, @id_domicilio11);




/**** CLIENTE 12 ****/

-- Insertar usuario Cliente 12
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente12', 'cliente12', 'Cliente', '2023-10-05');

-- Insert 12
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (7, 7000, 'Calle 13', 456, 'Departamento', 'E5');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente12 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio12= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 12
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Valeria', 'Díaz', @id_cliente12, 41090130, 20410901300, 'F', 'Argentina','1983-04-05' , 'cliente12@email.com', 1156590130, @id_domicilio12);


/**** CLIENTE 13 ****/

-- Insertar usuario Cliente 13
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente13', 'cliente13', 'Cliente', '2023-09-20');

-- Insert direccion 13
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (10, 10000, 'Calle 14', 789, 'Casa', NULL);

-- Obtener el último ID insertado en Usuarios
SET @id_cliente13 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio13= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 13
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Florencia', 'Pérez', @id_cliente13, 17890131, 20178901310, 'F', 'Argentina','1995-01-20' , 'cliente13@email.com', 1159870131, @id_domicilio13);



/**** CLIENTE 14 ****/

-- Insertar usuario Cliente 14
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente14', 'cliente14', 'Cliente', '2023-12-15');

-- Insert 14
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (13, 13000, 'Calle 15', 321, 'Departamento', 'F6');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente14 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio14= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 14
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Silvina', 'Hernández', @id_cliente14, 35090132, 20350901320, 'F', 'Argentina','1965-06-12' , 'cliente14@email.com', 1157894132, @id_domicilio14);


/**** CLIENTE 15 ****/
-- Insertar usuario Cliente 15
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente15', 'cliente15', 'Cliente', '2023-08-15');

-- Insert 15
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (16, 16000, 'Calle 16', 654, 'Casa', NULL);


-- Obtener el último ID insertado en Usuarios
SET @id_cliente15 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio15= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 15
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Martina', 'Giménez', @id_cliente15, 42090133, 20420901330, 'F', 'Argentina','1987-09-08' , 'cliente15@email.com', 1146789013, @id_domicilio15);

/**** CLIENTE 16 ****/
-- Insertar usuario Cliente 16
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente16', 'cliente16', 'Cliente', '2023-11-25');

-- Insert 16
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (19, 19000, 'Calle 17', 123, 'Casa', NULL);


-- Obtener el último ID insertado en Usuarios
SET @id_cliente16 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio16= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 16
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Alejandro', 'Ramírez', @id_cliente16, 34890134, 20348901341, 'M', 'Argentina','1982-10-15' , 'cliente16@email.com', 1132890134, @id_domicilio16);





/**** CLIENTE 17 ****/

-- Insertar usuario Cliente 17
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente17', 'cliente17', 'Cliente', '2023-10-18');

-- Insert 17
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (22, 22000, 'Calle 18', 456, 'Departamento', 'G7');

-- Obtener el último ID insertado en Usuarios
SET @id_cliente17 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio17= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 17
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Martín', 'Suárez', @id_cliente17, 33780135, 20337801351, 'M', 'Argentina','1993-02-28' , 'cliente17@email.com', 1156789015, @id_domicilio17);


/**** CLIENTE 18 ****/

-- Insertar usuario Cliente 18
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente18', 'cliente18', 'Cliente', '2023-09-30');

-- Insert 18
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (25, 25000, 'Calle 19', 789, 'Casa', NULL);


-- Obtener el último ID insertado en Usuarios
SET @id_cliente18 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio18= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 18
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Luis', 'Mendoza', @id_cliente18, 35090136, 20350901361, 'M', 'Argentina','1976-07-03' , 'cliente18@email.com', 1167890136, @id_domicilio18);

/**** CLIENTE 19 ****/

-- Insertar usuario Cliente 19
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente19', 'cliente19', 'Cliente', '2023-12-10');

-- Insert 19
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (28, 28000, 'Calle 20', 321, 'Departamento', 'H8');


-- Obtener el último ID insertado en Usuarios
SET @id_cliente19 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio19= (SELECT MAX(IdDireccion) FROM Direcciones);


-- Insert Cliente 19
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Javier', 'Torres', @id_cliente19, 25789137, 20257891371, 'M', 'Argentina','1988-11-19' , 'cliente19@email.com', 1156712137, @id_domicilio19);


/**** CLIENTE 20 ****/

-- Insertar usuario Cliente 20
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('cliente20', 'cliente20', 'Cliente', '2023-08-22');

-- Insert 20
INSERT INTO Direcciones (IdLocalidad, CodigoPostal, Calle, Numero, TipoDireccion, NumeroDepartamento)
VALUES (31, 31000, 'Calle 21', 654, 'Casa', NULL);


-- Obtener el último ID insertado en Usuarios
SET @id_cliente20 = (SELECT MAX(Id) FROM Usuarios);
SET @id_domicilio20= (SELECT MAX(IdDireccion) FROM Direcciones);

-- Insert Cliente 20
INSERT INTO Clientes (Nombre, Apellido, Id, Dni, Cuil, Sexo, Nacionalidad, FechaNacimiento, Correo, Telefono, IDDomicilio)
VALUES ('Diego', 'Cabrera', @id_cliente20, 26790138, 20267901381, 'M', 'Argentina','1971-04-08' , 'cliente20@email.com', 1158201138, @id_domicilio20);


-- Insert Cuentas Cliente 1 

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
values ("1429898198151655919526", 10000.00, 1, 2, '2023-01-08', 1);

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES ("1429898198151655919525", 10000.00, 1, 2, '2023-01-08', 1);

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES ("1629898198151655919529", 10000.00, 1, 2, '2023-10-08', 1);

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES ("1629898198151655919530", 0, 1, 2, '2023-09-08', 0);
;
-- Inserts Cuentas Cliente 2

set @CBU=1234501234987654321000;
INSERT INTO Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 3, '2023-02-15', 1);

set @CBU=@CBU+1;
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 3, '2023-02-15', 1);

set @CBU=@CBU+1;
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 3, '2023-10-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 3, '2023-09-15', 0);

-- Inserts Cuentas Cliente 3

-- Cuenta activa = 1, IdTipoCuenta = 1
set @CBU=@CBU+1;
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 4, '2023-03-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 4, '2023-03-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 2, 4, '2023-09-15', 0);

-- Inserts Cuentas Cliente 4

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 5, '2023-04-15',  1);

-- Cuenta activa = 1, IdTipoCuenta = 2
set @CBU=@CBU+1;
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 5, '2023-04-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 5, '2023-09-15', 0);

-- Inserts Cuentas Cliente 5

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 6, '2023-02-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 6, '2023-02-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 6, '2023-06-15', 0);

-- Inserts Cuentas Cliente 6

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 7, '2023-04-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 7, '2023-04-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)),0, 1, 7, '2023-03-15', 0);

-- Inserts Cuentas Cliente 7

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 8, '2023-02-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 8, '2023-04-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 8, '2023-03-15', 0);

-- Inserts Cuentas Cliente 8
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 9, '2023-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 9, '2023-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 9, '2023-10-15', 0);


-- Inserts Cuentas Cliente 9
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 10, '2023-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 10, '2023-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)),0, 1, 10, '2023-10-15', 0);

-- Inserts Cuentas Cliente 10
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 11, '2022-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 11, '2022-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 11, '2022-10-15', 0);

-- Inserts Cuentas Cliente 11
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 12, '2022-9-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 12, '2022-8-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 12, '2022-7-15', 0);

-- Inserts Cuentas Cliente 12
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 13, '2022-06-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 13, '2022-05-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 13, '2022-04-15', 0);

-- Inserts Cuentas Cliente 13
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 14, '2022-03-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 14, '2022-02-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 14, '2022-01-15', 0);

-- Inserts Cuentas Cliente 14
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 15, '2022-03-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 15, '2022-02-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 15, '2022-01-15', 0);

-- Inserts Cuentas Cliente 15
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 16, '2021-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 16, '2021-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)),0, 1, 16, '2021-10-15',0);

-- Inserts Cuentas Cliente 16

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 17, '2021-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 17, '2021-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 17, '2021-10-15', 0);

-- Inserts Cuentas Cliente 17
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 18, '2021-06-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 18, '2021-05-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 18, '2021-04-15', 0);


-- Inserts Cuentas Cliente 18
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 19, '2021-06-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 19, '2021-05-15',  1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 19, '2021-04-15',  0);

-- Inserts Cuentas Cliente 19

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
INSERT INTO Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion,  Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 20, '2023-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 20, '2023-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 20, '2023-10-15', 0);

-- Inserts Cuentas Cliente 20

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
INSERT INTO Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion,  Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 21, '2023-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 21, '2023-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 21, '2023-10-15', 0);

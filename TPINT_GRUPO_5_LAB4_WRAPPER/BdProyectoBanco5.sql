create database bdBancos;

use bdBancos;



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
	Activo bit default 1,
    foreign key (IdLocalidad) references Localidades(IdLocalidad)
);


CREATE TABLE Usuarios (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Usuario VARCHAR(50) NOT NULL UNIQUE,
    Contrasena VARCHAR(100) NOT NULL,
    TipoAcceso ENUM('1', '2') NOT NULL,
    Activo bit default 1
);

CREATE TABLE Clientes(
    Id int not null, 
    Dni int not null,
	Cuil int not null,
	Sexo char(1) not null,
	Nacionalidad varchar(50) not null,
    FechaNacimiento DATE not null,
    Correo varchar(50) not null,
	Telefono int not null,
    IDDomicilio int null,
	Activo bit default 1,
    PRIMARY KEY (Id),
    foreign key (Id) references Usuarios(Id),
    foreign key (IDDomicilio) references Direcciones (IdDireccion)
);



CREATE TABLE TiposCuenta (
    IdTipoCuenta INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50)
);

CREATE TABLE Cuentas (
    NumeroCuenta int PRIMARY KEY AUTO_INCREMENT,
    CBU varchar(50) UNIQUE,
    Saldo DECIMAL(10, 2) NOT NULL,
    IdTipoCuenta int not null,
    IdCliente INT not null,
    fechaCreacion Date not null,
    Activo bit default 1,
    FOREIGN KEY (IdCliente) REFERENCES Usuarios(ID),
    FOREIGN KEY (IdTipoCuenta) REFERENCES TiposCuenta(IdTipoCuenta)
);

ALTER TABLE Cuentas AUTO_INCREMENT = 1000000000;


CREATE TABLE Prestamos (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    MontoPedido DECIMAL(10, 2) NOT NULL,
	MontoConIntereses DECIMAL(10, 2) NOT NULL,
    Cuotas INT NOT NULL,
    PlazoPago int not null,
    MontoXmes DECIMAL(10, 2) NOT NULL,
    Estado ENUM('Pendiente', 'Aprobado', 'Rechazado') NOT NULL,
    Activo bit default 0 not null, 
    FechaPrestamo date not null,
    IdCliente INT not null,
    FOREIGN KEY (IdCliente) REFERENCES Usuarios(ID)
);

CREATE TABLE CuotasPrestamo (
    ID INT AUTO_INCREMENT,
    IDPrestamo INT,
    NumeroCuota INT NOT NULL,
    MontoCuota DECIMAL(10, 2) NOT NULL,
    FechaPagoCuota DATE not null,
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
    Concepto varchar(50),
    FOREIGN KEY (IdTipoMovimiento) REFERENCES TiposMovimiento (IdTipoMovimiento),
    FOREIGN KEY (CBU) REFERENCES Cuentas (CBU)
);
select * from Clientes
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

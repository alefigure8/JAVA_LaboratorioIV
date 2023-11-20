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



-- Insertar Estados
insert into Estados (descripcion) values ("Aprobado"), ("Pendiente"), ("Rechazado");

-- Insertar Tipo Movimientos
insert into TiposMovimiento (descripcion) values ("Alta de cuenta"),("Alta de un prestamo"),("Pago cuota prestamo"),("Transferencia");

-- InsertCuenta
insert into TiposCuenta (Descripcion) values ("Caja de Ahorro"), ("Cuenta Corriente");

/********* INSERT TIPO TASA **********/
INSERT INTO TipoTasa (CantCuotas, TasaInteres) VALUES (12, 150.0), (24, 300.0), (36, 450.0);

/******** USUARIO ADMIN ********/
-- Insertar usuario Administrador
INSERT INTO Usuarios (Usuario, Contrasena, TipoAcceso, Fechaalta)
VALUES ('admin', 'admin', 'Administrador', '2023-11-01');
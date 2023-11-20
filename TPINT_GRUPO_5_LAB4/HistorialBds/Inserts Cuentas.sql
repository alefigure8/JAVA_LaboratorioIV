
-- Insert Cuentas Cliente 1 

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
values ("1429898198151655919526", 10000.00, 1, 2, '2023-11-08', 1);

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES ("1429898198151655919525", 10000.00, 1, 2, '2023-10-08', 1);

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES ("1629898198151655919529", 10000.00, 1, 2, '2023-10-08', 1);

insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES ("1629898198151655919530", 0, 1, 2, '2023-09-08', 0);
;
-- Inserts Cuentas Cliente 2

set @CBU=1234501234987654321000;
INSERT INTO Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 3, '2023-11-15', 1);

set @CBU=@CBU+1;
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 3, '2023-11-15', 1);

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
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 4, '2023-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 4, '2023-10-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 2, 4, '2023-09-15', 0);

-- Inserts Cuentas Cliente 4
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 5, '2023-11-15',  1);

-- Cuenta activa = 1, IdTipoCuenta = 2
set @CBU=@CBU+1;
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 5, '2023-10-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 5, '2023-09-15', 0);

-- Inserts Cuentas Cliente 5

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 6, '2023-08-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 6, '2023-07-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 6, '2023-06-15', 0);

-- Inserts Cuentas Cliente 6

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 7, '2023-05-15', 1);

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
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 8, '2023-05-15', 1);

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

-- Inserts Cuentas Cliente 13
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

-- Inserts Cuentas Cliente 14
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 15, '2022-06-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 15, '2022-05-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 15, '2022-04-15', 0);

-- Inserts Cuentas Cliente 15
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 16, '2022-03-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 16, '2022-02-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 16, '2022-01-15', 0);

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
VALUES (CAST(@CBU AS CHAR(22)),0, 1, 17, '2021-10-15',0);

-- Inserts Cuentas Cliente 17

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 18, '2021-12-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 18, '2021-11-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 18, '2021-10-15', 0);

-- Inserts Cuentas Cliente 18
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 19, '2021-06-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 19, '2021-05-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo)  
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 19, '2021-04-15', 0);


-- Inserts Cuentas Cliente 19
set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 1
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 1, 20, '2021-06-15', 1);

set @CBU=@CBU+1;
-- Cuenta activa = 1, IdTipoCuenta = 2
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 10000.00, 2, 20, '2021-05-15',  1);

set @CBU=@CBU+1;
-- Cuenta activa = 0 (inactivo), fecha de creación en un mes anterior
insert into Cuentas (CBU, Saldo, IdTipoCuenta, IdCliente, fechaCreacion, Activo) 
VALUES (CAST(@CBU AS CHAR(22)), 0, 1, 20, '2021-04-15',  0);

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


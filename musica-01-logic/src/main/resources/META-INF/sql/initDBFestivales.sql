delete from BoletaEntity;
delete from FuncionEntity;
delete from LugarEntity;
delete from CiudadEntity;
delete from FestivalEntity;
delete from MusicoEntity;
delete from GeneroEntity;
delete from UsuarioEntity;


insert into CiudadEntity (id, nombre) values (1,'Cartagena');
insert into CiudadEntity (id, nombre) values (2,'Medellin');
insert into CiudadEntity (id, nombre) values (3,'Bogota');

insert into LugarEntity (id, nombre, abierto, capacidad, capacidadSonido, costoPreferencial, costoEconomico) values (1, 'Estadio Olimpico Jaime Moron Leon', 1, 100, 170, 300000, 150000);
insert into LugarEntity (id, nombre, abierto, capacidad, capacidadSonido, costoPreferencial, costoEconomico) values (2, 'Atanasio Girardot Sports Complex', 1, 100, 200, 180000, 90000);
insert into LugarEntity (id, nombre, abierto, capacidad, capacidadSonido, costoPreferencial, costoEconomico) values (3, 'El Campin', 1, 100, 150, 150000, 110000);

insert into FestivalEntity (id, nombre, fechaInicio, fechaFin) values (1, 'StoryLand', '08/10/2017', '08/12/2017');
insert into FestivalEntity (id, nombre, fechaInicio, fechaFin) values (2, 'Festival Vive la Musica', '09/07/2017', '09/09/2017');
insert into FestivalEntity (id, nombre, fechaInicio, fechaFin) values (3, 'Festival Estereo Picnic', '10/19/2017', '10/21/2017');

insert into FuncionEntity (id, fechaInicio, fechaFin, aprobada, calificacion) values (1, '08/11/2017', '08/11/2017', 1, 4.3);
insert into FuncionEntity (id, fechaInicio, fechaFin, aprobada, calificacion) values (2, '09/07/2017', '09/07/2017', 1, 4.3);
insert into FuncionEntity (id, fechaInicio, fechaFin, aprobada, calificacion) values (3, '10/19/2017', '10/19/2017', 1, 4.3);

insert into GeneroEntity (id, nombre) values(1, 'Electronica');
insert into GeneroEntity (id, nombre) values(2, 'Salsa');
insert into GeneroEntity (id, nombre) values(3, 'Pop');

insert into MusicoEntity (id, nombre) values (1, 'Martin Garrix');
insert into MusicoEntity (id, nombre) values (2, 'Marc Anthony');
insert into MusicoEntity (id, nombre) values (3, 'Clean Bandit');

insert into UsuarioEntity (id, nombre, clave, esAdmin) values (1, 'Javier Barbosa', 'unaBuenaClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (2, 'Luis Herrera', 'unaMejorClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (3, 'Juan Camilo Bustamante', 'unaGranClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (4, 'Pablo Alvarado', 'unaFeaClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (5, 'Christian Potdevin', 'unaGordaClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (6, 'Jaime Chavarriaga', 'unaFacilClave', 0);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (7, 'Felipe Castro', 'unaAmpliaClave', 0);

insert into BoletaEntity (id, precio) values (1, 150000);
insert into BoletaEntity (id, precio) values (2, 90000);
insert into BoletaEntity (id, precio) values (3, 110000);
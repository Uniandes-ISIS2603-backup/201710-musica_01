delete from BoletaEntity;
delete from FuncionEntity;
delete from LugarEntity;
delete from CiudadEntity;
delete from FestivalEntity;
delete from MusicoEntity;
delete from GeneroEntity;
delete from UsuarioEntity;


insert into CiudadEntity (id, nombre) values (100,'Cartagena');
insert into CiudadEntity (id, nombre) values (200,'Medellin');
insert into CiudadEntity (id, nombre) values (300,'Bogota');

insert into LugarEntity (id, nombre, abierto, capacidad, capacidadSonido, costoPreferencial, costoEconomico, ciudadLugar_id) values (100, 'Estadio Olimpico Jaime Moron Leon', 1, 100, 170, 300000, 150000, 100);
insert into LugarEntity (id, nombre, abierto, capacidad, capacidadSonido, costoPreferencial, costoEconomico, ciudadLugar_id) values (200, 'Atanasio Girardot Sports Complex', 1, 100, 200, 180000, 90000, 200);
insert into LugarEntity (id, nombre, abierto, capacidad, capacidadSonido, costoPreferencial, costoEconomico, ciudadLugar_id) values (300, 'El Campin', 1, 100, 150, 150000, 110000, 300);

insert into FestivalEntity (id, nombre, fechaInicio, fechaFin) values (100, 'StoryLand', '08/10/2017', '08/12/2017');
insert into FestivalEntity (id, nombre, fechaInicio, fechaFin) values (200, 'Festival Vive la Musica', '09/07/2017', '09/09/2017');
insert into FestivalEntity (id, nombre, fechaInicio, fechaFin) values (300, 'Festival Estereo Picnic', '10/19/2017', '10/21/2017');

insert into FuncionEntity (id, fechaInicio, fechaFin, aprobada, calificacion, lugarFuncion_id, festival_id) values (100, '08/11/2017', '08/11/2017', 1, 4.3, 200, 200);
insert into FuncionEntity (id, fechaInicio, fechaFin, aprobada, calificacion, lugarFuncion_id, festival_id) values (200, '09/07/2017', '09/07/2017', 1, 4.3, 200, 200);
insert into FuncionEntity (id, fechaInicio, fechaFin, aprobada, calificacion, lugarFuncion_id, festival_id) values (300, '10/19/2017', '10/19/2017', 1, 4.3, 200, 200);

insert into GeneroEntity (id, nombre) values(100, 'Electronica');
insert into GeneroEntity (id, nombre) values(200, 'Salsa');
insert into GeneroEntity (id, nombre) values(300, 'Pop');

insert into MusicoEntity (id, nombre, generoMusico_id) values (100, 'Martin Garrix', 100);
insert into MusicoEntity (id, nombre, generoMusico_id) values (200, 'Marc Anthony', 200);
insert into MusicoEntity (id, nombre, generoMusico_id) values (300, 'Clean Bandit', 300);

insert into UsuarioEntity (id, nombre, clave, esAdmin) values (100, 'Javier Barbosa', 'unaBuenaClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (200, 'Luis Herrera', 'unaMejorClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (300, 'Juan Camilo Bustamante', 'unaGranClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (400, 'Pablo Alvarado', 'unaFeaClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (500, 'Christian Potdevin', 'unaGordaClave', 1);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (600, 'Jaime Chavarriaga', 'unaFacilClave', 0);
insert into UsuarioEntity (id, nombre, clave, esAdmin) values (700, 'Felipe Castro', 'unaAmpliaClave', 0);

insert into BoletaEntity (id, precio, funcion_id) values (100, 150000, 100);
insert into BoletaEntity (id, precio, funcion_id) values (200, 90000, 200);
insert into BoletaEntity (id, precio, cliente_id, funcion_id) values (300, 110000, 600, 300);
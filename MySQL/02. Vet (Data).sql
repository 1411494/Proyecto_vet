

use vet;

/* persons */
insert into persons values('45196875','Marco', 'Llapapasca','marcollapapasca@gmail.com', 'SMP','973777853','1988-07-16', 1 );
insert into persons values('40102030','Maria', 'Huaman','mariahuaman@gmail.com', 'LIMA','970000544','1990-06-10', 1 );
insert into persons values('41234567','Ana', 'Requejo','anarequejo@gmail.com', 'LIMA','905684567','1995-02-12', 1 );
insert into persons values('23867432','Juan', 'Perez','juanperez@gmail.com', 'LIMA','965533456','1995-08-06', 1 );

/* users */
insert into users values('45196875', 'marco', '123','A',1);
insert into users values('40102030', 'Maria', '123','C',1);
insert into users values('41234567', 'Ana', '123','C',1);
insert into users values('23867432', 'Juan', '123','C',1);

/* pets */
insert into pets values('P0001', '40102030', 'Canela', 'Chihuahua', 'Crema', '2015-03-05','resources/Images/Pet/P0001_40102030_Canela.jpg', 1);
insert into pets values('P0002', '40102030', 'Osita', 'Caniche', 'Blanco', '2016-01-05','resources/Images/Pet/P0002_40102030_Osita.jpg', 1);
insert into pets values('P0001', '41234567', 'Canela', 'Pastor alemán', 'Gris',  '2015-05-05','resources/Images/Pet/P0001_41234567_Canela.jpg', 1);
insert into pets values('P0001', '23867432', 'Serio', 'Pug', 'Cervato', '2015-08-05','resources/Images/Pet/P0001_23867432_Serio.jpg', 1);

/* vaccines */
insert into vaccines values(1,'P0001', '2016-01-15', '1.5 Kg', 'Cataratas','2016-03-15');
insert into vaccines values(2,'P0001', '2016-03-15', '1.6 Kg', 'Tos','2016-06-15');
insert into vaccines values(3,'P0001', '2016-06-15', '1.7 Kg', 'Moquillo','2016-08-15');
insert into vaccines values(1,'P0002', '2016-05-10', '2.28 Kg', 'Cataratas','2016-07-18');
insert into vaccines values(1,'P0003', '2016-07-10', '3.10 Kg', 'Parasitos ','2016-08-02');
insert into vaccines values(1,'P0004', '2016-06-09', '4.10 Kg', 'Rabia','2016-07-09');


select * from persons;
select * from vaccines;
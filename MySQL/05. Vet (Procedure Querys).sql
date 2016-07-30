use vet;

drop procedure if exists `person_List`;
drop procedure if exists `user_List_by_DNI`;
drop procedure if exists `pet_List_by_DNI`;
drop procedure if exists `vaccine_List_by_PetId`;
drop procedure if exists `user_Login`; 

delimiter //
create procedure  `person_List`
(
	_dni				char(8),
	_first_name		varchar(50),
	_last_name		varchar(50)
)
begin
	select 	dni				,
			first_name		,
			last_name		,	
			email			,
			address			,
			phone_number 	,
			birth_date		,
			status			
		from persons
        where (		dni  like concat('',_dni,'%' )
				or concat(first_name, ' ',last_name)  like concat('',_first_name, ' ', _last_name,'%' ) 
				);

END //

delimiter //
create procedure  `user_List_by_DNI`
(
	_dni	char(8)
)
begin
	select 	dni				,
			user_name		,
			password		,
			type			,
			status			
    from users
		where dni = _dni;

END //

delimiter //
create procedure  `pet_List_by_DNI`
(
	_dni	char(8)
)
begin

	select 	pet_id			,
			dni				,
			pet_name		,
			breed			,  /* Raza */
			hair_color		,  /* Color de pelo */
			birth_date		,
            TIMESTAMPDIFF( YEAR, birth_date, now() ) as age_year,
			TIMESTAMPDIFF( MONTH, birth_date, now() ) % 12 as age_month,
			FLOOR( TIMESTAMPDIFF( DAY, birth_date, now() ) % 30.4375 ) as age_day,
            image,
			status		
		from pets
        where dni = _dni;
        
        
END //
 


delimiter //
create procedure  `vaccine_List_by_PetId`
(
	_pet_id	char(5)
)
begin
	select 	vaccine_id		,
			pet_id			,
			date			,
			weight			,
			disease			,
			next_date		 
		from vaccines 
        where pet_id  = _pet_id;

END //

delimiter //
create procedure  `user_Login`
(
	_user_name		varchar(8),
    _password		varchar(8)
)
begin
 select 	dni				 ,
			user_name		 ,
			password		 ,
			type			 ,
			status			
		from users
        where user_name = _user_name
			and password = _password;
END //



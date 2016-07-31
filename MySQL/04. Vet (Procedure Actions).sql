
use vet;

drop procedure if exists `person_Action`;
drop procedure if exists `user_Action`;
drop procedure if exists `vaccine_Action`;
drop procedure if exists `pet_Action`;

delimiter //
create procedure  `person_Action`
(
	_dni			char(8),
	_first_name		varchar(50),
	_last_name		varchar(50),	
	_email			varchar(50),
	_address		varchar(80),
	_phone_number 	varchar(12),
	_birth_date		varchar(20),
	_status			bit
)
begin
	if not exists(select dni from persons where dni = _dni ) then
		insert into persons 
			values(	_dni,
					_first_name,
                    _last_name,
                    _email,
                    _address, 
                    _phone_number,
                    _birth_date,
                    _status);
	ELSE
		if(_status = 1) Then
			update persons
				set first_name 	= 	_first_name,
					last_name 	= 	_last_name,
					email		=	_email,
					address		=	_address,
					phone_number=	_phone_number,
					birth_date	=	_birth_date,
					status		=	_status
			where dni = _dni;
		else	
			update persons
				set status		=	_status
			where dni = _dni;
        end if;
    end if;		
END //

delimiter //
create procedure  `user_Action`
(
	_dni			char(8),
    _user_name		varchar(8),
    _password		varchar(8),
    _type			char(1),
    _status			bit
)
begin	
	if not exists(select dni from users where dni = _dni ) then
		insert into users
			values (_dni,
					_user_name,
                    _password,
                    _type,
                    _status);
	else
		if(_status = 1) Then
			update users
				set user_name 	= 	_user_name,
					password	=	_password,
                    type		= 	_type,
                    status		= 	_status
			where	dni = _dni;
        Else
			update users
				set status		= 	_status
			where	dni = _dni;
        end if;
    
    end if;
END //



delimiter //
create procedure  `pet_Action`
(
	_pet_id			char(5),
	_dni			char(8),
	_pet_name		varchar(20),
	_breed			varchar(20),  /* Raza */
	_hair_color		varchar(20),  /* Color de pelo */
	_birth_date		varchar(20),
    _image			varchar(250),
	_status			bit

)
begin
	declare _newIdPets char(5);
	if not exists(select pet_id from pets where dni = _dni 
											and pet_id= _pet_id ) then
    
        set _newIdPets = (select pet_Max(_dni));
		insert into pets 
			values( _newIdPets,
					_dni,
                    _pet_name,
                    _breed,
                    _hair_color,
                    _birth_date,
                    _image,
                    _status);
	else	
		update pets
			set pet_name	= 	_pet_name,
				breed		=	_breed,
                hair_color	=	_hair_color,
                birth_date	=	_birth_date,
                image		= 	_image,
                status		=	_status
			where dni = _dni 
			and pet_id= _pet_id ;
	end if;
END //

 
delimiter //
create procedure  `vaccine_Action`
(
	_vaccine_id		int,	
	_pet_id			char(5),
	_date			varchar(20),
	_weight			varchar(10),
	_disease		varchar(20),
	_next_date		varchar(20)
)
begin
	declare _newIdVaccines int;
	if not exists(select vaccine_id from vaccines where vaccine_id = _vaccine_id 
											 and pet_id= _pet_id ) then
                    
            set _newIdVaccines =(select vaccine_Max(_pet_id));        
			insert into vaccines
					values(
							_newIdVaccines,
                            _pet_id,
                            _date,
                            _weight,
                            _disease,
                            _next_date
						);
	else
			update vaccines
				set date		= _date,
					weight		= _weight,
					disease		= _disease,
					next_date	= _next_date
				where vaccine_id = _vaccine_id 
					 and pet_id= _pet_id ;
    
    end if;
END //

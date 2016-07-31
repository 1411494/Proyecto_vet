
use vet;

drop function if exists `pet_Max`;
drop function if exists  `vaccine_Max`;

delimiter //
create function `pet_Max`( _dni char(8) )returns char(5)
begin
	declare maxPets int;
    declare newIdPets char(5);
    
	if Not Exists(select pet_id from pets where dni = _dni) Then
       set maxPets = 0;
	else
		set maxPets=  (select substring(max(pet_id), 2,4) from pets where dni = _dni);
	end if;
    
    set maxPets =maxPets + 1;
	set newIdPets = concat('P', LPAD(maxPets, 4,'0'));

	return newIdPets;
    
end //



delimiter //
create function `vaccine_Max`( _pet_id char(5) )returns int
begin
	declare maxVaccines int;
    declare newIdVaccines int;
    
	if Not Exists(select vaccine_id from vaccines where pet_id= _pet_id) Then
       set maxVaccines = 0;
	else
		set maxVaccines=  (select max(vaccine_id)  from vaccines where pet_id= _pet_id);
	end if;
    
    set maxVaccines =maxVaccines + 1;
	set newIdVaccines = maxVaccines ;

	return newIdVaccines;
    
end //

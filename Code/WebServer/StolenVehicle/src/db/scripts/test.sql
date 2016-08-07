use findmystolenvehicle;



select * from user;
select * from attachment;
select * from theft_information;
select * from vehicle;
select * from attachment;
delete from attachment;
delete from vehicle;
delete from theft_information;
delete from user;
select * from find_information;
select * from theft_information;
select * from vehicle;

update theft_information
set status = 'LOST';

select * from country;



select ti.id,ti.status,u.id,u.name, v.id,v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'LOST' and ti.vehicle_id = v.id and v.user_id = u.id and v.registrationNo  like '5110'
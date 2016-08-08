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
select * from vehicle where user_id = 'c49108ca-614a-4009-a662-6b07206407f9'
select * from attachment where vehicle_id = 'dd69eda5-0592-41a6-bdef-c664ff06fc34'

select * from attachment;

select ti.id,ti.status,u.id,u.name, v.id,v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'LOST' and ti.vehicle_id = v.id and v.user_id = u.id and v.registrationNo  like '5110'
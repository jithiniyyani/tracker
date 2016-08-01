use findmystolenvehicle;


select * from user;
select * from attachment;
select * from theft_information;
select * from vehicle;
delete from attachment;
delete from vehicle;
delete from theft_information;
delete from user;
select * from find_information;
select * from theft_information;
select * from vehicle;

update theft_information
set status = 'LOST';


select ti.id,u.name,u.id, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.id,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and ti.id = '8f06c43d-0947-4e6d-914a-607c1492eb8f';
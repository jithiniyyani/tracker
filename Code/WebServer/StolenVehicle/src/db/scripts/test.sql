use findmystolenvehicle;

select * from find_information;
select * from vehicle
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

select fi.id,fi.locators_name ,fi.find_location_cordinates,fi.locators_contactNumber,fi.locators_email,v.id,ti.id from user u, vehicle v,theft_information ti,find_information fi where v.user_id = '3e86fd6a-2006-407e-a384-7f8c39a7f104' and ti.vehicle_id = v.id and ti.status = 'lost' and ti.id = fi.theft_information_id;

select * from audit;
delete from user;

select fi.id,fi.locators_name ,fi.find_location_cordinates,fi.locators_contactNumber,fi.locators_email,v.id,ti.id from user u, vehicle v,theft_information ti,find_information fi where v.user_id = '5bdfd348-db05-4ef9-a2a9-0a87192c2245' and ti.vehicle_id = v.id and ti.status = 'LOST' and ti.id = fi.theft_information_id order by fi.status;

select * from user;
update user set activation_id = '24c151f1-13f7-4d65-9ba3-c459cfc44e23';
commit;
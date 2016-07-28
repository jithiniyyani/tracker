use findmystolenvehicle;

select * from user;
select * from vehicle;
select * from theft_information;
select * from attachment;
update user set status = 'ACTIVE' where activation_id = 'asdfasf'
select * from theft_information;
select * from attachment where vehicle_id = '';

select ti.id,u.name, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and ti.id = 'dcdc1db0-7029-411a-b4b1-b22f62f4dfb2'

select emailaddress from user where id = '';

delete from find_information;
select * from attachment;
select * from find_information;

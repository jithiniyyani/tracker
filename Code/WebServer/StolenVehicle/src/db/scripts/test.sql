use findmystolenvehicle;

/*Meta data*/
/*http://www.nationsonline.org/oneworld/country_code_list.htm*/
insert into country values('1','IND','India','config',now(),null,null);
insert into country values('2','MYS','Malaysia','config',now(),null,null);


select * from user where emailaddress = 'roger@gmail.com' and password  = 'test';

delete from user where id = '123';
insert into user values('123','roger','roger@gmail.com','roger','MALE','12312','9867','mumbai','airoli','ACTIVE','true','true','1','app',now(),null,null);



delete from vehicle;
delete from theft_information

select * from user;
select * from vehicle;
select * from theft_information;


select u.name, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and ti.id = '9f09a458-bd42-4807-9eb9-a36641547158';
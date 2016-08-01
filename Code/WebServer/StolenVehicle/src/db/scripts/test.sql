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

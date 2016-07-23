use findmystolenvehicle;

/*Meta data*/
/*http://www.nationsonline.org/oneworld/country_code_list.htm*/
insert into country values('1','IND','India','config',now(),null,null);
insert into country values('2','MYS','Malaysia','config',now(),null,null);


select * from user where emailaddress = 'roger@gmail.com' and password  = 'test';

delete from user where id = '123';
insert into user values('123','roger','roger@gmail.com','roger','MALE','12312','9867','mumbai','airoli','ACTIVE','true','true','1','app',now(),null,null);

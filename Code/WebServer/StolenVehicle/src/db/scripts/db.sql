drop database  if exists findMystolenVehicle;
create database findMystolenVehicle;
use findMystolenVehicle;

create table country(
 
   id varchar(255),  
   country_code char(3) not null,
   country varchar(255) not null,  
 
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,
   primary key (id),
   unique key (country_code)
   
);

/*most of the feilds are not null since we need data*/
create table user(
 
   id varchar(255) ,  
   name varchar(255) not null,
   emailaddress varchar(255) not null,  
   password varchar(255) not null,   
   gender varchar(10) not null,
   ic_passport varchar(255) not null, /*what does ic stand for */
   contactNumber varchar(255) not null,   
   city varchar(255) not null ,
   address  varchar(1024) not null,      
   status varchar(10),     /*values(active,disabled,deleted)*/
   email_notification varchar(10), /*This is to keep track if user wants email notification or not*/
   termsAndCondition varchar(10),  /*Possible values accepted/rejected*/
   country_id  varchar(255),
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,  
   primary key (id),
   unique key (emailaddress),
   foreign key (country_id) references country(id)
);

create table vehicle(
 
   id varchar(255),
   type varchar(255) not null, /*values('two wheeler,truck,..........')*/
   make varchar(255) not null,
   model varchar(255),
   year_of_make varchar(4),
   color varchar(255) not null, 
   registrationNo varchar(255) not null,
   extra_info varchar(1024),      /*This is for a note which user can enter as extra info*/
   stolen varchar(10),  /*values(true,false)*/
  
   user_id varchar(255),
   country_id  varchar(255),  /*Will help us track vehicle in country for status purpose*/
  
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,
   
   primary key (id),
   unique key (user_id,country_id,registrationNo), /*Accept unique cars for a country */
   foreign key (country_id) references country(id),
   foreign key (user_id) references user(id)
   
);

create table attachment(
 
   id varchar(255),
   attachment_name varchar(255),
   attachment_path varchar(255),
   vehicle_id varchar(255),
   find_information_id varchar(255),
   attachment_type varchar(10),  /*values('lost','found')*/
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,   
   primary key (id),
   foreign key (vehicle_id) references vehicle(id) 
   
);


create table theft_information(
 
   id varchar(255),
   theft_location_cordinates varchar(255) not null, /*This would lat/long of the vehicle's lost location*/
   vehicle_id varchar(255), 
   country_id varchar(255),
   rewards varchar(255),
   theft_dateTime DATETIME not null, /*Date and time of theft*/
   status varchar(255), /*values (lost,found,rewarded)*/   
   find_information_id varchar(255),
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,      
   primary key (id),
   foreign key (vehicle_id) references vehicle(id) ,
   foreign key (country_id) references country(id)
   
);


create table find_information(
  
   id varchar(255),
   find_location_cordinates varchar(255),
   find_dateTime DATETIME,
   locators_name varchar(255),
   locators_email varchar(255),
   locators_contactNumber varchar(255),
   theft_information_id varchar(255),
   status varchar(255),/*values(registered,accpeted,declined) */
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,
    foreign key (theft_information_id) references theft_information(id)	   
);


/*Meta data*/
/*http://www.nationsonline.org/oneworld/country_code_list.htm*/
insert into country values('1','IND','India','config',now(),null,null);
insert into country values('2','MYS','Malaysia','config',now(),null,null);



/*Screen 2*/
select * from user where emailaddress = 'liji@gmail.com' and password  = 'password1';


/*Screen 4*/
/*We will hash the passwords dont worry for now*/
insert into user values('1223423423','liji','liji@gmail.com','password1','female','passportNumber','9867090753','19.118206, 72.911119','address as input if location is not available for some reason','active','true','true','1','app',now(),null,null);



/*Screen 5*/
insert into vehicle values('123123321','Car','Ferrari','Enzo','2010','red or have rgb value','MH035110','Please help me meeee','true','1223423423','1','app',now(),null,null);
insert into attachment values('img1' ,'carimage1.jpg','C:/somepather/where/images/arestore/','123123321','lost','app',now(),null,null);
insert into attachment values('img2' ,'carimage2.jpg','C:/somepather/where/images/arestore/','123123321','lost','app',now(),null,null);
insert into theft_information values('MY1666011','19.118233, 72.911123','123123321','1','300USD',now(),'lost','app',now(),null,null);


/*Screen 8*/
/*In the url we have passed theft_information id and get all the in form there*/

/*from the theft_id we get all the information*/

select u.name, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make
from theft_information ti,vehicle v,user u
where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id
and ti.id = 'MY1666011';

select a.attachment_name,a.attachment_path from theft_information ti,vehicle v,attachment a where
ti.vehicle_id = v.id and v.id = a.vehicle_id and a.attachment_type = 'lost' and ti.status = 'lost' and  ti.id = 'MY1666011';


/*screen 9 */
select * from theft_information ti, vehicle v
where ti.vehicle_id = v.id and v.registrationNo  like '%MH035110%' and ti.status = 'lost';


/*Screen 10*/
insert into vehicle values('123123322','Car','Ferrari','Enzo',null,'red or have rgb value','MH035114','Please help me meeee','true',null,'1','app',now(),null,null);
insert into theft_information values('MY1666012','19.118233, 72.911123','123123322','1',null,now(),'lost','app',now(),null,null);
insert into attachment values('img3' ,'carimage1.jpg','C:/somepather/where/images/arestore/','123123322','lost','app',now(),null,null);
insert into attachment values('img4' ,'carimage2.jpg','C:/somepather/where/images/arestore/','123123322','lost','app',now(),null,null);
insert into find_information values('fd00002','19.118233, 72.911123',now(),'roger','roger@gmail.com','9867090751','MY1666012','registerd','app',now(),null,null);



/****/


/*Screen 11*/
insert into attachment values('12321c' ,'carimage2.jpg','C:/somepather/where/images/arestore/','123123321','found','app',now(),null,null);
insert into find_information values('fd00001','19.118233, 72.911123',now(),'ani','ani@gmail.com','9867090751','MY1666011','registerd','app',now(),null,null);


insert into attachment values('12321d' ,'carimage4.jpg','C:/somepather/where/images/arestore/','123123321','found','app',now(),null,null);
insert into find_information values('fd00003','19.118233, 72.911123',now(),'rafa','rafa@gmail.com','9867090752','MY1666011','registerd','app',now(),null,null);

/*On this insert we also send notification to the owner*/

/*Screen 12*/
/*When user logs in we execute this query*/
select fi.id,fi.locators_name ,fi.find_location_cordinates,fi.locators_contactNumber
from user u, vehicle v,theft_information ti,find_information fi
where v.user_id = '1223423423' and ti.vehicle_id = v.id and ti.status = 'lost'
and ti.id = fi.theft_information_id;

select a.attachment_name,a.attachment_path from user u,theft_information ti,vehicle v,attachment a 
where v.user_id = '1223423423' and 
ti.vehicle_id = v.id and v.id = a.vehicle_id and a.attachment_type = 'found';

/*Screen 12 Claim */
update find_information
set status = 'accepted'
where id = 'fd00001';

update theft_information
set status = 'found'
where id = 'MY1666011';

update vehicle 
set stolen = 'false'
where id = '123123321';
/*we sent notifcation to finder*/


/*If he says reject screen 12*/
update find_information
set status = 'reject'
where id = 'fd00001';


/*Screen 13*/
update theft_information
set status = 'rewarded'
where id = 'MY1666011';


/*Screen 14*/
select * from theft_information ti, vehicle v
where ti.vehicle_id = v.id and v.registrationNo  like '%MH035110%';
  /*Check the status ti.status*/
  /*if status is found says 'this car has been found*/
    /*if status is  rewarded says 'this car has been lost and found and reward has been settled*/
    
   /*if no rows then message in point 2*/
   
   /*if status is lost then point 3*/
 
  
   

  

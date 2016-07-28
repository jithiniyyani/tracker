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
   activation_id varchar(255),
   status varchar(50),     /*values(active,disabled,deleted)*/
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


  

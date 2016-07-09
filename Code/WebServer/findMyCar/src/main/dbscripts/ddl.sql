drop database  if exists findmycar;
create database findmycar;

use findmycar;

create table country(
 
   id varchar(255),
   country_code char(3),
   country varchar(255),
 
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,
   
   primary key (id),
   unique key (country_code)
   
);

create table user(
 
   id varchar(255),
   name varchar(255),
   username varchar(255),
   password varchar(255),
   emailaddress varchar(255),  
   gender varchar(10),
   passport varchar(10),
   contactNumber varchar(255),
   termsAndConditions varchar(10),  
      
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,
   
   primary key (id)
   
);

create table car(
 
   id varchar(255),
   registrationNo varchar(255),
   manufacturer varchar(255),
   model varchar(255),
   color varchar(255),  
   stolen varchar(10),
   user_id varchar(255),
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,
   
   primary key (id)
   
);

create table attachments(
 
   id varchar(255),
   attachment_name varchar(255),
   attachment_path varchar(255),
   car_id varchar(255),
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,   
   primary key (id)
   
);

create table stolen_location(
 
   id varchar(255),
   location_cordinates varchar(255),
   car_id varchar(255),
   createdBy varchar(255),
   createdAt DATETIME,
   modifiedBy varchar(255),
   modifiedAt DATETIME,   
   primary key (id)
   
);
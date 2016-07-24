package com.stolenvehicle.constants;

public class Query {

	public static final String GET_USER_BY_EMAIL_ID = "select * from user where emailaddress = ? and password  = ?";
	public static final String SAVE_USER = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String SAVE_VEHICLE = "insert into vehicle values(?,?,?,?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String SAVE_THEFT = "insert into theft_information values(?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String GET_THEFT_INFO_BY_ID = "select ti.id,u.name, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and ti.id = ?";
	public static final String GET_THEFT_INFO_BY_REGISTRATION_NUMBER = "select ti.id,u.name, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and v.registrationNo  like ?";
}

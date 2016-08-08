package com.stolenvehicle.constants;

public class Query {

	public static final String GET_USER_BY_EMAIL_ID = "select * from user where emailaddress = ? and password  = ? and status = 'ACTIVE'";
	public static final String GET_EMAIL_BY_USER_ID = "select emailaddress from user where id = ?;";
	public static final String SAVE_USER = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String ACTIVATE_USER = "update user set status = 'ACTIVE',activation_id = null where activation_id = ?";
	public static final String RESET_USER_PASSWORD = "update user set status = 'PASSWORD_RESET',activation_id = ? where emailaddress = ?";
	public static final String SET_USER_PASSWORD = "update user set status = 'ACTIVE',activation_id = null,password = ? where emailaddress = ? and activation_id=?";
	public static final String SAVE_VEHICLE = "insert into vehicle values(?,?,?,?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String SAVE_THEFT = "insert into theft_information values(?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String GET_THEFT_INFO_BY_ID = "select ti.id,ti.status,u.name,u.id, v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.id,v.type,v.make,v.model,v.year_of_make from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and ti.id = ?";
	public static final String GET_VEHICLE_ATTACHMENTS = "select * from attachment where vehicle_id = ?";
	public static final String GET_THEFT_INFO_BY_REGISTRATION_NUMBER = "select ti.id,ti.status,ti.theft_dateTime,ti.theft_location_cordinates,ti.rewards,u.id,u.name,v.id,v.registrationNo,v.type,v.make,v.model,v.year_of_make,v.color from theft_information ti,vehicle v,user u where ti.status = 'lost' and ti.vehicle_id = v.id and v.user_id = u.id and v.registrationNo  like ?";
	public static final String UPDATE_THEFT_INFO_STATUS = "update theft_information set status = ?,find_information_id = ? where id = ?;";
	public static final String SAVE_ATTACHMENT = "insert into attachment values(?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String SAVE_FIND_INFORMATION = "insert into find_information values(?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String GET_FIND_INFOLIST_BY_USER_ID = "select fi.id,fi.locators_name ,fi.find_location_cordinates,fi.locators_contactNumber,v.id,ti.id from user u, vehicle v,theft_information ti,find_information fi where v.user_id = ? and ti.vehicle_id = v.id and ti.status = 'lost' and ti.id = fi.theft_information_id;";
	public static final String UPDATE_FIND_INFORMATION_STATUS_BY_ID = "update find_information set status = ? where id = ?;";
	public static final String GET_VEHICLE_BY_USER_ID = "select * from vehicle where user_id = ?;";
	public static final String GET_ATTACHMENTS_BY_VEHICLE_ID="select * from attachment where vehicle_id = ?;";
}

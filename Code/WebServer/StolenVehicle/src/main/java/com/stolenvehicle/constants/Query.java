package com.stolenvehicle.constants;

public class Query {

	public static final String GET_USER_BY_EMAIL_ID = "select * from user where emailaddress = ? and password  = ?";
	public static final String SAVE_USER = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),null,null);";
	public static final String SAVE_VEHICLE = "insert into vehicle values(?,?,?,?,?,?,?,?,?,?,?,?,now(),null,null);";
}

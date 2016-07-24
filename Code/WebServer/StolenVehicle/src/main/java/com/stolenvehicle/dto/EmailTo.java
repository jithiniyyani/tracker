package com.stolenvehicle.dto;

public class EmailTo {

	private String receipent;
	private String subject;
	private String message;

	public EmailTo(){
		
	}
	
	public EmailTo(String receipent,String subject, String message){
		this.receipent = receipent;
		this.subject = subject;
		this.message = message;
		
	}
	public String getReceipent() {
		return receipent;
	}

	public void setReceipent(String receipent) {
		this.receipent = receipent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

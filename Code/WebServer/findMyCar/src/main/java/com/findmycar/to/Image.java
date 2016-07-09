package com.findmycar.to;

public class Image {

	String imageName;

	String imageContent;
	
	public Image(){
		
	}
	
	public Image(String imageName,String imageContent){
		this.imageName = imageName;
		this.imageContent = imageContent;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageContent() {
		return imageContent;
	}

	public void setImageContent(String imageContent) {
		this.imageContent = imageContent;
	}
	
	

}

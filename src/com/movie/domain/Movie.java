package com.movie.domain;


public class Movie {

	private Integer id;
	private String name;
	private String published_year;
	private String type;
	private String vedioUrl;
	private String details;
	private String miniImageUrl;
	public String getMiniImageUrl() {
		return miniImageUrl;
	}
	public void setMiniImageUrl(String miniImageUrl) {
		this.miniImageUrl = miniImageUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublished_year() {
		return published_year;
	}
	public void setPublished_year(String publishedYear) {
		published_year = publishedYear;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVedioUrl() {
		return vedioUrl;
	}
	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}


}

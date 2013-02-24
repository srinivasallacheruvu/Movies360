package com.movie360.dto;

import java.io.Serializable;

public class Star_Videos  implements Serializable {private int id;
private String name;
private String thumbnailUrl;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getThumbnailUrl() {
	return thumbnailUrl;
}
public void setThumbnailUrl(String thumbnailUrl) {
	this.thumbnailUrl = thumbnailUrl;
}
}

package com.cms.core.models;

public class UserMetadata {
	private int userMetadataId;
	private String key;
	private String value;
	private String type;
	private int postId;

	public int getUserMetadataId() {
		return userMetadataId;
	}

	public void setUserMetadataId(int userMetadataId) {
		this.userMetadataId = userMetadataId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
}

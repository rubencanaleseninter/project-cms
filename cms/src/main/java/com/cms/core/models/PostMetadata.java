package com.cms.core.models;

public class PostMetadata {
	private int postMetadataId;
	private String key;
	private String value;
	private String type;
	private int postId;

	public int getPostMetadataId() {
		return postMetadataId;
	}

	public void setPostMetadataId(int postMetadataId) {
		this.postMetadataId = postMetadataId;
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

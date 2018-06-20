package com.udacity.gradle.builditbigger.backend;

public class Bean {

	private String data;

	Bean(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
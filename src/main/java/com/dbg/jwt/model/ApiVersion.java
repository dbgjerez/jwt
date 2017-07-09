package com.dbg.jwt.model;

public enum ApiVersion {

	V1("v1");

	private ApiVersion(String version) {
		this.version = version;
	}

	String version;

}

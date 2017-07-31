package com.dbg.jwt.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {

	private static final long serialVersionUID = 826155204304558335L;

	private String msg;

	public MessageDTO() {
		super();
	}

	public MessageDTO(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

package com.dbg.jwt.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO implements Serializable {

	private static final long serialVersionUID = 826155204304558335L;

	private String msg;

}

package com.dbg.jwt.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.dbg.jwt.service.LocalDateTimeService;

@Service
public class LocalDateTimeServiceImpl implements LocalDateTimeService {

	@Override
	public LocalDateTime now() {
		return LocalDateTime.now();
	}

	@Override
	public LocalDateTime plusSeconds(LocalDateTime init, Integer seconds) {
		return init.plus(seconds, ChronoUnit.SECONDS);
	}

}

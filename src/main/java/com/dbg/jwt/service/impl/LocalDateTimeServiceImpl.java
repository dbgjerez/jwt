package com.dbg.jwt.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.dbg.jwt.service.LocalDateTimeService;

@Service
public class LocalDateTimeServiceImpl implements LocalDateTimeService {

	private static final ZoneId DEFAULT_ZONEID = ZoneId.systemDefault();

	@Override
	public LocalDateTime now() {
		return LocalDateTime.now();
	}

	@Override
	public LocalDateTime plusSeconds(LocalDateTime init, Integer seconds) {
		return init.plus(seconds, ChronoUnit.SECONDS);
	}

	@Override
	public LocalDateTime toLocalDateTime(Date d) {
		return toLocalDateTime(d, DEFAULT_ZONEID);
	}

	@Override
	public LocalDateTime toLocalDateTime(Date d, ZoneId zoneId) {
		return LocalDateTime.ofInstant(d.toInstant(), zoneId);
	}

}

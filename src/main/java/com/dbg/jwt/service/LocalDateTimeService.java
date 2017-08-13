package com.dbg.jwt.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface LocalDateTimeService {

	LocalDateTime now();

	LocalDateTime plusSeconds(LocalDateTime init, Integer seconds);

	LocalDateTime toLocalDateTime(Date d);

	LocalDateTime toLocalDateTime(Date d, ZoneId zoneId);

}

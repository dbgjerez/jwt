package com.dbg.jwt.service;

import java.time.LocalDateTime;

public interface LocalDateTimeService {

	LocalDateTime now();

	LocalDateTime plusSeconds(LocalDateTime init, Integer seconds);

}

package com.dbg.jwt.mappers;

import com.dbg.jwt.dto.GenerateTokenDTO;

public interface TokenMapper {

	GenerateTokenDTO map(String token);

}

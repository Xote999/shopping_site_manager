package com.example.common.service;

import com.example.common.domain.dto.LoginDto;

public interface IAuthService {

    String login(LoginDto loginDto);
}

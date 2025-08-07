package com.xzit.mapper;

import com.xzit.model.Users;

import java.util.List;

public interface UsersMapper {
  Users login(Users user);
    void updateLastTime(Users user);
}

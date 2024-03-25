package com.ohgiraffers.project.user.model;

import com.ohgiraffers.project.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

    List<UserDTO> selectAllUsers();

    int registUser(UserDTO userDTO);

    int updateUser(int code, String password);
}

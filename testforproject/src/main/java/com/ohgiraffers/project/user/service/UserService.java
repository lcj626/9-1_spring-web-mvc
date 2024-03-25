package com.ohgiraffers.project.user.service;

import com.ohgiraffers.project.user.dto.UserDTO;
import com.ohgiraffers.project.user.model.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {


    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserDTO> selectAllUsers(){

        List<UserDTO> users = userDAO.selectAllUsers();

        if(Objects.isNull(users)){
            System.out.println("user가 없슴다");
        }
        return users;

    }



    public int registUser(UserDTO userDTO){

        int result = userDAO.registUser(userDTO);

        if(result <= 0){
            System.out.println("실패하였습니다.");
        }else{
            System.out.println("등록을 마쳤습니다.");
        }
        return result;
    }


    public int updateUser(int code, String password){
        int result = userDAO.updateUser(code, password);

        if(result <= 0){
            System.out.println("수정에 실패하였습니다.");
        }else{
            System.out.println("수정이 완료되었습니다.");
        }
        return result;
    }


}

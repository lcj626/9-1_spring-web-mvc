package com.ohgiraffers.project.user.controller;

import com.ohgiraffers.project.user.dto.UserDTO;
import com.ohgiraffers.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView selectAllUsers(ModelAndView mv){

        List<UserDTO> users = userService.selectAllUsers();

        if(Objects.isNull(users)){
            System.out.println("exception 이다 ");
        }
        mv.addObject("users", users);
        mv.setViewName("user/allUsers");

        return mv;

    }


    @GetMapping("/regist_user")
    public String registUser(){
        return "user/userRegist";
    }

    @PostMapping("/regist")
    public String registUserPage(UserDTO userDTO){
        userService.registUser(userDTO);

        return "redirect:/users";
    }


    @GetMapping("/update_user")
    public String updateUserView(){
        return "user/userUpdate";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam int code, String password){
        userService.updateUser(code, password);

        return "redirect:/users";
    }



}

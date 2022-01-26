package com.onlineshop.onlineshop.web.controller;

import com.onlineshop.onlineshop.model.exceptions.InvalidArgumentsException;
import com.onlineshop.onlineshop.model.exceptions.PasswordDoesntMatchException;
import com.onlineshop.onlineshop.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("register")
public class RegisterController {

      private final AuthService authService;

      public RegisterController(AuthService authService) {
            this.authService = authService;
      }
      @GetMapping
      public String getRegisterPage(@RequestParam(required = false) String error, Model model){
         if(error!=null && !error.isEmpty()){
             model.addAttribute("Errors",true);
             model.addAttribute("error",true);
         }
          return "register";
      }
      @PostMapping
      public String register(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String repeatPassword,
                             @RequestParam String name,
                             @RequestParam String surename){
                 try{
                     this.authService.register(username,password,repeatPassword,name,surename);
                     return "redirect:/login";
                 }
                 catch (PasswordDoesntMatchException | InvalidArgumentsException exception){
                     return "redirect:/register?error="+ exception.getMessage();
                 }
      }
}

package com.petronas.fetchtechexam.controller;

import com.petronas.fetchtechexam.model.User;
import com.petronas.fetchtechexam.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    // page login
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView dashboard(WebRequest request) {
        ModelAndView model = new ModelAndView();
        User user = userService.findById(1);
        logger.info("username " +user.getUsername());
        logger.info("passs " +user.getPassWord());
        //model.addObject("users", userService.findById(1));
        //model.setViewName("dashboard");
        logger.info("test");
        return model;
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(WebRequest request) {
        ModelAndView model = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("username "+username);
        //model.addObject("users", userService.findById(1));
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(WebRequest request) {

        ModelAndView model = new ModelAndView();
        // Get data from request
        String username = request.getParameter("username");
        String  password = request.getParameter("password");
        String  userId = request.getParameter("userid");
        String  companyId = request.getParameter("companyid");
        // end get data from request
        byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
        logger.info("username "+username);
        logger.info("password "+password);
        logger.info("hashedPassword "+new String(encodedBytes));

        User user = new User();
        user.setUsername(username);
        // Hash base64 encode to validate data
        user.setPassWord(new String(encodedBytes));

        try {
            user.setUserId(Integer.valueOf(userId));
            user.setCompanyId(Integer.valueOf(companyId));
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        boolean status = userService.isValidUser(user);
        if (status){
            //exits user on DB
            model.setViewName("home");
        }
        else {
            //not exits user on DB
            model.setViewName("access-denied");
        }
        logger.info("login process");
        return model;
    }}

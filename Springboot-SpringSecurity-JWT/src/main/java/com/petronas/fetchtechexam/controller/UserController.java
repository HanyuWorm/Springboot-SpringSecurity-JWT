package com.petronas.fetchtechexam.controller;

import com.petronas.fetchtechexam.config.Constant;
import com.petronas.fetchtechexam.model.CustomErrorType;
import com.petronas.fetchtechexam.model.User;
import com.petronas.fetchtechexam.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller

@RestController
@RequestMapping("/api")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    // -------------------Retrieve All Users---------------------------------------------
    @RequestMapping(value="/user-list", method=RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        logger.info("Fetching All User");
        CustomErrorType customErrorType = new CustomErrorType();
        List<User> lstUsers = userService.findAll();
        if (lstUsers.isEmpty()) {
            logger.info("Fetching User Empty");
            customErrorType.setErrorCode(Constant.MESSAGE.ERROR.getMessage());
            customErrorType.setErrorMessage(Constant.MESSAGE.RECORD_NOT_FOUND.getMessage());
            customErrorType.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
            // You may decide to return HttpStatus bad
        }
        logger.info(" All User"+lstUsers);
        logger.info("End Fetching All User");
        return new ResponseEntity<List<User>>(lstUsers, HttpStatus.OK);
    }

    // -------------------Save Users---------------------------------------------
    @RequestMapping(value="/user-save", method=RequestMethod.POST)
    public ResponseEntity<User> insertUser(User User) {
        //TODO

        // hardcode to test inser User
        User user = new User();
        user.setCompanyId(2);
        user.setUserId(2);
        byte[] encodedBytes = Base64.encodeBase64("Hung@@12345".getBytes());
        user.setPassWord(new String(encodedBytes));
        logger.info("Start Creating User : {}");
        try {
            userService.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        logger.info("Creating User : {}", user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    // -------------------END Save Users---------------------------------------------

}

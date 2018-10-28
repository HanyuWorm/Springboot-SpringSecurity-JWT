package com.petronas.fetchtechexam.controller;

import com.petronas.fetchtechexam.Util.Util;
import com.petronas.fetchtechexam.config.Constant;
import com.petronas.fetchtechexam.model.ResponseToken;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class TokenController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    // -------------------Generate TOKEM JWT---------------------------------------------
    @RequestMapping(value="/gettoken", method=RequestMethod.POST)
    public ResponseEntity<?> createComment(@RequestParam(value="companyid", required=true) int companyId,
                                           @RequestParam(value="password", required=true) String password,
                                           @RequestParam(value="username", required=true) String username) {

        logger.info("START checking gennerate token ");
        // create object return
        ResponseToken responseToken = new ResponseToken();
        // Encode base64 password to validate User
        byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
        // put User to validate
        User user = new User();
        user.setUsername(username);
        user.setPassWord(new String(encodedBytes));
        user.setCompanyId(companyId);

        boolean status = userService.isValidUserRequire(user);
        if (status){
            logger.info("START  gennerate token : ");
            String JWT = Util.creatJWT(username);
            responseToken.setJWTMessage(JWT);
            responseToken.setMessage(Constant.MESSAGE.SUCCESS.getMessage());
            responseToken.setStatus(HttpStatus.OK);
            logger.info("END gennerate gennerate token : "+JWT);
            return new ResponseEntity<Object>(responseToken, new HttpHeaders(), HttpStatus.OK);
        }
        else{
            logger.info("ERROR  gennerate token :");
            responseToken.setJWTMessage(Constant.MESSAGE.JWT_NOT_FOUND.getMessage());
            responseToken.setMessage(Constant.MESSAGE.ERROR.getMessage());
            responseToken.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Object>(responseToken, new HttpHeaders(), HttpStatus.OK);
        }

    }
    // -------------------END Generate TOKEM JWT---------------------------------------------
}

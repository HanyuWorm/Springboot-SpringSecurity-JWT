package com.petronas.fetchtechexam.Util;

import com.petronas.fetchtechexam.config.Constant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {
    final static String DATE_FORMAT = "yyyy-MM-dd";
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    public static String creatJWT(String username){
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return JWT;
    }
    public static boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static List<String> validInput(int id, String comment, String date){
        List<String> messsges = new ArrayList<>();

        if (id==0){
            messsges.add(Constant.MESSAGE.MISSSING_ID.getMessage());
        }
        if (comment==null ||"".equals(comment)){
            messsges.add(Constant.MESSAGE.MISSSING_COMMENT.getMessage());
        }
        if (date==null ||"".equals(date)){
            messsges.add(Constant.MESSAGE.MISSSING_DATE.getMessage());
        }else {
            boolean validateDate = Util.isDateValid(date);
            if (!validateDate){
                messsges.add(Constant.MESSAGE.FORMAT_DATE.getMessage());
            }
        }
        if ((id==0)||(null==comment)||(null==date)){
            messsges.add(Constant.MESSAGE.FIELD_MISSING.getMessage());
        }
        return messsges;
    }
}

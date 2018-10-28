package com.petronas.fetchtechexam.controller;

import com.petronas.fetchtechexam.Util.Util;
import com.petronas.fetchtechexam.config.Constant;
import com.petronas.fetchtechexam.model.Comment;
import com.petronas.fetchtechexam.model.CustomErrorType;
import com.petronas.fetchtechexam.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private CommentService commentService;

    // -------------------Retrieve All Comment---------------------------------------------
    @RequestMapping(value="/comment-list", method=RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        logger.info("START Fetching All comment");
        CustomErrorType customErrorType = new CustomErrorType();
        List<Comment> lstComments = commentService.findAll();
        if (lstComments.isEmpty()) {
            customErrorType.setStatus(HttpStatus.OK);
            customErrorType.setErrorCode(Constant.MESSAGE.SUCCESS.getMessage());
            customErrorType.setErrorMessage(Constant.MESSAGE.RECORD_NOT_FOUND.getMessage());
            return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
        }
        logger.info(" All comment"+lstComments.toString());
        logger.info("END Fetching All comment");
        customErrorType.setStatus(HttpStatus.OK);
        customErrorType.setErrorCode(Constant.MESSAGE.SUCCESS.getMessage());
        customErrorType.setErrorMessage(Constant.MESSAGE.SUCCESS.getMessage());
        return new ResponseEntity<Object>(lstComments,new HttpHeaders(), HttpStatus.OK);
    }
    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        logger.info("START Fetching Comment with id {}", id);
        CustomErrorType customErrorType = new CustomErrorType();
        try {
            Comment comment = commentService.findById(id);
            if (null == comment ) {
                logger.error("Comment with not found.", id);
                customErrorType.setErrorMessage(Constant.MESSAGE.COMMENT_ID_NOT_FOUND.getMessage());
                customErrorType.setStatus(HttpStatus.NOT_FOUND);
                customErrorType.setErrorCode(Constant.MESSAGE.ERROR.getMessage());
                return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
            }else{
                logger.info("END Fetching Comment with id {}", id);
                return new ResponseEntity<Comment>(comment, HttpStatus.OK);
            }
        }
        catch (Exception ex){
            logger.error("Exception with id {} not found.", id);
            customErrorType.setErrorMessage(Constant.MESSAGE.COMMENT_ID_NOT_FOUND.getMessage());
            customErrorType.setStatus(HttpStatus.NOT_FOUND);
            customErrorType.setErrorCode(Constant.MESSAGE.ERROR.getMessage());
            return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
        }

    }

    // -------------------Create a User-------------------------------------------
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResponseEntity<?> createComment(@RequestParam(value="id", required=true) int id,
                                        @RequestParam(value="comment", required=true) String comment,
                                        @RequestParam(value="date", required=true) String date) {

        logger.info("START Creating Comment : {}", comment);
        CustomErrorType customErrorType = new CustomErrorType();
        // Validate exit comment
        if (commentService.isCommentExist(id)) {
            logger.error("Unable to create. A comment with name {} already exist", commentService.findById(id));
            customErrorType.setErrorCode(Constant.MESSAGE.ERROR.getMessage());
            customErrorType.setErrorMessage(Constant.MESSAGE.COMMENT_EXITS.getMessage());
            customErrorType.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);

        }else{
            // prepair data to commit
            Comment commentObj = new Comment();
            List<String> validInput = Util.validInput(id,comment,date);
            if (validInput.size()==0){
                try {
                    commentObj.setId(id);
                    commentObj.setComment(comment);
                    Date dateTime = formatter.parse(date);
                    commentObj.setDate(dateTime);
                    commentService.save(commentObj);
                    customErrorType.setStatus(HttpStatus.OK);
                    customErrorType.setErrorCode(Constant.MESSAGE.SUCCESS.getMessage());
                    customErrorType.setErrorMessage(Constant.MESSAGE.SUCCESS.getMessage());
                }catch (Exception ex){
                    ex.printStackTrace();
                    logger.error("Unable to create. Please Validate Input");
                    customErrorType.setStatus(HttpStatus.BAD_REQUEST);
                    customErrorType.setErrorCode(Constant.MESSAGE.ERROR.getMessage());
                    customErrorType.setErrorMessage(ex.getMessage());
                    return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
                }
            }
            else{
                customErrorType.setStatus(HttpStatus.BAD_REQUEST);
                customErrorType.setErrorCode(Constant.MESSAGE.ERROR.getMessage());
                customErrorType.setErrorMessage(validInput.toString());
                return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
            }

            logger.info("END Creating Comment : {}", comment);
            return new ResponseEntity<Object>(customErrorType, new HttpHeaders(), HttpStatus.OK);
        }



    }


}

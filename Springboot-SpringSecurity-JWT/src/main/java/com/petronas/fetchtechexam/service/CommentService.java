package com.petronas.fetchtechexam.service;

import com.petronas.fetchtechexam.dao.CommentDAO;
import com.petronas.fetchtechexam.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;
    public List<Comment> findAll() {
        return commentDAO.findAll();
    }
    public Comment findById(final int id) {
        return commentDAO.findById(id);
    }
    public void save(final Comment comment) {
        commentDAO.save(comment);
    }
    public void update(final Comment comment) {

        commentDAO.update(comment);
    }
    public void delete(final int id) {
        Comment comment = commentDAO.findById(id);
        if (comment != null) {
            commentDAO.delete(comment);
        }
    }
    public boolean isCommentExist(int id) {
        return findById(id)!=null;
    }
}

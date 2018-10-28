package com.petronas.fetchtechexam.dao;

import com.petronas.fetchtechexam.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "CommentDAO")
@Transactional(rollbackFor = Exception.class)
public class CommentDAO {
    @Autowired
    private SessionFactory sessionFactory;
    public void save(final Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(comment);
    }
    public void update(final Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(comment);
    }
    public Comment  findById(final int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Comment.class, id);
    }
    public void delete(final Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(comment);
    }
    public List<Comment> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Comment> lstComment = session.createSQLQuery("SELECT * FROM comment").list();
        //List<User> lstUser = session.createQuery("SELECT * FROM User", User.class).getResultList();
        //return session.createQuery("FROM Customer", Customer.class).getResultList();
        return lstComment;
    }
}

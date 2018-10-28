package com.petronas.fetchtechexam.dao;

import com.petronas.fetchtechexam.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "userDAO")
@Transactional(rollbackFor = Exception.class)

public class UserDAO {
    public static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    @Autowired
    private SessionFactory sessionFactory;
    public void save(final User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }
    public void update(final User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }
    public User findById(final int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
    public void delete(final User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(user);
    }
    public List<User> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> lstUser2 = session.createSQLQuery("SELECT * FROM user").list();
        //List<User> lstUser = session.createQuery("SELECT * FROM User", User.class).getResultList();
        return lstUser2;
    }
    public boolean isValidUser(User user){
        logger.info("Checking the user in the database");
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from User s where s.username = :username and s.passWord =:password and s.companyId =:companyid and s.userId=:userid";
        List<User> result = session.createQuery(hql)
                .setString("username", user.getUsername())
                .setString("password", user.getPassWord())
                .setString("companyid", String.valueOf(user.getCompanyId()))
                .setString("userid", String.valueOf(user.getUserId()))
                .list();

        if (result.size()>=1){
            return true;
        }else {
            logger.info(" user not exist in the database");
            return false;
        }

    }
    public boolean isValidUserRequire(User user){
        logger.info("Checking the user in the database");
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from User s where s.username = :username and s.passWord =:password and s.companyId =:companyid";
        List<User> result = session.createQuery(hql)
                .setString("username", user.getUsername())
                .setString("password", user.getPassWord())
                .setString("companyid", String.valueOf(user.getCompanyId()))
                .list();

        if (result.size()>=1){
            return true;
        }else {
            logger.info(" user not exist in the database");
            return false;
        }

    }
}

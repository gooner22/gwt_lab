package com.mhontar.gwt.model.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.mhontar.gwt.model.IUserDao;
import com.mhontar.gwt.model.User;
import java.util.List;

import java.sql.SQLException;

/**
 * Created by Maks on 15.02.14.
 */
public class UserDao extends BaseDaoImpl<User, String> implements IUserDao {

    public UserDao(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public User login(User user) throws SQLException {
        User u = getUser(user.getUsername());
        if(u.getPwd().equals(user.getPwd())){
            return u;
        }
        return null;
    }
    @Override
    public User createUser(User user) throws SQLException{
        User userDB = this.createIfNotExists(user);
        if(!userDB.getPwd().equals(user.getPwd())){
            userDB.setPwd(user.getPwd());
            this.update(userDB);
        }
        return userDB;

    }
    @Override
    public User getUser(String username) throws SQLException{
        return this.queryForId(username);
    }
}

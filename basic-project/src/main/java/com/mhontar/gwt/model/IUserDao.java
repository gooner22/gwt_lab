package com.mhontar.gwt.model;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maks on 15.02.14.
 */
public interface IUserDao extends Dao <User, String> {
    public User login(User user) throws SQLException;
    public User getUser(String username) throws SQLException;
    public User createUser(User user) throws SQLException;
}

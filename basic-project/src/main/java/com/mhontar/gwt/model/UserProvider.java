package com.mhontar.gwt.model;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mhontar.gwt.model.impl.UserDao;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * Created by Maks on 15.02.14.
 */
public class UserProvider {

    private IUserDao iUserDao;

    public UserProvider() {
        // h2 by default but change to match your database
        final String databaseUrl = "jdbc:h2:mem:account";
        try {
            final JdbcConnectionSource connectionSource =
                    new JdbcConnectionSource(databaseUrl);
            this.iUserDao = new UserDao(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            initTestUser();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User createUser(String username, String pwd) throws SQLException{
        return iUserDao.createUser(getHashedUser(username, pwd));
    }

    public User login(String username, String pwd) throws SQLException{
        return iUserDao.login(getHashedUser(username, pwd));
    }
    public User getUser(String username) throws SQLException{
        return iUserDao.getUser(username);
    }

    public static User getHashedUser(String username, String pwd){
        return new User(username, getMd5String(pwd));
    }

    private void initTestUser(){
        try {
            createUser("vasia","123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getMd5String(String pwd) {
        return DigestUtils.md5Hex(pwd);
    }
}

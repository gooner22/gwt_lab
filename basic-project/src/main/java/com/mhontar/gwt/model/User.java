package com.mhontar.gwt.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.mhontar.gwt.model.impl.UserDao;

/**
 * Created by Maks on 15.02.14.
 */
@DatabaseTable(daoClass = UserDao.class)
public class User implements IsSerializable {


    @DatabaseField(id = true)
    String username;
    @DatabaseField
    String pwd;

    public User() {

    }
    public User(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

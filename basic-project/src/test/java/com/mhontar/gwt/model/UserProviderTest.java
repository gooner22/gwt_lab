package com.mhontar.gwt.model;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Maks on 15.02.14.
 */
public class UserProviderTest extends TestCase {

    public static final String USERNAME = "vasia";
    public static final String PWD = "petia";
    public static final User USER = new User(USERNAME, PWD);
    UserProvider userProvider = new UserProvider();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateUser() throws Exception {
        User user = userProvider.createUser(USER.getUsername(), USER.getPwd());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getPwd(), UserProvider.getMd5String(USER.getPwd()));
    }
    public void testCreateUser1() throws Exception {
        User user = userProvider.createUser(USER.getUsername(), USER.getPwd());
        Assert.assertNotNull(user);
    }

    public void testCreateUser2() throws Exception {
        User user = userProvider.getUser(USER.getUsername());
        Assert.assertNotNull(user);
    }
    public void testCreateUser3() throws Exception {
        User user = userProvider.getUser(USER.getPwd());
        Assert.assertNull(user);
    }
    public void testCreateUser4() throws Exception {
        User user1 = userProvider.createUser(USER.getUsername(), USER.getPwd());
        User user = userProvider.login(USER.getUsername(), USER.getPwd());
        Assert.assertNotNull(user);
    }
    public void testCreateUser5() throws Exception {
        User user = new User(USER.getUsername(),"wrongPwd");
        Assert.assertNull(userProvider.login(user.getUsername(), user.getPwd()));
    }
    public void testGetHashedUser() throws Exception {
        User user = UserProvider.getHashedUser(USER.getUsername(), USER.getPwd());
        Assert.assertEquals(user.getUsername(), USER.getUsername());
        Assert.assertEquals(user.getPwd(), UserProvider.getMd5String(USER.getPwd()));
    }
}

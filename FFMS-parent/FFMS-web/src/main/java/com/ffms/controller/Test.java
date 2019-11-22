package com.ffms.controller;

import com.ffms.dao.ResponseDao;
import com.ffms.dao.UserDao;
import com.ffms.domain.User;

public class Test {
    public static void main(String[] args) {
        User userFlag = new UserDao().login("sa", "sa");
        System.out.println(userFlag);
        System.out.println(new ResponseDao().selectLastTrans());
    }
}

package com.ffms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ffms.domain.User;
import com.ffms.service.UserService;
import com.ffms.utils.DBConnection;


/**
 * @author zc
 *UserDao.java
 * 2019年10月17日 下午2:51:24
 */
public class UserDao implements UserService {
	private Connection conn;
  public UserDao() {
	conn= new DBConnection().getConnection();
	
  }
/* 验证用户登陆
 * @see com.ffms.service.UserService#login(java.lang.String, java.lang.String)
 */
@Override
public User login(String name ,String password ) {
	 String sql="select * from tb_user  where name=? and password=?";
	 User user=new User();
	 try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setPassword(rs.getString(3));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return user;
}


// public static void main(String[] args) {
//	System.out.println(new UserDao().login("asd", "123"));
//}
}

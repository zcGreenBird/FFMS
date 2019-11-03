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
 * UserDao.java
 * 2019年10月17日 下午2:51:24eudic
 */
public class UserDao implements UserService {
    private Connection conn;

    public UserDao() {
        conn = new DBConnection().getConnection();

    }

    /* 验证用户登陆
     * @see com.ffms.service.UserService#login(java.lang.String, java.lang.String)
     */
    @Override
    public User login(String name, String password) {
        String sql = "select * from tb_user where name=? and password=?";
        User user = new User();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User selectUserByUserName(String userName) {
        User user = null;
        try {
            String sql = "select * from tb_user where user_name = ?";
            PreparedStatement pStmt = (PreparedStatement) conn.prepareStatement(sql);
            pStmt.setString(1, userName);
            ResultSet rs = pStmt.executeQuery();//ִ执行sql语句
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public boolean insertUser(int id, String name, String password) throws SQLException {
        try {
            String sql = "insert into tb_user(id,name,password)values(?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.setString(2, name);
            pStmt.setString(3, password);
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateUser(int id, String name, String password) throws Exception {
        try {
            String sql = "update tb_user set name=?,password=? where id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, password);
            pStmt.setInt(3, id);
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


// public static void main(String[] args) {
//	System.out.println(new UserDao().login("asd", "123"));
//}
}

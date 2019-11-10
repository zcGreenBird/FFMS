package com.ffms.dao;/*
  @auther WJW129
  @date 2019/11/10 - 14:15
*/
import com.ffms.domain.vo.*;
import com.ffms.utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResponseDao {
    private Connection conn;
    public ResponseDao(){
        conn = new DBConnection().getConnection();
    }

    /**
     * 查询所有分类
     * @return List<CategoryResponse></>
     */
    public List<CategoryResponse> selectCategory(){
        String sql = "select id,name from tb_category";
        List<CategoryResponse> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
               CategoryResponse  cr = null;
               cr.setId(rs.getInt(1));
               cr.setName(rs.getString(2));
               list.add(cr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

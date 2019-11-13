package com.ffms.dao;/*
  @auther WJW129
  @date 2019/11/10 - 14:15
*/
import com.ffms.domain.vo.*;
import com.ffms.domain.*;
import com.ffms.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    /**
     * 分类信息
     * @param user
     * @return
     */
    public List<CategoryResponse> ClassifiedInformation(User user) {
        List<CategoryResponse> classList = new ArrayList();
        int familyId = user.getFamilyId();
        String sql ="SELECT  id ,category_id,consumer_name,consumer_time,consume_amount,trading_party,type,remarks,user_id,family_id FROM tb_consumer where consumer_time >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and family_id= ?";
        PreparedStatement pStmt = null;
        try {
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,familyId);
            ResultSet rs = pStmt.executeQuery();
            ResultSet total = pStmt.executeQuery();
            String [] name = {"食品","出行","生活","其他"};
            double totalPrice = 0; // 统计总金额
            while(total.next()){ // 统计总金额
                totalPrice += total.getDouble(5);
            }
            while(rs.next()) {
                CategoryResponse cate = new CategoryResponse();
                for (int i = 0; i < 4; i++){
                    cate.setId(i);// 设置id;
                    cate.setName(name[i]);// 设置分类名称
                    double price = 0;// 统计分类总金额
                    ResultSet num = pStmt.executeQuery();
                    while(num.next()){// 统计分类总金额
                        if(num.getInt(2) == i+1){
                            price += num.getDouble(5);
                        }
                    }
                    cate.setAllPrice(price);
                    cate.setAccount(price/totalPrice);
                    classList.add(cate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classList;

    }

    /**
     * 查询最近一次的消费记录
     */
    public List<LastTransResponse> selectLastTrans(){
        String sql ="select ";
        List<LastTransResponse> list = new ArrayList<>();
        return list;
    }

}
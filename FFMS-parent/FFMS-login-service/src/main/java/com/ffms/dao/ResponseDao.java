package com.ffms.dao;/*
  @auther WJW129
  @date 2019/11/10 - 14:15
*/
import com.ffms.domain.VO.*;
import com.ffms.domain.*;
import com.ffms.utils.DBConnection;

import java.sql.*;
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
    public List<LastTransResponse> selectLastTrans() {
        String sql ="SELECT user_id,consumer_name,consumer_time,consumer_amount FROM (SELECT user_id,consumer_name,consumer_time,consumer_amount FROM tb_consumer ORDER BY consumer_time DESC) c GROUP BY user_id;";
        List<LastTransResponse> list = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                LastTransResponse  ltr = null;
                ltr.setUserId(rs.getInt(1));
                ltr.setName(rs.getString(2));
                ltr.setTime(rs.getTime(3));
                ltr.setPrice(rs.getByte(4));
                list.add(ltr);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 查询三个月每个月的总消费
     */
    public List<MonthResponse> selectMonthConsume(){
        String sql = "SELECT MONTH(consumer_time) AS '月份',SUM(consumer_amount) AS '金额' FROM tb_consumer GROUP BY MONTH(consumer_time) DESC;";
        List<MonthResponse> list =null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i=1;
            while(rs.next()&&i<=3){
                MonthResponse mr = new MonthResponse();
                mr.setName(rs.getString(1));
                mr.setPrice(rs.getByte(2));
                list.add(mr);
                i++;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
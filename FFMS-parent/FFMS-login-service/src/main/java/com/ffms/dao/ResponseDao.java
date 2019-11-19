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
import java.util.ListIterator;

public class ResponseDao {
    private Connection conn;
    public ResponseDao(){
        conn = new DBConnection().getConnection();
    }

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
        List<LastTransResponse> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                LastTransResponse  ltr = new LastTransResponse();
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
        List<MonthResponse> list =new ArrayList<>();
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
    /**
     * 查询上
     * 个星期和这个星期的消费总额
     */
    public List<WeekResponse> selectWeekConsume(){
        String sql = "select DATE_FORMAT(consumer_time,'%w') as week,SUM(consumer_amount),consumer_time\n" +
                "from tb_consumer  WHERE consumer_time >= DATE_SUB( DATE_ADD(curdate(),interval -day(curdate())+1 day) , INTERVAL  1  WEEK )\n" +
                "GROUP BY DATE_FORMAT(consumer_time,'%w')  ORDER BY DATE_FORMAT(consumer_time,'%w')";
        List<WeekResponse> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            WeekResponse wr = new WeekResponse();
            while(rs.next()){
                if(rs.getInt(1)==0){
                    wr.setSunday(rs.getFloat(2)+"");
                }else if(rs.getInt(1)==1){
                    wr.setMonday(rs.getFloat(2)+"");
                }else if(rs.getInt(1)==2){
                    wr.setTuesday(rs.getFloat(2)+"");
                }else if(rs.getInt(1)==3){
                    wr.setWednesday(rs.getFloat(2)+"");
                }else if(rs.getInt(1)==4){
                    wr.setThursday(rs.getFloat(2)+"");
                }else if(rs.getInt(1)==5){
                    wr.setFriday(rs.getFloat(2)+"");
                }else if(rs.getInt(1)==6){
                    wr.setSaturday(rs.getFloat(2)+"");
                }
            }
            if(wr.getMonday()==null){
                wr.setMonday("0");
            }
            if(wr.getTuesday()==null){
                wr.setTuesday("0");
            }
            if(wr.getWednesday()==null){
                wr.setWednesday("0");
            }
            if(wr.getThursday()==null){
                wr.setThursday("0");
            }
            if(wr.getFriday()==null){
                wr.setFriday("0");
            }
            if(wr.getSaturday()==null){
                wr.setSaturday("0");
            }
            if(wr.getSunday()==null) {
                wr.setSunday("0");
            }
            list.add(wr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
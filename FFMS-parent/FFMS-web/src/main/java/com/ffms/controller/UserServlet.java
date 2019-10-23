package com.ffms.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ffms.dao.UserDao;
import com.ffms.domain.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String  type= request.getParameter("type");
		System.out.println(type);
		User user=new UserDao().login("asd", "123");
		System.out.println(user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
public static void main(String[] args) {
//	 DateTime date = new DateTime(new Date());
////
//	DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:ss:SS");
//    DateTime dateTime = dateTimeFormatter.parseDateTime("2019-10-20 20:50:09");
//     System.out.println(new UserServlet().isRentalOverdue(dateTime));
////   System.out.println(dateTime);
////String  dateTime1 = dateTime.toString("MM-dd");
////System.out.println(dateTime1);
//	

	
DateTime d1 = new DateTime(new DateTime());  
DateTime d2 = new DateTime("2012-05-01");  
  Integer i=1;
//和系统时间比  
boolean b1 = d1.isAfterNow();

boolean b2 = d1.isBeforeNow(); 
System.out.println(b2);
boolean b3 = d1.isEqualNow();  
  
//和其他日期比  
boolean f1 = d1.isAfter(d2);  
boolean f2 = d1.isBefore(d2);  
boolean f3 = d1.isEqual(d2); 

  }
}

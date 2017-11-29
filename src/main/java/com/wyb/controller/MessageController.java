package com.wyb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.HibernateSessionFactory;


@Controller
@RequestMapping(value = "Message")
public class MessageController {

	@RequestMapping(value = "GetMessagePage")
	public String toLogin(HttpServletRequest request,
			HttpServletResponse response) {

		return "123";

	}

	@RequestMapping(value = "getTimes")
	@ResponseBody
	public String getTime(HttpServletRequest request,
			HttpServletResponse response) {
		String cno = request.getParameter("cno");
		String cna = request.getParameter("cna");
		String cpno = request.getParameter("cpno");
		String cc = request.getParameter("cc");
		
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		String sql = "insert into course values('"+cno+"','"+cna+"','"+cpno+"','"+cc+"')";
		SQLQuery query = session.createSQLQuery(sql);
		int t = query.executeUpdate();
		
		tx.commit();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//

		String time = df.format(new Date());// new Date()
		return "Ìí¼Ó½á¹û£º" + t + "!";
	}
	
	@RequestMapping(value = "getCourse")
	@ResponseBody
	public String getCourse() {
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "select * from course";
		
		//session.createSQLQuery(sql).list(); 
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl =query.list();
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for(Object[] o :cl){
			
			String cno = o[0].toString();
			String cna = o[1].toString();
			String cpno = o[2].toString();
			String cc = o[3].toString();
			
			sb.append(isStart?"":",");
			isStart = false;
			sb.append("{");
			sb.append("\"cno\":\""+cno+"\",");
			sb.append("\"cna\":\""+cna+"\",");
			sb.append("\"cpno\":\""+cpno+"\",");
			sb.append("\"cc\":\""+cc+"\"");
			sb.append("}");
		}
		sb.append("]");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//

		String time = df.format(new Date());// new Date()
		return sb.toString();
	}
	
	@RequestMapping(value = "getStudent")
	@ResponseBody
	public String getStudent() {
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "select * from student";
		
		//session.createSQLQuery(sql).list(); 
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl =query.list();
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for(Object[] o :cl){
			
			String sno = o[0].toString();
			String cna = o[1].toString();
			String csex = o[2].toString();
			String sage = o[3].toString();
			String sdept = o[4].toString();
			
			sb.append(isStart?"":",");
			isStart = false;
			sb.append("{");
			sb.append("\"sno\":\""+sno+"\",");
			sb.append("\"sna\":\""+cna+"\",");
			sb.append("\"ssex\":\""+csex+"\",");
			sb.append("\"sage\":\""+sage+"\",");
			sb.append("\"sdept\":\""+sdept+"\"");
			sb.append("}");
		}
		sb.append("]");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//

		String time = df.format(new Date());// new Date()
		return sb.toString();
	}
	
	@RequestMapping(value = "getSC")
	@ResponseBody
	public String getSC() {
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "select * from sc";
		
		//session.createSQLQuery(sql).list(); 
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl =query.list();
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for(Object[] o :cl){
			
			String sno = o[0].toString();
			String cno = o[1].toString();
			String grade = o[2].toString();
			
			sb.append(isStart?"":",");
			isStart = false;
			sb.append("{");
			sb.append("\"sno\":\""+sno+"\",");
			sb.append("\"cno\":\""+cno+"\",");
			sb.append("\"grade\":\""+grade+"\"");
			sb.append("}");
		}
		sb.append("]");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//

		String time = df.format(new Date());// new Date()
		return sb.toString();
	}
}

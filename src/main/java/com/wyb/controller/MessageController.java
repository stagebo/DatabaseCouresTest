package com.wyb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.HibernateSessionFactory;

@Controller
@RequestMapping(value = "Message")
public class MessageController {

	@RequestMapping(value = "GetMessagePage")
	@ResponseBody
	public String toLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "Files\\";
		System.out.println(savePath);
		return "123";

	}

	@RequestMapping(value = "excuteSql")
	@ResponseBody
	public String excuteSql(HttpServletRequest request,
			HttpServletResponse response) {
		String sql = request.getParameter("sql");

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();

			SQLQuery query = session.createSQLQuery(sql);
			int t = query.executeUpdate();

			tx.commit();
			return t + "";
		} catch (Exception ex) {

			return "0";
		}
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
		String sql = "insert into course values('" + cno + "','" + cna + "','"
				+ cpno + "','" + cc + "')";
		SQLQuery query = session.createSQLQuery(sql);
		int t = query.executeUpdate();

		tx.commit();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//

		String time = df.format(new Date());// new Date()
		return t + "";
	}

	@RequestMapping(value = "getCourse")
	@ResponseBody
	public String getCourse() {
		Session session = HibernateSessionFactory.getSession();

		String sql = "select * from course";

		// session.createSQLQuery(sql).list();
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl = query.list();

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for (Object[] o : cl) {

			String cno = o[0].toString();
			String cna = o[1].toString();
			String cpno = o[2].toString();
			String cc = o[3].toString();

			sb.append(isStart ? "" : ",");
			isStart = false;
			sb.append("{");
			sb.append("\"cno\":\"" + cno + "\",");
			sb.append("\"cna\":\"" + cna + "\",");
			sb.append("\"cpno\":\"" + cpno + "\",");
			sb.append("\"cc\":\"" + cc + "\"");
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

		// session.createSQLQuery(sql).list();
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl = query.list();

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for (Object[] o : cl) {

			String sno = o[0].toString();
			String cna = o[1].toString();
			String csex = o[2].toString();
			String sage = o[3].toString();
			String sdept = o[4].toString();

			sb.append(isStart ? "" : ",");
			isStart = false;
			sb.append("{");
			sb.append("\"sno\":\"" + sno + "\",");
			sb.append("\"sna\":\"" + cna + "\",");
			sb.append("\"ssex\":\"" + csex + "\",");
			sb.append("\"sage\":\"" + sage + "\",");
			sb.append("\"sdept\":\"" + sdept + "\"");
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

		// session.createSQLQuery(sql).list();
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl = query.list();

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for (Object[] o : cl) {

			String sno = o[0].toString();
			String cno = o[1].toString();
			String grade = o[2].toString();

			sb.append(isStart ? "" : ",");
			isStart = false;
			sb.append("{");
			sb.append("\"sno\":\"" + sno + "\",");
			sb.append("\"cno\":\"" + cno + "\",");
			sb.append("\"grade\":\"" + grade + "\"");
			sb.append("}");
		}
		sb.append("]");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//

		String time = df.format(new Date());// new Date()
		return sb.toString();
	}

	@RequestMapping("springUpload")
	public String springUpload(HttpServletRequest request)
			throws IllegalStateException, IOException {
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "WEB-INF\\static\\imgFiles\\";

		String message = "上传成功";

		String uuid = UUID.randomUUID().toString();
		String fileName = savePath + uuid + ".jpg";
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					// 上传
					file.transferTo(new File(fileName));
					String sql = "insert into image values('static/imgFiles/"
							+ uuid + ".jpg')";

					try {
						Session session = HibernateSessionFactory.getSession();
						Transaction tx = session.beginTransaction();

						SQLQuery query = session.createSQLQuery(sql);
						int t = query.executeUpdate();

						tx.commit();

					} catch (Exception ex) {

						message = "存入数据库失败！";
					}
				}

			}

		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime)
				+ "ms");

		return "redirect:/index.jsp";
	}

	@RequestMapping(value = "getImgList")
	@ResponseBody
	public String getImgList() {
		Session session = HibernateSessionFactory.getSession();

		String sql = "select filename,filename as f1 from image";

		// session.createSQLQuery(sql).list();
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> cl = query.list();

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean isStart = true;
		for (Object[] o : cl) {
			String src = o[0].toString();

			sb.append(isStart ? "" : ",");
			isStart = false;
			sb.append("{");
			sb.append("\"src\":\"" + src + "\"");

			sb.append("}");
		}
		sb.append("]");
		return sb.toString();
	}

}

package com.jfs;

import java.util.Date;
import org.hibernate.Session;
import com.jfs.util.HibernateUtill;
import com.jfs.user.DBUser;

import java.util.List; 
import org.json.simple.JSONObject;
import java.util.Date;
import java.util.Iterator; 
import java.util.ArrayList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
//	public static void main(String[] args) {
//		System.out.println("Maven + Hibernate + Oracle");
//		Session session = HibernateUtill.getSessionFactory().openSession();
//
//		session.beginTransaction();
//		DBUser user = new DBUser();
//
//		user.setUserId(100);
//		user.setUsername("superman");
//		user.setCreatedBy("system");
//		user.setCreatedDate(new Date());
//
//		session.save(user);
//		session.getTransaction().commit();
//	}
	 
	public static String postUser(int uid, String name, String by){
		 System.out.println("Post Request");
		 Session session = HibernateUtill.getSessionFactory().openSession();
		 Transaction tx = null; 
		 
	      try {
	    	  tx = session.beginTransaction();
	    	  DBUser user = new DBUser();
	    	  user.setUserId(uid);
	    	  user.setUsername(name);
	    	  user.setCreatedBy(by);
	    	  user.setCreatedDate(new Date());
	    	  session.save(user);
	    	  tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return "Successfully added";
	   }
	 
	public static List<JSONObject> getUser( ){
		 System.out.println("Get Request");
		 Session session = HibernateUtill.getSessionFactory().openSession();
		 Transaction tx = null;
		 List<JSONObject> obj = new ArrayList<JSONObject>();
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		 
		 try {
	         tx = session.beginTransaction();
	         List users = session.createQuery("FROM DBUser").list(); 
	         for (Iterator iterator = users.iterator(); iterator.hasNext();){
	            DBUser user = (DBUser) iterator.next(); 
	            JSONObject x = new JSONObject();
	            x.put("id", Integer.toString(user.getUserId()));
	            x.put("name", user.getUsername());
	            x.put("by", user.getCreatedBy());
	            x.put("date", dateFormat.format(user.getCreatedDate()));
	            obj.add(x);
	            
	            System.out.print("UserId: " + user.getUserId()); 
	            System.out.print("  UserName: " + user.getUsername());
	            System.out.print("  CreatedBy: " + user.getCreatedBy());
	            System.out.println("  CreatedDate: " + user.getCreatedDate()); 
	         }
	         tx.commit();
	     } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	     } finally {
	    	 session.close(); 
	     }
		 System.out.println(obj);
		 return obj;
	   }
}
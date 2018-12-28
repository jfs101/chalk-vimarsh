package com.faceprep;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.faceprep.util.HibernateUtil;
import com.faceprep.game.Game;
public class App {
	static int gameId=1;
//	public static void main(String[] args) {
//	  JSONObject result1 = newGame("Max Payne 3", "Ubisoft");
//	  JSONObject result2 = newGame("Call of Duty: Black Ops 4", "Activision");
//	  JSONObject result3 = newGame("Fortnite", "Epic Games");
//	  System.out.print(result1 + " " + result2 + " " + result3); 
//	  JSONObject result4 = getGames();
//	  Integer i = new Integer(1);
//	  Integer j = new Integer (2);
//	  JSONObject result5 = updateGame(i, "Rockstar");
//	  JSONObject result6 = deleteGame(j);
//	  JSONObject result7 = getGames();
//	  System.out.print(result4 + " " + result5 + " " + result6 + " " + result7);
//	}
	/* Method to CREATE/ADD a Game record in the database */
	public static JSONObject newGame(String name, String by){
		System.out.println("\nPost Request\n");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null; 
  		JSONObject obj = new JSONObject();
		try {
   			obj.put("success", "false");
			tx = session.beginTransaction();
			Game game = new Game();
	 		game.setGameId(gameId);
		 	game.setGamename(name);
	 		game.setCreatedBy(by);
	 		game.setBoughtDate(new Date());
	 		session.save(game);
	 		tx.commit();
	 		gameId++;
   			obj.put("success", "true");
	 	} catch (HibernateException e) {
	 		if (tx!=null) tx.rollback();
	 		e.printStackTrace();
	 	} catch (JSONException e) {
	 		if (tx!=null) tx.rollback();
	 	 	e.printStackTrace();
	 	} finally {
	  		session.close(); 
	 	}
	 	return obj;
	}
	/* Method to READ/GET all the games */
	public static JSONObject getGames( ){
		 System.out.println("\nGet Request\n");
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 List<JSONObject> objs = new ArrayList<JSONObject>();
		 JSONObject obj = new JSONObject();
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		 try {
	   			obj.put("success", "false");
				tx = session.beginTransaction();
	   			List games = session.createQuery("FROM Game").list(); 
	   			for (Iterator iterator = games.iterator(); iterator.hasNext();){
	   				Game game = (Game) iterator.next(); 
	   				JSONObject x = new JSONObject();
	   				x.put("id", Integer.toString(game.getGameId()));
	   				x.put("name", game.getGamename());
	   				x.put("by", game.getCreatedBy());
	   				x.put("date", dateFormat.format(game.getBoughtDate()));
	   				objs.add(x);
	   	   			System.out.print("GameId: " + game.getGameId()); 
	   				System.out.print(" GameName: " + game.getGamename());
					System.out.print(" CreatedBy: " + game.getCreatedBy());
					System.out.println(" BoughtDate: " + game.getBoughtDate());
				}
	  	 		tx.commit();
	   			obj.put("success", "true");
	   			obj.put("result", objs);
	  		} catch (HibernateException e) {
	   			if (tx!=null) tx.rollback();
	   			e.printStackTrace(); 
	  		} catch (JSONException e) {
	 	 		if (tx!=null) tx.rollback();
	 	 		e.printStackTrace();
	  		} finally {
	 	 		session.close(); 
	  		}
		 	System.out.println(objs);
		 	return obj;
		}
	/* Method to UPDATE createdBy for a game */
	public static JSONObject updateGame(Integer gameID, String createdBy ){
		System.out.println("\nPut Request\n");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null; 
  		JSONObject obj = new JSONObject(); 
	 	try {
			obj.put("success", "false");
			tx = session.beginTransaction();
	  		Game game = (Game)session.get(Game.class, gameID); 
  			game.setCreatedBy(createdBy);
	  		session.update(game); 
	  		tx.commit();
   			obj.put("success", "true");
	 	} catch (HibernateException e) {
	 		if (tx!=null) tx.rollback();
	  		e.printStackTrace(); 
	 	} catch (JSONException e) {
	 	 	if (tx!=null) tx.rollback();
	 	 	e.printStackTrace();
	 	} finally {
	  		session.close(); 
	 	}
	 	return obj;
	}
	/* Method to DELETE a game from the records */
	public static JSONObject deleteGame(Integer GameID){
		System.out.println("\nDelete Request\n");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
  		JSONObject obj = new JSONObject();
		try {
   			obj.put("success", "false");
			tx = session.beginTransaction();
	  		Game game = (Game)session.get(Game.class, GameID); 
	  		session.delete(game); 
	  		tx.commit();
   			obj.put("success", "true");
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
	 	} catch (JSONException e) {
	 	 	if (tx!=null) tx.rollback();
	 	 	e.printStackTrace();
		} finally {
			session.close(); 
		}
		return obj;
	}
}
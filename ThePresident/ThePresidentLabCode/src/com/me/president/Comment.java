package com.me.president;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static com.me.president.ObjectifyDAO.ofy;

/**
 * @author manelayed
 * 
 */
@Entity
public class Comment implements Serializable{

	@Id
	Long id;
	@Index
	Date date;
	public String user, text;

	public Comment() {
	}

	public Comment(String text, String user) {
		this.user = user;
		this.text = text;
		this.date = new Date();
	}

	public static void store(String text, String user) {
		if (text!= null && !text.isEmpty() ) {
			ofy().save().entity(new Comment(text, user));
			MemcacheServiceFactory.getMemcacheService().delete("100Comments"); // flush cache
		}
	}

	public static List<Comment> retrieveAll() {
		System.out.println("retriveAll");
		return ofy().load().type(Comment.class).order("-date").limit(100).list();
	}

	public static List<Comment> retrieveAllCached() {
	    MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
	    List<Comment> comments = (ArrayList<Comment>)cache.get("100Comments");
	    if (comments == null) {
	        // read from Datastore
	        comments = retrieveAll();
	        // write to Memcache
	        cache.put("100Comments", new ArrayList<Comment>(comments));
	    }
	    return comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

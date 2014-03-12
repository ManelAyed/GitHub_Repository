package com.me.president;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class ObjectifyDAO {

	static { 
		ObjectifyService.register(Comment.class); 
		ObjectifyService.register(Counter.class);
		
	} // register all @Entity classes
    
	static Objectify ofy() { return ObjectifyService.ofy(); }  // proxy for Objectify
    
}

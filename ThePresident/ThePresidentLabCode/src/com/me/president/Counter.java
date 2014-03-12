package com.me.president;

import java.util.Random;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static com.me.president.ObjectifyDAO.ofy;

@Entity
public class Counter {
	@Id
	String key;
	@Index
	String name;
	@Index
	Long value;

	Counter() {
	} // Empty constructor

	Counter(String name, Integer shard_N, Long val) {
		this.name = name;
		this.value = val;
		key = name + "_shard" + shard_N.toString();
	}

	public static void increment(String name) {
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		Long val = cache.increment(name, 1); // atomic
		if (val == null) {
			val = Counter.read(name);
			Long init = (val == null) ? 0L : val;
			// atomic increment with initial value
			val = cache.increment(name, 1, init);
		}
		Counter.write(name, val);
	}

	public static Long value(String name) {
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		Long val = (Long) cache.get(name);
		if (val == null) {
			val = Counter.read(name);
			if (val != null) {
				cache.put(name, val);
			}
		}
		return val;
	}

	static Long read(String name) {
		Counter c = ofy().load().type(Counter.class).filter("name", name)
				.order("-value").first().get();
		return (c == null ? null : c.value);
	}

	static void write(String name, Long value) {
		Random r = new Random();
		Integer shard_N = r.nextInt(10);
		ofy().save().entity(new Counter(name, shard_N, value));
	}

}

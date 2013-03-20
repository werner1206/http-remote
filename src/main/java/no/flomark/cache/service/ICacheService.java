package no.flomark.cache.service;

import java.util.concurrent.ConcurrentHashMap;

import no.flomark.user.User;


/**
 * Cache Service Interface
 * 
 * @author  onlinetechvision.com
 * @since   10 Mar 2012
 * @version 1.0.0
 *
 */
public interface ICacheService {

	/**
	 * Get User Map
	 * 
	 * @return ConcurrentHashMap User Map
	 */
	public ConcurrentHashMap<Long, User> getUserMap();
	
}

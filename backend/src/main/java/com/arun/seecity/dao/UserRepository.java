/**
 * 
 */
package com.arun.seecity.dao;

import com.arun.seecity.model.User;

/**
 * Repository to handle all the transactions of the <code>User</code> entity.
 * 
 * @author Arun S P
 *
 */
public interface UserRepository extends BaseRepository<User> {

	/**
	 * Find a user by username.
	 * 
	 * @param username Username to match.
	 * @return Matched User. Null otherwise.
	 */
	User findOneByUsername(String username);
}

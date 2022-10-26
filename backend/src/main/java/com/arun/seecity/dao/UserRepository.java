/**
 * 
 */
package com.arun.seecity.dao;

import com.arun.seecity.model.User;

/**
 * @author arun
 *
 */
public interface UserRepository extends BaseRepository<User> {

	User findOneByUsername(String username);
}

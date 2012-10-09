/*
 * Copyright (C) 2012 Colleage of Software Engineering, Southeast University
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cn.edu.seu.cose.graduates.core.service;

import cn.edu.seu.cose.graduates.persistence.dao.DataAccessException;
import cn.edu.seu.cose.graduates.persistence.dao.UserDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.User;

/**
 *
 * @author zc <cottyard@gmail.com>
 * @author rAy <predator.ray@gmail.com>
 */
public class UserAccountService {
    
    private UserDataAccess userDao;
    
    public UserAccountService() {}
    
    public UserAccountService(UserDataAccess userDao) {
       this.userDao = userDao; 
    }
    
    public void setUserDataAccess(UserDataAccess userDao) {
        synchronized(userDao) {
            this.userDao = userDao;
        }
    }
    
    public void register(String username, String password)
            throws DataAccessException {
        userDao.createUser(username, password);
    }
    
    public void unregister(long userId) throws DataAccessException {
        userDao.deleteUser(userId);
    }
    
    public boolean changePassword(long userId, String oldPassword,
            String newPaasword)
            throws DataAccessException {
        User user = userDao.getUserById(userId);
        boolean verified = (user != null)
                && (user.getPassword().equals(oldPassword));
        if (!verified) {
            return false;
        }
        userDao.updatePassword(userId, newPaasword);
        return true;
    }
    
    public User verify(String username, String password)
            throws DataAccessException {
        User user = userDao.getUserByUsername(username);
        boolean verified = (user != null)
                && (user.getPassword().equals(password));
        return verified ? user : null;
    }
    
}

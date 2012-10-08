/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.seu.cose.graduates.core.service;

import cn.edu.seu.cose.graduates.persistence.dao.DataAccessException;
import cn.edu.seu.cose.graduates.persistence.dao.UserDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.User;

/**
 *
 * @author zc <cottyard@gmail.com>
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

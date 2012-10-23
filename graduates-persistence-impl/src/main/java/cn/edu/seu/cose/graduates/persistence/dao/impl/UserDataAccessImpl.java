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

package cn.edu.seu.cose.graduates.persistence.dao.impl;

import cn.edu.seu.cose.graduates.persistence.dao.DataAccessException;
import cn.edu.seu.cose.graduates.persistence.dao.UserDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.Schedule;
import cn.edu.seu.cose.graduates.persistence.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class UserDataAccessImpl extends AbstractDataAccess
        implements UserDataAccess {
    
    private static Logger logger = Logger.getLogger(
            UserDataAccessImpl.class.getName());
    
    public UserDataAccessImpl(DataSource dataSource) {
        super(dataSource);
    }

    public User getUserById(long userId) throws DataAccessException {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM grad_users WHERE user_id=?");
            PreparedStatement ps2 = connection.prepareStatement(
                    "SELECT * FROM grad_phase WHERE user_id=? ORDER BY phase_time");
            ps.setLong(1, userId);
            ps2.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            
            if (!rs.next()) {
                return null;
            }
            
            long id = rs.getLong("user_id");
            String name = rs.getString("user_name");
            String pass = rs.getString("user_pass");
            User user = new User();
            user.setId(id);
            user.setUsername(name);
            user.setPassword(pass);
            LinkedList<Long> list = new LinkedList<Long>();
            for (int i = 0; rs2.next(); i++){
                long time = rs2.getLong("phase_time");
                if(i == 0) {
                    list.add(time);
                } else {
                    list.add(time - list.peekLast());
                }
            }
            Schedule schedule = new Schedule();
            schedule.setDeltaTimeTable(list);
            user.setSchedule(schedule);

            return user;
        } catch(SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new DataAccessException(ex);
        }
    }

    public User getUserByUsername(String username) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User createUser(String username, String password) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSchedule(long userId, Schedule schedule) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateUsername(long userId, String username) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updatePassword(long userId, String password) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long deleteUser(long userId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

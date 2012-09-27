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

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class UserDataAccessImpl implements UserDataAccess {

    public User getUserById(long userId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User createUser(String username, String password)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSchedule(long userId, Schedule schedule)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateUsername(long userId, String username)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updatePassword(long userId, String password)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long deleteUser(long userId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

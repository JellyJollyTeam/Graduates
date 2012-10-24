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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
abstract class AbstractDataAccess {
    
    private static final Logger logger = Logger.getLogger(
            AbstractDataAccess.class.getName());
    
    private DataSource dateSource;
    
    public AbstractDataAccess(DataSource dateSource) {
        this.dateSource = dateSource;
    }
    
    protected Connection getConnection() throws DataAccessException {
        try {
            return dateSource.getConnection();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new DataAccessException(ex);
        }
    }
    
    protected JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dateSource);
    }

}

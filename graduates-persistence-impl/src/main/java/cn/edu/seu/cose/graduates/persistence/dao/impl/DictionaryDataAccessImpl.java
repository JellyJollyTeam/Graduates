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
import cn.edu.seu.cose.graduates.persistence.dao.DictionaryDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.Dictionary;
import cn.edu.seu.cose.graduates.persistence.model.Word;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class DictionaryDataAccessImpl extends AbstractDataAccess
        implements DictionaryDataAccess {
    
    public DictionaryDataAccessImpl(DataSource dataSource) {
        super(dataSource);
    }

    public Dictionary getDictionaryById(long dictId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long createDictionary(String dictName, List<Word> words)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateDictionnaryName(String name) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Word referTo(long dictId, String english) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteDictionary(long dictId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

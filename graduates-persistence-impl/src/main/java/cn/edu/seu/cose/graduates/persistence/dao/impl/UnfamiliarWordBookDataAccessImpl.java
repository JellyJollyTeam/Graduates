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
import cn.edu.seu.cose.graduates.persistence.dao.UnfamiliarWordBookDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.BookedWord;
import cn.edu.seu.cose.graduates.persistence.model.Word;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class UnfamiliarWordBookDataAccessImpl
        extends AbstractDataAccess implements UnfamiliarWordBookDataAccess {
    
    public UnfamiliarWordBookDataAccessImpl(DataSource dataSource) {
        super(dataSource);
    }

    public List<BookedWord> getBookedWordsByUserId(long userId)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updatePhase(long userId, long wordId, long phaseId)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Word> getWordsByUserId(long userId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addWord(long wordId, long userId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteWord(long wordId, long userId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

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
import cn.edu.seu.cose.graduates.persistence.dao.WordDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class WordDataAccessImpl extends AbstractDataAccess
        implements WordDataAccess {

    private static Logger logger = Logger.getLogger(
            UserDataAccessImpl.class.getName());

    public WordDataAccessImpl(DataSource dataSource) {
        super(dataSource);
    }

    public Word getWordById(long wordId) throws DataAccessException {
        Connection conn = getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from grad_words where word_id=?");
            ps.setLong(1, wordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Word word = new Word();
                word.setId(wordId);
                word.setEnglish(rs.getString("english"));
                word.setExplanation("description");
                return word;
            }
            return null;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new DataAccessException(ex);
        }
    }
    
    private static final String CREATE_WORD_STATEMENT =
            "INSERT INTO grad_words(english, explanation) VALUES (?, ?);";

    public long createWord(String english, String explanation)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateWord(Word word) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateWordExplanation(long wordId, String explanation)
            throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteWord(long wordId) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

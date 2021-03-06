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
package cn.edu.seu.cose.graduates.persistence.dao;

import cn.edu.seu.cose.graduates.persistence.model.Word;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public interface WordDataAccess {
    
    Word getWordById(long wordId) throws DataAccessException;
    
    long createWord(String english, String explanation) throws DataAccessException;
    
    void updateWord(Word word) throws DataAccessException;
    
    void updateWordExplanation(long wordId, String explanation)
            throws DataAccessException;
    
    void deleteWord(long wordId) throws DataAccessException;
    
}

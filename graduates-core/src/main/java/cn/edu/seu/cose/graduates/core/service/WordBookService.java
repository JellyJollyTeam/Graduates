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
import cn.edu.seu.cose.graduates.persistence.dao.FamiliarWordBookDataAccess;
import cn.edu.seu.cose.graduates.persistence.dao.UnfamiliarWordBookDataAccess;
import cn.edu.seu.cose.graduates.persistence.dao.UserDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.BookedWord;
import cn.edu.seu.cose.graduates.persistence.model.Schedule;
import cn.edu.seu.cose.graduates.persistence.model.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zc <cottyard@gmail.com>
 * @author rAy <predator.ray@gmail.com>
 */
public class WordBookService {
    
    private static final String DEMOTING_ERR_MSG = "Demoting a phase 0 word.";
    
    private static final Logger logger = Logger.getLogger(
            WordBookService.class.getName());
    
    private FamiliarWordBookDataAccess familiarWordBookdao;
    
    private UnfamiliarWordBookDataAccess unfamiliarWordBookDao;
    
    private UserDataAccess userDao;
    
    public WordBookService() {}
    
    public WordBookService(
            UnfamiliarWordBookDataAccess unfamiliarWorkBookDao,
            FamiliarWordBookDataAccess familiarWordBookdao,
            UserDataAccess userDao) {
        this.familiarWordBookdao = familiarWordBookdao;
        this.unfamiliarWordBookDao = unfamiliarWorkBookDao;
        this.userDao = userDao;
    }
    
    public void setFamiliarWordBookDataAccess(
            FamiliarWordBookDataAccess familiarWordBookdao) {
        synchronized(familiarWordBookdao) {
            this.familiarWordBookdao = familiarWordBookdao;
        }
    }
    
    public void setUnfamiliarWordBookDataAccess(
            UnfamiliarWordBookDataAccess unfamiliarWorkBookDao) {
        synchronized(unfamiliarWorkBookDao) {
            this.unfamiliarWordBookDao = unfamiliarWorkBookDao;
        }
    }
    
    public void markWordAsUnfamiliar(long wordId, long userId)
            throws DataAccessException {
        unfamiliarWordBookDao.addWord(wordId, userId);
    }
    
    public void markWordAsFamiliar(long wordId, long userId)
            throws DataAccessException {
        unfamiliarWordBookDao.deleteWord(wordId, userId);
        familiarWordBookdao.addWord(wordId, userId);
    }
    
    public void promoteWordPhase(long wordId, long userId)
            throws DataAccessException {
        User user = userDao.getUserById(userId);
        Schedule schedule = user.getSchedule();
        int maxPhase = schedule.getDeltaTimeTable().size();
        int lastPhase = maxPhase - 1;
        
        BookedWord word = unfamiliarWordBookDao.getBookedWord(userId, wordId);
        int currentPhase = word.getPhase();
        if (currentPhase < lastPhase) {
            Date currentDate = new Date();
            unfamiliarWordBookDao.updatePhase(userId, wordId,
                    currentPhase + 1, currentDate);
        } else {
            markWordAsFamiliar(wordId, userId);
        }
    }
    
    public void demoteWordPhase(long wordId, long userId)
            throws DataAccessException {
        BookedWord word = unfamiliarWordBookDao.getBookedWord(userId, wordId);
        int currentPhase = word.getPhase();
        if (currentPhase <= 0) {
            logger.log(Level.WARNING, DEMOTING_ERR_MSG);
            return;
        }
        
        Date currentDate = new Date();
        unfamiliarWordBookDao.updatePhase(userId, wordId,
                currentPhase - 1, currentDate);
    }

    public void resetWordPhase(long wordId, long userId)
            throws DataAccessException {
        Date currentDate = new Date();
        unfamiliarWordBookDao.updatePhase(userId, wordId, 0, currentDate);
    }
    
    public List<BookedWord> getWordsForTest(long userId, int phase)
            throws DataAccessException {
        Date currentDate = new Date();
        List<BookedWord> testWords = unfamiliarWordBookDao
                .getBookedWordsByPhaseForTest(userId, phase, currentDate);
        return testWords;
    }
    
}

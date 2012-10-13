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
package cn.edu.seu.cose.graduates.web.controller;

import cn.edu.seu.cose.graduates.persistence.dao.UnfamiliarWordBookDataAccess;
import cn.edu.seu.cose.graduates.persistence.model.BookedWord;
import cn.edu.seu.cose.graduates.persistence.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author zc <cottyard@gmail.com>
 * @author Wenhao Ji <predator.ray@gmail.com>
 */
@Controller
@RequestMapping("wordsforview.html")
public class WordsForViewController {
    
    private static final String ATTRI_WORDS_FOR_VIEW = "wordsForView";
    
    private static final int VIEW_PHASE = 0;
    
    private ModelAndView wordsForViewModelAndView;
    
    private ModelAndView redirectToLoginModelAndView;
    
    private UnfamiliarWordBookDataAccess unfamiliarWordBookdao;
    
    public WordsForViewController(
            UnfamiliarWordBookDataAccess unfamiliarWordBookdao) {
        this.unfamiliarWordBookdao = unfamiliarWordBookdao;
    }

    public void setWordsForViewModelAndView(
            ModelAndView wordsForViewModelAndView) {
        this.wordsForViewModelAndView = wordsForViewModelAndView;
    }
    
    public void setRedirectToLoginModelAndView(
            ModelAndView redirectToLoginModelAndView) {
        this.redirectToLoginModelAndView = redirectToLoginModelAndView;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession currentSession = request.getSession();
        User verifiedUser = (User) currentSession.getAttribute(
                GraduatesSessionAttributes.ATTRI_USER);
        if (verifiedUser == null) {
            return redirectToLoginModelAndView;
        }
        
        long userId = verifiedUser.getId();
        List<BookedWord> wordsForView =
                unfamiliarWordBookdao.getBookedWordsByPhase(userId, VIEW_PHASE);
        wordsForViewModelAndView.addObject(ATTRI_WORDS_FOR_VIEW, wordsForView);
        return wordsForViewModelAndView;
    }
    
}

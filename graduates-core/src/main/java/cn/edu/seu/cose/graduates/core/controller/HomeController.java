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
package cn.edu.seu.cose.graduates.core.controller;

import cn.edu.seu.cose.graduates.core.service.UserAccountService;
import cn.edu.seu.cose.graduates.core.service.WordBookService;
import cn.edu.seu.cose.graduates.core.view.GraduatesViews;
import cn.edu.seu.cose.graduates.persistence.dao.DataAccessException;
import cn.edu.seu.cose.graduates.persistence.model.BookedWord;
import cn.edu.seu.cose.graduates.persistence.model.User;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author zc <cottyard@gmail.com>
 * @author Wenhao Ji <predator.ray@gmail.com>
 */
public class HomeController extends AbstractController {
    
    private static final String ATTRI_USER_BEAN = "user";
    
    private static final String ATTRI_WORDS_FOR_VIEW = "wordsForView";
    
    private static final String COOKIE_USERNAME = "username";
    
    private static final String COOKIE_PASSWORD = "password";
    
    private UserAccountService userAccountService;
    
    private WordBookService wordBookService;
    
    public HomeController(UserAccountService userAccountService,
            WordBookService wordBookService) {
        this.userAccountService = userAccountService;
        this.wordBookService = wordBookService;
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession currentSession = request.getSession();
        User verifiedUserInSession =
                (User) currentSession.getAttribute(
                GraduatesSessionAttributes.ATTRI_USER);
        
        if (verifiedUserInSession != null) {
            ModelAndView home = new ModelAndView(GraduatesViews.HOME_VIEW);
            home.addObject(ATTRI_USER_BEAN, verifiedUserInSession);
            return home;
        }
        
        String username = getCookieValueByName(request, COOKIE_USERNAME);
        String password = getCookieValueByName(request, COOKIE_PASSWORD);
        boolean cookieIncomplete = username == null || password == null;
        if (cookieIncomplete) {
            ModelAndView register =
                    new ModelAndView(GraduatesViews.REGISTER_VIEW);
            return register;
        }
        
        User verifiedUser = userAccountService.verify(username, password);
        boolean verified = (verifiedUser != null);
        if (!verified) {
            removeCookieByName(response, COOKIE_USERNAME);
            removeCookieByName(response, COOKIE_PASSWORD);
            ModelAndView login = new ModelAndView(GraduatesViews.LOGIN_VIEW);
            return login;
        }
        
        long userId = verifiedUser.getId();
        ModelAndView home = new ModelAndView(GraduatesViews.HOME_VIEW);
        home.addObject(ATTRI_USER_BEAN, verifiedUser);
        addWordsBean(home, userId);
        return home;
    }
    
    private String getCookieValueByName(
            HttpServletRequest request, String name) {
        Assert.notNull(name);
        for (Cookie cookie: request.getCookies()) {
            String cookieName = cookie.getName();
            if (name.equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
    
    private void removeCookieByName(HttpServletResponse response,
            String name) {
        Cookie removedCookie = new Cookie(name, null);
        removedCookie.setMaxAge(0);
        response.addCookie(removedCookie);
    }
    
    private void addWordsBean(ModelAndView modelAndView, long userId)
            throws DataAccessException {
        List<BookedWord> bookedWords = wordBookService.getWordsForView(userId);
        modelAndView.addObject(ATTRI_WORDS_FOR_VIEW, bookedWords);
    }
    
}

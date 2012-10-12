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

import cn.edu.seu.cose.graduates.core.service.UserAccountService;
import cn.edu.seu.cose.graduates.web.view.LoginViewMessage;
import cn.edu.seu.cose.graduates.persistence.dao.DataAccessException;
import cn.edu.seu.cose.graduates.persistence.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author zc <cottyard@gmail.com>
 * @author Wenhao Ji <predator.ray@gmail.com>
 */
public class LoginController extends AbstractController {
    
    private static final String PARAM_USERNAME = "username";
    
    private static final String PARAM_PASSWORD = "password";
    
    private static final String PARAM_REMEMBER_ME = "rm";
    
    private static final String ATTRI_USER_BEAN = "user";
    
    private static final String ATTRI_LOGIN_MSG = "loginMsg";
    
    private static final Logger myLogger = Logger.getLogger(
            LoginController.class.getName());
    
    private UserAccountService userAccountService;
    
    private ModelAndView homeModelAndView;
    
    private ModelAndView loginModelAndView;
    
    public LoginController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public void setHomeModelAndView(ModelAndView homeModelAndView) {
        this.homeModelAndView = homeModelAndView;
    }

    public void setLoginModelAndView(ModelAndView loginModelAndView) {
        this.loginModelAndView = loginModelAndView;
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter(PARAM_USERNAME);
        String password = request.getParameter(PARAM_PASSWORD);
        HttpSession currentSession = request.getSession();
        clearVerifiedUser(currentSession);
        try {
            User verifiedUser = userAccountService.verify(username, password);
            boolean verified = (verifiedUser != null);
            if (!verified) {
                loginModelAndView.addObject(username,
                        LoginViewMessage.INCORRECT_USERNAME_OR_PASSWORD);
                return loginModelAndView;
            }
            addToSession(currentSession, verifiedUser);
            homeModelAndView.addObject(ATTRI_USER_BEAN, verifiedUser);
            return homeModelAndView;
        } catch (DataAccessException ex) {
            myLogger.log(Level.SEVERE, ex.getMessage(), ex);
            loginModelAndView.addObject(ATTRI_LOGIN_MSG,
                    LoginViewMessage.ERROR_OCCURRED);
            return loginModelAndView;
        }
    }
    
    private void addToSession(HttpSession session, User verifiedUser) {
        session.setAttribute(GraduatesSessionAttributes.ATTRI_USER,
                verifiedUser);
    }
    
    private void clearVerifiedUser(HttpSession session) {
        session.removeAttribute(GraduatesSessionAttributes.ATTRI_USER);
    }
    
}

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

import cn.edu.seu.cose.graduates.core.service.WordBookService;
import cn.edu.seu.cose.graduates.core.util.Utils;
import cn.edu.seu.cose.graduates.core.view.GraduatesViews;
import cn.edu.seu.cose.graduates.persistence.dao.DataAccessException;
import cn.edu.seu.cose.graduates.persistence.model.User;
import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author zc <cottyard@gmail.com>
 */
public class PromoteController extends AbstractController {
    
    private static final String FORMAT_ERR_MSG =
            "non-numeric value in wordid.";
    
    private static final String SUCCESS = "success";
    
    private static final String FAILURE = "failure";
    
    private static final Logger myLogger = Logger.getLogger(
            PromoteController.class);
    
    private static final String PARAM_WORD_ID = "wordid";
    
    private WordBookService wordBookService;
    
    private ModelAndView redirectModelAndView;
    
    public PromoteController(WordBookService wordBookService) {
        this.wordBookService = wordBookService;
    }

    public void setRedirectModelAndView(ModelAndView redirectModelAndView) {
        this.redirectModelAndView = redirectModelAndView;
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession currentSession = request.getSession();
        User verifiedUser = (User) currentSession.getAttribute(
                GraduatesSessionAttributes.ATTRI_USER);
        if (verifiedUser == null) {
            return redirectModelAndView;
        }
        
        String wordIdParam = request.getParameter(PARAM_WORD_ID);
        if (!Utils.isNumeric(wordIdParam)) {
            myLogger.log(Level.WARNING, FORMAT_ERR_MSG);
            response.sendError(401);
            return null;
        }
        
        long userId = verifiedUser.getId();
        long wordId = Long.valueOf(wordIdParam);
        try {
            wordBookService.promoteWordPhase(wordId, userId);
        } catch (DataAccessException ex) {
            myLogger.log(Level.SEVERE, ex.getMessage(), ex);
            sendFailureResponse(response);
            return null;
        }
        sendSuccessResponse(response);
        return null;
    }
    
    private void sendTextResponse(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }
    
    private void sendSuccessResponse(HttpServletResponse response)
            throws IOException {
        sendTextResponse(response, SUCCESS);
    }
    
    private void sendFailureResponse(HttpServletResponse response)
            throws IOException {
        sendTextResponse(response, FAILURE);
    }
    
}

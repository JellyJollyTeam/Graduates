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

import cn.edu.seu.cose.graduates.core.service.WordBookService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("test.html")
public class WordsForTestController {
    
    private static final String ATTRI_WORDS_FOR_TEST = "wordsForTest";
    
    private WordBookService wordBookService;
    
    private ModelAndView wordsForTestModelAndView;

    public WordsForTestController(WordBookService wordBookService) {
        this.wordBookService = wordBookService;
    }
    
    public void setWordsForTestModelAndView(
            ModelAndView wordsForTestModelAndView) {
        this.wordsForTestModelAndView = wordsForTestModelAndView;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getWordsForTest(HttpServletRequest request,
            HttpServletResponse response) {
        // TODO get words for test
        return wordsForTestModelAndView;
    }
    
}


package com.sudokodemo.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SARAT
 */
@Controller
@RequestMapping("/")
public class SudokoController {

    @RequestMapping(method = RequestMethod.GET)
    public String getSudokoHomePage() {
        return "index.html";
    }
}

package ua.ksolodovnik.webalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Picture controller class
 * @author ksolodovnik
 */

@Controller
public class PictureController {

    @RequestMapping("/picture")
    public String showPicture(){
        return "picture";
    }
}

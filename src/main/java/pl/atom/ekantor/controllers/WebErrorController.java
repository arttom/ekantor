package pl.atom.ekantor.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Artur on 18.03.2017.
 */
@Controller("error")
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorPage(){
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

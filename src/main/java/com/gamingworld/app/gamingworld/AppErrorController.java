package com.gamingworld.app.gamingworld;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // In case of error redirect to Swagger Documentation.
        return "<script>window.location.replace(\"https://api.gworld.xempre.com/swagger-ui/index.html\");</script>";
    }
}

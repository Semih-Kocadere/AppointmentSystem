package com.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/customError")
    public String handleError() {
        // Handle the error and return a custom error page
        return "error"; // Assuming you have an error.html template in your templates folder
    }
}
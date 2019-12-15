package org.transexpress.snap.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.transexpress.snap.misc.HTMLReader;

@RequestMapping("")
@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return HTMLReader.read("frontend/index.html");
    }
}

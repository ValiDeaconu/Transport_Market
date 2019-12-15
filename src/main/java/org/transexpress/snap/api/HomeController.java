package org.transexpress.snap.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return HTMLReader.read("frontend/index.html");
    }
}

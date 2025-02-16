package top.helouisedayane.controllers;

import org.springframework.web.bind.annotation.RestController;

import top.helouisedayane.model.Greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class GreetingController{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greetting(
        @RequestParam(value = "name", defaultValue = "Helouise")
        String name
       ){

        return new Greeting(counter.incrementAndGet(), String.format(template,name));

    }
}
package com.jfs.spring;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.jfs.App;

import org.json.simple.JSONObject;

@RestController
public class Controller {

    public Controller() {
    }
    
    @GetMapping("/getUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<JSONObject> coolCars() {
    	return App.getUser();
    }
}
package com.faceprep.spring;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.faceprep.App;

import org.json.JSONObject;

@RestController
public class Controller {

    public Controller() {
    }
    
    @GetMapping("/game")
    @CrossOrigin(origins = "http://localhost:4200")
    public String get() {
    	return App.getGames().toString();
    }
    
    @PostMapping("/game/{name}/{by}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String post(@PathVariable String name, @PathVariable String by) {
    	return App.newGame(name, by).toString();
    }

	@PutMapping("/game/{id}/{by}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String put(@PathVariable int id, @PathVariable String by) {
    	return App.updateGame(id, by).toString();
    }

	@DeleteMapping("/game/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public String delete(@PathVariable int id) {
    	return App.deleteGame(id).toString();
    }
}
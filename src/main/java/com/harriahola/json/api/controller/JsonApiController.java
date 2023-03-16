package com.harriahola.json.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.harriahola.json.api.security.jwt.JwtUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonApiController {
    private Map<String, String> endpointToFileMap;

    @Autowired
    private JwtUtil jwtUtil;

    public JsonApiController() {
        endpointToFileMap = new HashMap<>();

        String[] endpoints = System.getProperty("endpoints", "test").split(",");
        String[] jsonFiles = System.getProperty("jsonFiles", "test.json").split(",");

        if (endpoints.length != jsonFiles.length) {
            throw new IllegalArgumentException("The number of endpoints and json files must match!");
        }

        for (int i = 0; i < endpoints.length; i++) {
            endpointToFileMap.put(endpoints[i], jsonFiles[i]);
        }
    }

    @GetMapping("/{endpoint}")
    public String getData(@PathVariable("endpoint") String endpoint) {
        String jsonFilePath = endpointToFileMap.get(endpoint);

        if (jsonFilePath == null) {
            return "{\"error\": \"Invalid endpoint\"}";
        }

        Path path = Paths.get(jsonFilePath);

        try {
            byte[] fileContent = Files.readAllBytes(path);
            String jsonContent = new String(fileContent);
            return jsonContent;
        } catch (IOException error) {
            return "{\"error\": \"Unable to read json file\"}";
        }
    }


    @GetMapping(path = "/generate-token/{id}")
    public ResponseEntity<?> generateToken(@PathVariable("id") String id) {
        try {
            String userId = id;
            String token = jwtUtil.generateJwtToken(userId);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating token: " + e.getMessage());
        }
    }

}

package com.libokai.controller.impl;

import com.libokai.controller.myController;
import com.libokai.service.impl.OtherApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class OtherApiControllerImpl implements myController {

    @Autowired
    private OtherApiServiceImpl apiService;

    @RequestMapping("/api")
    public String getApiData() throws IOException {
        return apiService.getApiData();
    }


}

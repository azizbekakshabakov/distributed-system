package com.example.redissession.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    @GetMapping("/index-page")
    public String indexPage() {
        return "This is the index page";
    }

    @GetMapping("session-page")
    public String getSession(HttpSession session) {
        return "Session is " + session;
    }

    @GetMapping("save-to-session/{data}")
    public String saveToSession(HttpSession session, @PathVariable("data") String data) {
        ArrayList<String> dataList;
        if (session.getAttribute("dataList") != null) {
            dataList = (ArrayList<String>) session.getAttribute("dataList");
        } else {
            dataList = new ArrayList<>();
        }
        dataList.add(data);
        session.setAttribute("dataList", dataList);

        return "success";
    }

    @GetMapping("view-data")
    public String dataList(HttpSession session) {
        ArrayList<String> dataList;
        if (session.getAttribute("dataList") != null) {
            dataList = (ArrayList<String>) session.getAttribute("dataList");
        } else {
            dataList = new ArrayList<>();
        }
        String result = "";
        for (String data : dataList) {
            result += data + " ";
        }

        return result;
    }
}

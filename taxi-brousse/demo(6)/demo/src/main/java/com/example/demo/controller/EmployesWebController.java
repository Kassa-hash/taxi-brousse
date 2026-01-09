package com.example.demo.controller;

import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployesWebController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/employes")
    public String listEmployes(Model model) {
        model.addAttribute("employes", employeService.getAllEmployes());
        return "employes/list";
    }
}

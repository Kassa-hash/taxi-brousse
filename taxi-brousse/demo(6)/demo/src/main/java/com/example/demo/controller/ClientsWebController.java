package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.CategorieClient;
import com.example.demo.entity.TypeClient;
import com.example.demo.service.ClientService;
import com.example.demo.service.CategorieClientService;
import com.example.demo.service.TypeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientsWebController {

    @Autowired
    private ClientService clientService;
    
    @Autowired
    private CategorieClientService categorieClientService;
    
    @Autowired
    private TypeClientService typeClientService;

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }
    
    @GetMapping("/{id}")
    public String viewClient(@PathVariable int id, Model model) {
        var client = clientService.getClientById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "clients/detail";
        }
        return "redirect:/clients";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("categories", categorieClientService.getAllCategorieClients());
        model.addAttribute("types", typeClientService.getAllTypeClients());
        return "clients/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var client = clientService.getClientById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            model.addAttribute("categories", categorieClientService.getAllCategorieClients());
            model.addAttribute("types", typeClientService.getAllTypeClients());
            return "clients/form";
        }
        return "redirect:/clients";
    }
    
    @PostMapping("/create")
    public String createClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }
    
    @PostMapping("/{id}/update")
    public String updateClient(@PathVariable int id, @ModelAttribute Client client) {
        client.setIdClient(id);
        clientService.updateClient(id, client);
        return "redirect:/clients";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}


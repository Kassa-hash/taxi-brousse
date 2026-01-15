package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientWebController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public String afficherClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

    @GetMapping("/form")
    public String afficherFormulaire(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    @PostMapping("/save")
    public String sauvegarder(@ModelAttribute Client client) {
        clientService.createClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String afficherDetail(@PathVariable Integer id, Model model) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "clients/detail";
        }
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String afficherEditeur(@PathVariable Integer id, Model model) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "clients/form";
        }
        return "redirect:/clients";
    }

    @PostMapping("/{id}/update")
    public String mettreAJour(@PathVariable Integer id, @ModelAttribute Client client) {
        clientService.updateClient(id, client);
        return "redirect:/clients/" + id;
    }

    @PostMapping("/{id}/delete")
    public String supprimer(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}

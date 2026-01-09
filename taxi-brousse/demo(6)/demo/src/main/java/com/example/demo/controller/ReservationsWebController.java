package com.example.demo.controller;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;
import com.example.demo.service.ClientService;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationsWebController {

    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservations/list";
    }
    
    @GetMapping("/{id}")
    public String viewReservation(@PathVariable int id, Model model) {
        var reservation = reservationService.getReservationById(id);
        if (reservation.isPresent()) {
            model.addAttribute("reservation", reservation.get());
            return "reservations/detail";
        }
        return "redirect:/reservations";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("voyages", voyageService.getAllVoyages());
        return "reservations/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var reservation = reservationService.getReservationById(id);
        if (reservation.isPresent()) {
            model.addAttribute("reservation", reservation.get());
            model.addAttribute("clients", clientService.getAllClients());
            model.addAttribute("voyages", voyageService.getAllVoyages());
            return "reservations/form";
        }
        return "redirect:/reservations";
    }
    
    @PostMapping("/create")
    public String createReservation(@ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }
    
    @PostMapping("/{id}/update")
    public String updateReservation(@PathVariable int id, @ModelAttribute Reservation reservation) {
        reservation.setIdReservation(id);
        reservationService.updateReservation(id, reservation);
        return "redirect:/reservations";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}


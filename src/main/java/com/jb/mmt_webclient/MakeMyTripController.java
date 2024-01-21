package com.jb.makemytrip2.controller;

import com.jb.makemytrip2.bindings.Passenger;
import com.jb.makemytrip2.bindings.Ticket;
import com.jb.makemytrip2.service.MakeMyTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MakeMyTripController {

    private final MakeMyTripService service;

    public MakeMyTripController(MakeMyTripService service) {
        this.service = service;
    }

    @PostMapping("/ticket")
    public String ticketBooking(@ModelAttribute("p") Passenger p, Model m){
        Ticket bookTicket =  service.bookTicket(p);
        m.addAttribute("ticket", bookTicket);
        m.addAttribute("msg", "Your ticket is booked"
                +bookTicket.getTicketNum());
        return "bookTicket";
    }

    @GetMapping("/book-ticket")
    public String bookTicket(Model m){
        m.addAttribute("p", new Passenger());
        return "bookTicket";
    }


    @GetMapping("/")
    public String index(Model model){
        List<Ticket> allTickets = service.getAllTickets();
        model.addAttribute("tickets", allTickets);
        return "index";
    }


}

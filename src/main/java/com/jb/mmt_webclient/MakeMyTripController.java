package com.jb.mmt_webclient;

import com.jb.mmt_webclient.bindings.Passenger;
import com.jb.mmt_webclient.bindings.Ticket;
import com.jb.mmt_webclient.service.MakeMyTripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class MakeMyTripController {

    private final MakeMyTripService service;

    public MakeMyTripController(MakeMyTripService service) {
        this.service = service;
    }

    @PostMapping("/ticket")
    public String ticketBooking(@ModelAttribute("p") Passenger p, Model m){
        Mono<Ticket> bookTicket =  service.bookTicket(p);
        Ticket ticket = bookTicket.block();
        m.addAttribute("ticket", ticket);
        m.addAttribute("msg", "Your ticket is booked"
                +ticket.getTicketNum());
        return "bookTicket";
    }

    @GetMapping("/book-ticket")
    public String bookTicket(Model m){
        m.addAttribute("p", new Passenger());
        return "bookTicket";
    }


    @GetMapping("/")
    public String index(Model model){
        Mono<Ticket[]> allTickets = service.getAllTickets();
        model.addAttribute("tickets", allTickets);
        return "index";
    }


}

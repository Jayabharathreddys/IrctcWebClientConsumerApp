package com.jb.mmt_webclient.service;

import com.jb.mmt_webclient.bindings.Passenger;
import com.jb.mmt_webclient.bindings.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MakeMyTripService {
    public Mono<Ticket> bookTicket(Passenger p){
       String apiUrl ="http://13.200.229.121:8080/ticket";
        WebClient webClient = WebClient.create();
        Mono<Ticket> bodyToMono = webClient.post()
                .uri(apiUrl)
                .body(BodyInserters.fromValue(p))
                .retrieve()
                .bodyToMono(Ticket.class);

        return bodyToMono;
    }

    public Mono<Ticket[]> getAllTickets(){
        String apiUrl ="http://13.200.229.121:8080/tickets";
        RestTemplate rt = new RestTemplate();

        WebClient webClient = WebClient.create();
        Mono<Ticket[]> bodyToMono = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Ticket[].class);
      //  assert body != null;
     //   List<Ticket> tickets = Arrays.asList(body);
        return bodyToMono;
    }

}

package com.example.softwareproject.stadium.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.PaymentHistory;
import com.example.softwareproject.stadium.models.Stores;
import com.example.softwareproject.stadium.models.Ticket;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.PaymentHistoryRepository;
import com.example.softwareproject.stadium.repositories.StoresRepository;
import com.example.softwareproject.stadium.repositories.TicketRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoresRepository storesRepository;

    public Ticket addTicket(Ticket ticket){
        try{
            return ticketRepository.save(ticket);
        }catch(Exception e)
        {
         System.out.println(e.toString());
         return null;
       }
    }
    public List<Ticket> getTicketsByManager(UserDetails userDetails){
        List<Ticket> emptyTickets = new ArrayList<Ticket>();
        Ticket no_ticket = new Ticket();
        emptyTickets.add(no_ticket);


        String email = userDetails.getUsername();
        User user = this.userRepository.findByEmail(email).orElse(null);
        if(user == null)  return emptyTickets;;
        Stores store = this.storesRepository.findByManagerId(user.getId()).orElse(null);
        if(store == null)  return emptyTickets;;
        List<Ticket> tickets = this.ticketRepository.findByConfirmationAndStoreId(false,store.getId());
    
        if(tickets.size() == 0) return emptyTickets;
        return tickets;

    }
    public List<Ticket> getTicketsByManagerStatic(String email){
        List<Ticket> emptyTickets = new ArrayList<Ticket>();
        Ticket no_ticket = new Ticket();
        // no_ticket.setReservationDate(null);
        // no_ticket.setUser(null);
        // no_ticket.setCategory(null);
        // no_ticket.setConfirmation(null);
        // no_ticket.setMatch(null);
        // no_ticket.setPrice(null);
        // no_ticket.setStadium(null);
        // no_ticket.setStore(null);
        emptyTickets.add(no_ticket);
        User user = this.userRepository.findByEmail(email).orElse(null);
        if(user == null) {
            System.out.println("cannot find user");
            return emptyTickets;
        }
        Stores store = this.storesRepository.findByManagerId(user.getId()).orElse(null);
        if(store == null){
            System.out.println("cannot find store");
            
            return emptyTickets;
        } 
        List<Ticket> tickets = this.ticketRepository.findByConfirmationAndStoreId(true,store.getId());
       
        if(tickets.size() == 0){
            return emptyTickets;
        }
        return tickets;

    }
    
}

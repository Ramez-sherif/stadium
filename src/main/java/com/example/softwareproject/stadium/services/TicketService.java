package com.example.softwareproject.stadium.services;

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
         e.toString();
         return null;
       }
    }
    public List<Ticket> getTicketsByManager(UserDetails userDetails){
        String email = userDetails.getUsername();
        User user = this.userRepository.findByEmail(email).orElse(null);
        if(user == null) return null;
        Stores store = this.storesRepository.findByManagerId(user.getId()).orElse(null);
        if(store == null) return null;
        List<Ticket> tickets = this.ticketRepository.findByStoreId(store.getId());
        if(tickets.size() == 0) return null;
        return tickets;

    }
}

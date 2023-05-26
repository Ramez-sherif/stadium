package com.example.softwareproject.stadium.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class PaymentHistoryService {
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoresRepository storesRepository;

    public PaymentHistory ConfrimTicket(Long id){
        Ticket ticket = this.ticketRepository.findById(id).orElse(null);
        if(ticket == null) return null;
        ticket.setConfirmation(true);
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setTicket(ticket);        
        paymentHistory.setPaymentDate(LocalDateTime.now());
        if(this.paymentHistoryRepository.save(paymentHistory) == null){
            return null;
        }
        return paymentHistory;
    }
}

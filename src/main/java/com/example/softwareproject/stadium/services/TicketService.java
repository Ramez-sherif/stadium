package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.PaymentHistory;
import com.example.softwareproject.stadium.repositories.PaymentHistoryRepository;
import com.example.softwareproject.stadium.repositories.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
}

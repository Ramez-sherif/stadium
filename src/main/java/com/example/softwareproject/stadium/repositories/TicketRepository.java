package com.example.softwareproject.stadium.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByStoreId(Long id);
    List<Ticket> findByConfirmationAndStoreId(int confirmation,Long id);
    List<Ticket> findByConfirmationAndUserId(int confirmation,String id);
    List<Ticket> findByMatchId(Long id);

}
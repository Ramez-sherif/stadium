package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Stores;
import com.example.softwareproject.stadium.repositories.StoresRepository;

@Service
public class StoreService {
    @Autowired
    private StoresRepository storesRepository;


    public List<Stores> getAllStores() {
        return storesRepository.findAll();
    }

    public Stores getStoreById(long id) {
        return storesRepository.findById(id).orElseThrow();
    }

    public Stores addStore(Stores store) {
        return storesRepository.save(store);
    }
    
    public void deleteStore(Stores c) {
        storesRepository.delete(c);

    }

    public void deleteStoreById(long id) {
        storesRepository.deleteById(id);
    }
    public Stores updateStores(Long id, Stores stores) {
        Stores exisStores = storesRepository.findById(id).orElseThrow();
        exisStores.setName(stores.getName());
        exisStores.setLocation(stores.getLocation());
        exisStores.setManager(stores.getManager());
        return storesRepository.save(exisStores);
    }
    
   
}


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
        return storesRepository.findById(id).orElse(null);
    }

    public Stores addStore(Stores store) {
        try{

            return storesRepository.save(store);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public void deleteStore(Stores c) {
        storesRepository.delete(c);

    }

    public void deleteStoreById(long id) {
        storesRepository.deleteById(id);
    }
    public Stores updateStores(Long id, Stores stores) {
        try{
            Stores exisStores = storesRepository.findById(id).orElseThrow();
            exisStores.setName(stores.getName());
            exisStores.setLocation(stores.getLocation());
            exisStores.setManager(stores.getManager());
            return storesRepository.save(exisStores);
        }catch(Exception e){
            return null;
        }
    }
    
   
}


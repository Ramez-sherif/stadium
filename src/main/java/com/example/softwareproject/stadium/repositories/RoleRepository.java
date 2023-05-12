package com.example.softwareproject.stadium.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.softwareproject.stadium.models.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role,Long> {
    List<Role> findByName(String Name);
}

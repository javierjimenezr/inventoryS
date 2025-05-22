package com.EcoMarktSpa.demo.Repository;


import com.EcoMarktSpa.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Métodos custom si quieres buscar por username, email, etc.
}
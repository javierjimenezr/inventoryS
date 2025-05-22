package com.EcoMarktSpa.demo.Repository;

import com.EcoMarktSpa.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}

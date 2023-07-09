package com.retail.kynaara.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCustomRepository {

    @Autowired
    EntityManager entityManager;
}

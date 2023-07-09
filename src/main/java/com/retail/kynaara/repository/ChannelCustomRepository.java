package com.retail.kynaara.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class ChannelCustomRepository {

    @Autowired
    EntityManager entityManager;
}

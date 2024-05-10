package com.tender.jpa.dao;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassDao {

    @Autowired
    EntityManager em;



}

package com.example.odev.repo;

import com.example.odev.Model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Person, Long> {
    //Person cinsinde nesneleri tutan repo


}

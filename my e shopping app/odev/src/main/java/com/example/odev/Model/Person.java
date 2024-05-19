package com.example.odev.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
        //Person nesne tanımlaması
        @Id
        private Long id;
        private String name;
 //Getter ve setterlar
        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }
}

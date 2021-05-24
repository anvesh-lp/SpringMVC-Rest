package com.anvesh.springmvcrest.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Customer {
    private String firstname;
    private String lastname;
    private String customer_url;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}

package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;
}
package com.digital.userprofile.pojo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String userName;
    @ToString.Exclude
    private String userPhoneNumber;
    @ToString.Exclude
    private String userEmail;

    //can try date if below don't work
    @CreationTimestamp
    private Instant recordCreatedDateTime;

    @UpdateTimestamp
    private Instant recordUpdatedDateTime;
}

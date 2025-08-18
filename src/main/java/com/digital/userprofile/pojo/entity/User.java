package com.digital.userprofile.pojo.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name = "userId", columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

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

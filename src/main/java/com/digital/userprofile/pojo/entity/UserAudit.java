package com.digital.userprofile.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "user")
public class UserAudit {

    @Id
    @Column(name = "userAuditId", columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userAuditId;

    private String userId;

    private String recordCreatedDateTime;
    private String recordUpdatedDateTime;

    // Additional fields for audit purposes
    private String action; // e.g., "CREATE", "UPDATE", "DELETE"
    private String ip; // User or system that performed the action
    private String corrId;
}

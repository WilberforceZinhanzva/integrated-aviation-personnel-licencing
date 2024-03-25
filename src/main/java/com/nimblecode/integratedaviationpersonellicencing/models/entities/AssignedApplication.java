package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name="assigned_applications")
public class AssignedApplication {
    @Id
    private String id = UUID.randomUUID().toString();
    private String applicationId;
    private String assignedTo;
    private String assignedBy;
    private LocalDateTime assignedOn;
}

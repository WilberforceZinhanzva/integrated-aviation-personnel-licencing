package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableComment;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Entity
@Table(name="comments")
public class Comment implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String message;
    private String commentedBy;
    private LocalDateTime createdOn = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name="application")
    private Application application;

    @Override
    public TransferableComment serializeForTransfer() {
        return TransferableComment.builder()
                .id(id)
                .message(message)
                .from(commentedBy)
                .createdOn(createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }
}

package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableCompletedCheck;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
@Data
@Entity
@Table(name="completed_checks")
public class CompletedCheck implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String checkId;
    private String checkedBy;
    private LocalDateTime checkedOn = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="application")
    private Application application;

    @Override
    public TransferableCompletedCheck serializeForTransfer() {
        return TransferableCompletedCheck.builder()
                .id(id)
                .checkId(checkId)
                .checkedBy(checkedBy)
                .checkedOn(checkedOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }
}

package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="av_roles")
public class Role implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private LocalDateTime createdOn = LocalDateTime.now();

    @Override
    public TransferableRole serializeForTransfer() {
        return TransferableRole.builder()
                .id(id)
                .name(name)
                .createdOn(createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }
}

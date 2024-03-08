package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="applications_types")
public class ApplicationType implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;

    @Override
    public TransferableApplicationType serializeForTransfer() {
        return TransferableApplicationType.builder()
                .id(id)
                .name(name)
                .build();
    }
}

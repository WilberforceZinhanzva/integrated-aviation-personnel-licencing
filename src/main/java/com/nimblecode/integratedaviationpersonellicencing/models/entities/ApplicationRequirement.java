package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationRequirement;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="application_requirements")
public class ApplicationRequirement implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String applicationType;
    private String requirement;

    @Override
    public TransferableApplicationRequirement serializeForTransfer() {
        return TransferableApplicationRequirement.builder()
                .id(id)
                .applicationType(applicationType)
                .requirement(requirement)
                .build();
    }
}

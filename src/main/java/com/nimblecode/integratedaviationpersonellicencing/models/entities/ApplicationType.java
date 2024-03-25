package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="applications_types")
public class ApplicationType implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ApplicationCheck> applicationChecks = new ArrayList<>();

    @Override
    public TransferableApplicationType serializeForTransfer() {
        return TransferableApplicationType.builder()
                .id(id)
                .name(name)
                .build();
    }
}

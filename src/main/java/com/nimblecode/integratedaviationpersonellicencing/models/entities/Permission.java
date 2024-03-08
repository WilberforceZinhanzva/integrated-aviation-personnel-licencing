package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferablePermission;
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
@Table(name="permissions")
public class Permission implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private LocalDateTime createdOn= LocalDateTime.now();
    @ManyToMany
    private List<User> users;


    @Override
    public TransferablePermission serializeForTransfer() {
        return TransferablePermission.builder()
                .id(id)
                .name(name)
                .createdOn(createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }
}

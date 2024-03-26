package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="user_permissions")
public class UserPermission {
    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private String permissionId;
}

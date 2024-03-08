package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableUser;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class User implements IDatabaseEntity {
    @Id
    private String id= UUID.randomUUID().toString();
    private String fullname;
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id")

    )
    private List<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_permissions",
            joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="permission",referencedColumnName = "id")
    )
    private List<Permission> permissions;

    @Override
    public TransferableUser serializeForTransfer() {
        return TransferableUser.builder()
                .id(id)
                .fullname(fullname)
                .username(username)
                .roles(roles.stream().map(Role::serializeForTransfer).toList())
                .permissions(permissions.stream().map(Permission::serializeForTransfer).toList())
                .build();
    }
}

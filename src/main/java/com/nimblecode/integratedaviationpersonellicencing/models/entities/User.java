package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableUser;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="av_users")
public class User implements IDatabaseEntity, UserDetails {
    @Id
    private String id= UUID.randomUUID().toString();
    private String fullname;
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id")

    )
    private List<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        for(Permission permission: permissions){
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplicationCheck;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name="application_checks")
public class ApplicationCheck implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String requiredCheck;
    private String permittedRoles;
    @ManyToOne
    @JoinColumn(name="av_application_type")
    private ApplicationType applicationType;

    @Override
    public TransferableApplicationCheck serializeForTransfer() {
        return TransferableApplicationCheck.builder()
                .id(id)
                .check(requiredCheck)
                .build();
    }

    public List<String> permittedRolesList(){
        if(Optional.ofNullable(permittedRoles).isEmpty())
            this.permittedRoles="";
        String[] roles = permittedRoles.split(",");
        return Arrays.asList(roles);
    }

    public void addPermittedRoles(List<String> roles){
        List<String> existingPermittedRoles = new ArrayList<>();

        for(String newRole : roles){
            if(!existingPermittedRoles.contains(newRole))
                existingPermittedRoles.add(newRole);
        }

        StringBuilder aggregateRoles = new StringBuilder();
        for(int i = 0; i < existingPermittedRoles.size(); i++){
            aggregateRoles.append(existingPermittedRoles.get(i));
            if(i < existingPermittedRoles.size()-1)
                aggregateRoles.append(",");
        }
        this.permittedRoles = aggregateRoles.toString();
    }

    public void removePermittedRoles(List<String> roles){
        if(Optional.ofNullable(permittedRoles).isEmpty())
            this.permittedRoles="";
        List<String> existingPermittedRoles = Arrays.asList(permittedRoles.split(","));
        for(String r : roles){
            existingPermittedRoles.remove(r);
        }

        StringBuilder aggregateRoles = new StringBuilder();
        for(int i = 0; i < existingPermittedRoles.size(); i++){
            aggregateRoles.append(existingPermittedRoles.get(i));
            if(i < existingPermittedRoles.size()-1)
                aggregateRoles.append(",");
        }
        this.permittedRoles = aggregateRoles.toString();
    }
}

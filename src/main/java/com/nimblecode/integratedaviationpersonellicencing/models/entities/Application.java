package com.nimblecode.integratedaviationpersonellicencing.models.entities;

import com.nimblecode.integratedaviationpersonellicencing.enums.ApplicationStatus;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.IDatabaseEntity;
import com.nimblecode.integratedaviationpersonellicencing.models.transferables.TransferableApplication;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
@Entity
@Table(name="av_applications")
public class Application implements IDatabaseEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String applicationRef;
    private String applicantName;
    private String applicationType;
    private String pendingChecks;
    private LocalDateTime createdOn = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CompletedCheck> completedCheckList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;


    @Override
    public TransferableApplication serializeForTransfer() {
        return TransferableApplication.builder()
                .id(id)
                .applicationRef(applicationRef)
                .applicantName(applicantName)
                .applicationType(applicationType)
                .pendingChecks(pendingChecks())
                .applicationStatus(applicationStatus)
                .completedCheckList(completedCheckList.stream().map(CompletedCheck::serializeForTransfer).toList())
                .comments(comments.stream().map(Comment::serializeForTransfer).toList())
                .createdOn(createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }

    public List<String> pendingChecks(){
        if(Optional.ofNullable(pendingChecks).isPresent() && !pendingChecks.isEmpty())
            return Arrays.asList(pendingChecks.split(","));
        return new ArrayList<>();

    }
    public void addPendingChecks(List<String> checks){
        List<String> existingChecks = new ArrayList<>();
        if(!Optional.ofNullable(pendingChecks).isEmpty())
            existingChecks = Arrays.asList(pendingChecks.split(","));


        for(int i = 0; i < checks.size(); i++){
            if(!existingChecks.contains(checks.get(i)))
               existingChecks.add(checks.get(i));
        }
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < existingChecks.size(); i++){
            s.append(existingChecks.get(i));
            if(i < existingChecks.size() -1)
                s.append(",");
        }


        this.pendingChecks = s.toString();

    }

    public void removeChecks(List<String> checks){
        List<String> existingChecks = new ArrayList<>();

        if(Optional.ofNullable(pendingChecks).isPresent()){
            if(!pendingChecks.isEmpty()){
                for(String s : pendingChecks.split(",")){
                    existingChecks.add(s);
                }
            }


        }


        for(int i = 0; i < checks.size(); i++){
            existingChecks.remove(checks.get(i));
        }
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < existingChecks.size(); i++){
            s.append(existingChecks.get(i));
            if(i < existingChecks.size() -1)
                s.append(",");
        }


        this.pendingChecks = s.toString();
    }
}

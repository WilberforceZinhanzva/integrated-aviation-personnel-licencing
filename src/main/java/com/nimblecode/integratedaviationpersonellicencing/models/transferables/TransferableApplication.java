package com.nimblecode.integratedaviationpersonellicencing.models.transferables;

import com.nimblecode.integratedaviationpersonellicencing.enums.ApplicationStatus;
import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransferableApplication implements ITransferable {
    private String id;
    private String applicationRef;
    private String applicantName;
    private String applicationType;
    private List<String> pendingChecks;
    private ApplicationStatus applicationStatus;
    private List<TransferableCompletedCheck> completedCheckList;
    private List<TransferableComment> comments;
    private String createdOn;
}

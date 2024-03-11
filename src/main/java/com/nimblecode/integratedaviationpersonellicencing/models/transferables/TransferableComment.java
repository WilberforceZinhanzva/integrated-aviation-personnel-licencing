package com.nimblecode.integratedaviationpersonellicencing.models.transferables;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferableComment implements ITransferable {
    private String id;
    private String message;
    private String from;
    private String createdOn;
}

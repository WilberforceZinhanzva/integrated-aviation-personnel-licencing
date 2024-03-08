package com.nimblecode.integratedaviationpersonellicencing.models.transferables;

import com.nimblecode.integratedaviationpersonellicencing.models.interfaces.ITransferable;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TransferableUser implements ITransferable {
    private String id;
    private String fullname;
    private String username;
    private List<TransferableRole> roles = new ArrayList<>();
    private List<TransferablePermission> permissions = new ArrayList<>();
}

package com.fnol.service;

import com.fnol.model.ExtractedFields;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldValidator {

    public List<String> findMissingFields(ExtractedFields f) {

        List<String> missing = new ArrayList<>();

        if (f.getPolicyNumber() == null) missing.add("policyNumber");
        if (f.getPolicyholderName() == null) missing.add("policyholderName");
        if (f.getEffectiveDates() == null) missing.add("effectiveDates");

        if (f.getIncidentDate() == null) missing.add("incidentDate");
        if (f.getIncidentTime() == null) missing.add("incidentTime");
        if (f.getIncidentLocation() == null) missing.add("incidentLocation");
        if (f.getIncidentDescription() == null) missing.add("incidentDescription");

        if (f.getClaimant() == null) missing.add("claimant");

        if (f.getAssetType() == null) missing.add("assetType");
        if (f.getAssetId() == null) missing.add("assetId");
        if (f.getEstimatedDamage() == null) missing.add("estimatedDamage");

        if (f.getClaimType() == null) missing.add("claimType");
        if (f.getInitialEstimate() == null) missing.add("initialEstimate");

        return missing;
    }
}

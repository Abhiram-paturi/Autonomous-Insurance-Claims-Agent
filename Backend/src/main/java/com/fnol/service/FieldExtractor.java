package com.fnol.service;

import com.fnol.model.ExtractedFields;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FieldExtractor {

    public ExtractedFields extract(String text) {
        ExtractedFields f = new ExtractedFields();

        f.setPolicyNumber(find(text, "Policy Number[:\\s]+([A-Z0-9\\-]+)"));
        f.setPolicyholderName(find(text, "Policyholder[:\\s]+([A-Za-z ]+)"));
        f.setEffectiveDates(find(text, "Effective Dates[:\\s]+([0-9/\\- ]+)"));

        f.setIncidentDate(find(text, "Incident Date[:\\s]+([0-9/\\-]+)"));
        f.setIncidentTime(find(text, "Incident Time[:\\s]+([0-9:]+)"));
        f.setIncidentLocation(find(text, "Location[:\\s]+([A-Za-z0-9 ,]+)"));
        f.setIncidentDescription(find(text, "Description[:\\s]+([\\s\\S]{10,300})"));

        f.setClaimant(find(text, "Claimant[:\\s]+([A-Za-z ]+)"));

        f.setThirdParties(find(text, "Third Party[:\\s]+([A-Za-z ]+)"));
        f.setContactDetails(find(text, "Contact[:\\s]+([A-Za-z0-9+ ]+)"));

        f.setAssetType(find(text, "Asset Type[:\\s]+([A-Za-z ]+)"));
        f.setAssetId(find(text, "Asset ID[:\\s]+([A-Za-z0-9\\- ]+)"));
        f.setEstimatedDamage(parseDouble(find(text, "Estimated Damage[:\\s]+([0-9,.]+)")));

        f.setClaimType(find(text, "Claim Type[:\\s]+([A-Za-z ]+)"));
        f.setAttachments(find(text, "Attachments[:\\s]+([A-Za-z0-9, ]+)"));
        f.setInitialEstimate(parseDouble(find(text, "Initial Estimate[:\\s]+([0-9,.]+)")));

        return f;
    }

    private String find(String text, String regex) {
        Matcher m = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text);
        return m.find() ? m.group(1).trim() : null;
    }

    private Double parseDouble(String val) {
        if (val == null) return null;
        return Double.parseDouble(val.replace(",", ""));
    }
}

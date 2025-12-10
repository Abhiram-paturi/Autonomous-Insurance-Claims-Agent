package com.fnol.service;

import com.fnol.model.ExtractedFields;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimRouter {

    public record RouteResult(String route, String reason) {}

    public RouteResult route(ExtractedFields f, List<String> missing) {

        if (!missing.isEmpty()) {
            return new RouteResult("Manual review", "Missing mandatory fields");
        }

        if (f.getEstimatedDamage() != null && f.getEstimatedDamage() < 25000) {
            return new RouteResult("Fast-track", "Estimated damage less than £25,000");
        }
        
        if (f.getIncidentDescription() != null) {
            String desc = f.getIncidentDescription().toLowerCase();

            if (desc.contains("fraud") || desc.contains("inconsistent") || desc.contains("staged")) {
                return new RouteResult("Investigation", "Suspicious keywords detected");
            }
        }

        if ("injury".equalsIgnoreCase(f.getClaimType())) {
            return new RouteResult("Specialist Queue", "Injury claims require specialist handling");
        }


        return new RouteResult("Standard Queue", "No special conditions triggered");
    }
}

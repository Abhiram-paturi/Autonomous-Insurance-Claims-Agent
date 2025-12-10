package com.fnol.model;


import java.util.List;


public class OutputPayload {
    private ExtractedFields extractedFields;
    private List<String> missingFields;
    private String recommendedRoute;
    private String reasoning;

    
    public ExtractedFields getExtractedFields() {
        return extractedFields;
    }
    public void setExtractedFields(ExtractedFields extractedFields) {
        this.extractedFields = extractedFields;
    }
    public List<String> getMissingFields() {
        return missingFields;
    }
    public void setMissingFields(List<String> missingFields) {
        this.missingFields = missingFields;
    }
    public String getRecommendedRoute() {
        return recommendedRoute;
    }
    public void setRecommendedRoute(String recommendedRoute) {
        this.recommendedRoute = recommendedRoute;
    }
    public String getReasoning() {
        return reasoning;
    }
    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }


    
}

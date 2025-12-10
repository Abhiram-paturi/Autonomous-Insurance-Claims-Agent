package com.fnol.controller;

import com.fnol.model.ExtractedFields;
import com.fnol.model.OutputPayload;
import com.fnol.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/fnol")
@CrossOrigin(origins = "*") // allow frontend access
public class FnolController {

    @Autowired
    private DocumentParser parser;

    @Autowired
    private FieldExtractor extractor;

    @Autowired
    private FieldValidator validator;

    @Autowired
    private ClaimRouter router;

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OutputPayload analyze(@RequestParam("file") MultipartFile file) throws Exception {

        // Step 1: Parse text from file
        String text = parser.extractText(file);

        // Step 2: Extract fields
        ExtractedFields fields = extractor.extract(text);

        // Step 3: Validate missing fields
        var missing = validator.findMissingFields(fields);

        // Step 4: Routing
        var routing = router.route(fields, missing);

        // Step 5: Construct JSON output
        OutputPayload output = new OutputPayload();
        output.setExtractedFields(fields);
        output.setMissingFields(missing);
        output.setRecommendedRoute(routing.route());
        output.setReasoning(routing.reason());

        return output;
    }
}

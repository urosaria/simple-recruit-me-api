package com.example.simplerecruitmeapi.controller;

import com.example.simplerecruitmeapi.domain.Candidate;
import com.example.simplerecruitmeapi.dto.CandidateDto;
import com.example.simplerecruitmeapi.service.RecruitmentService;
import com.example.simplerecruitmeapi.validation.RecruitmentValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1-0")
public class RecruitmentController {

    private static final Logger logger = LoggerFactory.getLogger(RecruitmentController.class);

    private final RecruitmentService recruitmentService;
    private final RecruitmentValidator recruitmentValidator;

    public RecruitmentController(RecruitmentService recruitmentService, RecruitmentValidator recruitmentValidator) {
        this.recruitmentService = recruitmentService;
        this.recruitmentValidator = recruitmentValidator;
    }

    @PostMapping(value = {"/recruiteme.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recruitMe(@Valid @RequestBody CandidateDto payload, BindingResult result) {
        recruitmentValidator.validate(payload, result);

        if (result.hasErrors()) {
            return handleValidationErrors(result);
        }

        Candidate candidate = recruitmentService.saveCandidate(payload);
        logger.info("Candidate saved: {}", candidate);
        return new ResponseEntity<>(recruitmentService.viewCandidate(candidate.getId()), HttpStatus.OK);
    }

    private ResponseEntity<List<String>> handleValidationErrors(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}

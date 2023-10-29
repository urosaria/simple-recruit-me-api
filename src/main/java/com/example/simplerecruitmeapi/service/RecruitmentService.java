package com.example.simplerecruitmeapi.service;

import com.example.simplerecruitmeapi.domain.Candidate;
import com.example.simplerecruitmeapi.dto.CandidateDto;

import java.util.Optional;

public interface RecruitmentService {
    Candidate saveCandidate(CandidateDto candidateInfo);
    Optional<CandidateDto> viewCandidate(Long id);
}

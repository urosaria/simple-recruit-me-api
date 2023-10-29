package com.example.simplerecruitmeapi.repository;

import com.example.simplerecruitmeapi.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}


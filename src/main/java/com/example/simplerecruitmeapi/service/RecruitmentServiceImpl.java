package com.example.simplerecruitmeapi.service;

import com.example.simplerecruitmeapi.domain.Candidate;
import com.example.simplerecruitmeapi.domain.NotificationWbh;
import com.example.simplerecruitmeapi.dto.CandidateDto;
import com.example.simplerecruitmeapi.dto.NotificationWhbDto;
import com.example.simplerecruitmeapi.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RecruitmentServiceImpl implements RecruitmentService {

    private final CandidateRepository candidateRepository;

    public RecruitmentServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate saveCandidate(CandidateDto candidateDto) {
        Candidate candidateEntity = convertToCandidateEntity(candidateDto);
        Candidate savedCandidate = candidateRepository.save(candidateEntity);

        if (savedCandidate != null) {
            sendNotification(candidateDto);
        }

        return savedCandidate;
    }

    @Override
    public Optional<CandidateDto> viewCandidate(Long id) {
        return candidateRepository.findById(id)
                .map(RecruitmentServiceImpl::convertToCandidateDtoEntity);
    }

    private void sendNotification(CandidateDto candidateDto){
        switch (candidateDto.getNotificationType()) {
            case MBL -> System.out.println("Sending SMS to: " + candidateDto.getMobileNumber());
            case EML -> System.out.println("Sending email to: " + candidateDto.getEmail());
            case WBH -> System.out.println("Sending webhook notification to: " + candidateDto.getNotificationWhbDto().getEndpoint());
        }
    }

    private Candidate convertToCandidateEntity(CandidateDto candidateDto) {
        Candidate candidate = new Candidate();
        candidate.setFirstName(candidateDto.getFirstName());
        candidate.setLastName(candidateDto.getLastName());
        candidate.setEmail(candidateDto.getEmail());
        candidate.setMobileNumber(candidateDto.getMobileNumber());
        candidate.setNotificationType(candidateDto.getNotificationType());

        if(candidateDto.getNotificationWhbDto() != null) {
            NotificationWbh notificationWbh = new NotificationWbh();
            notificationWbh.setEndpoint(candidateDto.getNotificationWhbDto().getEndpoint());
            notificationWbh.setMethod(candidateDto.getNotificationWhbDto().getMethod());
            notificationWbh.setAuthType(candidateDto.getNotificationWhbDto().getAuthentication());
            notificationWbh.setUid(candidateDto.getNotificationWhbDto().getUid());
            notificationWbh.setPwd(candidateDto.getNotificationWhbDto().getPwd());
            notificationWbh.setToken(candidateDto.getNotificationWhbDto().getToken());
            candidate.setNotificationWbh(notificationWbh);
        }

        return candidate;
    }

    public static CandidateDto convertToCandidateDtoEntity(Candidate candidate) {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(candidate.getFirstName());
        candidateDto.setLastName(candidate.getLastName());
        candidateDto.setEmail(candidate.getEmail());
        candidateDto.setMobileNumber(candidate.getMobileNumber());
        candidateDto.setNotificationType(candidate.getNotificationType());

        if(candidate.getNotificationWbh() != null) {
            NotificationWhbDto notificationWhbDto = new NotificationWhbDto();
            notificationWhbDto.setEndpoint(candidate.getNotificationWbh().getEndpoint());
            notificationWhbDto.setMethod(candidate.getNotificationWbh().getMethod());
            notificationWhbDto.setAuthentication(candidate.getNotificationWbh().getAuthType());
            notificationWhbDto.setUid(candidate.getNotificationWbh().getUid());
            notificationWhbDto.setPwd(candidate.getNotificationWbh().getPwd());
            notificationWhbDto.setToken(candidate.getNotificationWbh().getToken());
            candidateDto.setNotificationWhbDto(notificationWhbDto);
        }

        return candidateDto;
    }

}

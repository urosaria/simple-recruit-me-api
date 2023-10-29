package com.example.simplerecruitmeapi.service;

import com.example.simplerecruitmeapi.domain.Candidate;
import com.example.simplerecruitmeapi.domain.NotificationWbh;
import com.example.simplerecruitmeapi.dto.CandidateDto;
import com.example.simplerecruitmeapi.dto.NotificationWhbDto;
import com.example.simplerecruitmeapi.enums.NotificationType;
import com.example.simplerecruitmeapi.repository.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.simplerecruitmeapi.enums.AuthenticationType.BASIC;
import static com.example.simplerecruitmeapi.enums.HttpMethod.GET;
import static com.example.simplerecruitmeapi.enums.NotificationType.MBL;
import static com.example.simplerecruitmeapi.enums.NotificationType.WBH;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class RecruitmentServiceImplTest {

    @InjectMocks
    private RecruitmentServiceImpl recruitmentService;

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    void saveCandidate_MBL() {
        // Given
        CandidateDto mockCandidateDto = createMockCandidateDto(MBL);
        Candidate mockCandidate = createMockCandidate(MBL);

        // When
        when(candidateRepository.save(any(Candidate.class))).thenReturn(mockCandidate);

        Candidate savedCandidate = recruitmentService.saveCandidate(mockCandidateDto);

        // Then
        assertEquals(mockCandidate, savedCandidate);
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }

    @Test
    void saveCandidate_WBH() {
        // Given
        CandidateDto mockCandidateDto = createMockCandidateDto(WBH);
        Candidate mockCandidate = createMockCandidate(WBH);

        // When
        when(candidateRepository.save(any(Candidate.class))).thenReturn(mockCandidate);

        Candidate savedCandidate = recruitmentService.saveCandidate(mockCandidateDto);

        // Then
        assertEquals(mockCandidate, savedCandidate);
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }

    private CandidateDto createMockCandidateDto(Enum notificationType) {
        CandidateDto mockCandidateDto = new CandidateDto();
        mockCandidateDto.setFirstName("test");
        mockCandidateDto.setLastName("last");
        mockCandidateDto.setEmail("email@test.com");
        mockCandidateDto.setMobileNumber("0431 111 1111");
        mockCandidateDto.setNotificationType((NotificationType) notificationType);

        if (notificationType == WBH) {
            NotificationWhbDto notificationWhbDto = new NotificationWhbDto();
            notificationWhbDto.setEndpoint("endpoint");
            notificationWhbDto.setMethod(GET);
            notificationWhbDto.setAuthentication(BASIC);
            notificationWhbDto.setUid("testUid");
            notificationWhbDto.setPwd("testPwd");
            mockCandidateDto.setNotificationWhbDto(notificationWhbDto);
        }

        return mockCandidateDto;
    }

    private Candidate createMockCandidate(Enum notificationType) {
        Candidate mockCandidate = new Candidate();
        mockCandidate.setFirstName("test");
        mockCandidate.setLastName("last");
        mockCandidate.setEmail("email@test.com");
        mockCandidate.setMobileNumber("0431 111 1111");
        mockCandidate.setNotificationType((NotificationType) notificationType);

        if (notificationType == WBH) {
            NotificationWbh notificationWbh = new NotificationWbh();
            notificationWbh.setEndpoint("endpoint");
            notificationWbh.setMethod(GET);
            notificationWbh.setAuthType(BASIC);
            notificationWbh.setUid("testUid");
            notificationWbh.setPwd("testPwd");
            mockCandidate.setNotificationWbh(notificationWbh);
        }

        return mockCandidate;
    }
}

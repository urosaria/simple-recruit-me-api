package com.example.simplerecruitmeapi.validation;

import com.example.simplerecruitmeapi.dto.CandidateDto;
import com.example.simplerecruitmeapi.dto.NotificationWhbDto;
import com.example.simplerecruitmeapi.enums.AuthenticationType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("recruitmentValidator")
public class RecruitmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CandidateDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CandidateDto candidateDto = (CandidateDto) target;

        NotificationWhbDto whbDto = candidateDto.getNotificationWhbDto();

        if ("WBH".equals(candidateDto.getNotificationType().name()) && whbDto == null) {
            errors.reject("Notification-wbh is required");
            return;
        }

        if (whbDto.getAuthentication() == AuthenticationType.BASIC) {
            checkForEmptyField(errors, "notificationWhbDto.uid", whbDto.getUid(), "UID is required for BASIC authentication.");
            checkForEmptyField(errors, "notificationWhbDto.pwd", whbDto.getPwd(), "PWD is required for BASIC authentication.");
        } else if (whbDto.getAuthentication() == AuthenticationType.BEARER) {
            checkForEmptyField(errors, "notificationWhbDto.token", whbDto.getToken(), "Token is required for BEARER authentication.");
        }
    }

    private void checkForEmptyField(Errors errors, String fieldPath, String fieldValue, String errorMessage) {
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            errors.rejectValue(fieldPath, "InvalidField", errorMessage);
        }
    }

}

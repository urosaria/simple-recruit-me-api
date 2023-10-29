package com.example.simplerecruitmeapi.dto;

import com.example.simplerecruitmeapi.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CandidateDto {
    @NotBlank(message = "First name is required.")
    @JsonProperty("first-name")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @JsonProperty("last-name")
    private String lastName;

    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Mobile number is required.")
    @JsonProperty("mobile-number")
    private String mobileNumber;

    @NotNull(message = "Notification type is required.")
    @JsonProperty("notification-type")
    private NotificationType notificationType;

    @JsonProperty("notification-wbh")
    @Valid
    private NotificationWhbDto notificationWhbDto;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationWhbDto getNotificationWhbDto() {
        return notificationWhbDto;
    }

    public void setNotificationWhbDto(NotificationWhbDto notificationWhbDto) {
        this.notificationWhbDto = notificationWhbDto;
    }
}

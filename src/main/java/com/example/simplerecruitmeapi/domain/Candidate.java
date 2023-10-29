package com.example.simplerecruitmeapi.domain;

import com.example.simplerecruitmeapi.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CANDIDATE")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String mobileNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @OneToOne(cascade = CascadeType.ALL)
    private NotificationWbh notificationWbh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public NotificationWbh getNotificationWbh() {
        return notificationWbh;
    }

    public void setNotificationWbh(NotificationWbh notificationWbh) {
        this.notificationWbh = notificationWbh;
    }
}

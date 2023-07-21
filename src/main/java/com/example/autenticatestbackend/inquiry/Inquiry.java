package com.example.autenticatestbackend.inquiry;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table

public class Inquiry {
    @Id
    @SequenceGenerator(
            name = "inquiry_sequence",
            sequenceName = "inquiry_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inquiry_sequence"
    )
    private Long id;

    @NotNull
    private Long deviceId;

    private String justification;

    private LocalDateTime date;

    @NotBlank
    private String status;

    public Inquiry() {
    }

    public Inquiry(Long deviceId, String justification) {
        this.deviceId = deviceId;
        this.justification = justification;
    }

    public Inquiry(Long id, Long deviceId, String justification, LocalDateTime date, String status) {
        this.id = id;
        this.deviceId = deviceId;
        this.justification = justification;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

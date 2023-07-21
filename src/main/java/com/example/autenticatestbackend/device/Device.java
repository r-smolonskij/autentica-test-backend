package com.example.autenticatestbackend.device;


import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table
public class Device {
    @Id
    @SequenceGenerator(
            name = "device_sequence",
            sequenceName = "device_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_sequence"
    )
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String name;

    @NotBlank
    private String parameters;

    public Device() {
    }

    public Device(String name, String parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public Device(Long id, String name, String parameters) {
        this.id = id;
        this.name = name;
        this.parameters = parameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}

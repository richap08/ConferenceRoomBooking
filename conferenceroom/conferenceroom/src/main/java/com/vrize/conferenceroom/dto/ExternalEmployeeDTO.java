package com.vrize.conferenceroom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalEmployeeDTO {

    private String name;
    private String email;

    public String generateEmployeeCode() {
        if (email == null) {
            return "EXT-UNKNOWN";
        }
        return "EXT-" + Math.abs(email.hashCode());
    }
}
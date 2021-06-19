package com.yudin.project.messanger.dto;

import com.yudin.project.messanger.enums.RegistrationStatus;

public class RegisterResult {
    private RegistrationStatus registrationStatus;

    public RegisterResult(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }
}

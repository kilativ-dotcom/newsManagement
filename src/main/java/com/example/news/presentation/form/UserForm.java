package com.example.news.presentation.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    @NotNull
    @Size(min=5, max=30)
    @Pattern(
            regexp = "^[A-Za-z0-9]+$",
            message = "Allowed only A-Z a-z 0-9"
    )
    private String username;

    @NotNull
    @Size(min=8, max=30)
    private String password;

    @NotNull
    @Size(min=8, max=30)
    private String passwordRepeat;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}

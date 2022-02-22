package ru.netology.entities;



public class RegistrationDto {

    private final String login;
    private final String password;
    private final String status;

    public String getName() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public RegistrationDto(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }


}

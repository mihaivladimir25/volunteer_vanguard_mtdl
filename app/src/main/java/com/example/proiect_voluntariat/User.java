package com.example.proiect_voluntariat;

public class User {
    private String username;
    private String name;
    private String email;
    private String password;
    private String address;
    private String specialization;
    private String interests;
    private String skills;

    public User(String username, String name, String email, String password, String address, String specialization, String interests, String skills) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.specialization = specialization;
        this.interests = interests;
        this.skills = skills;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getInterests() {
        return interests;
    }

    public String getSkills() {
        return skills;
    }
}

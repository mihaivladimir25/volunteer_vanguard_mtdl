package com.example.proiect_mtdl;

public class ProfileData {
    private String name;
    private String address;
    private String specialization;
    private String interests;
    private String skills;

    public ProfileData(String name, String address, String specialization, String interests, String skills) {
        this.name = name;
        this.address = address;
        this.specialization = specialization;
        this.interests = interests;
        this.skills = skills;
    }

    public String getName() {
        return name;
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

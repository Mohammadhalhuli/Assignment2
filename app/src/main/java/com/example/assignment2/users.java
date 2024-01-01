package com.example.assignment2;

public class users {
    private String email;
    private String password;
    private String username;
    private String imagePath;

    public users(String email, String password, String username, String imagePath) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.imagePath = imagePath;
    }

    public users() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "users{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

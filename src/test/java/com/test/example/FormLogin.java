package com.test.example;

public class FormLogin {
   private String username;
   private String usernameXPath;
   private String password;
   private String passwordXPath;
   private String buttonXPath;

    public FormLogin(String username, String usernameXPath, String password, String passwordXPath, String buttonXPath) {
        this.username = username;
        this.usernameXPath = usernameXPath;
        this.password = password;
        this.passwordXPath = passwordXPath;
        this.buttonXPath = buttonXPath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameXPath() {
        return usernameXPath;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordXPath() {
        return passwordXPath;
    }

    public String getButtonXPath() {
        return buttonXPath;
    }
    public static class Builder {
        private String username;
        private String usernameXPath;
        private String password;
        private String passwordXPath;
        private String buttonXPath;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setUsernameXPath(String usernameXPath) {
            this.usernameXPath = usernameXPath;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setPasswordXPath(String passwordXPath) {
            this.passwordXPath = passwordXPath;
            return this;
        }

        public Builder setButtonXPath(String buttonXPath) {
            this.buttonXPath = buttonXPath;
            return this;
        }

        public FormLogin build() {
            return new FormLogin(username, usernameXPath, password, passwordXPath, buttonXPath);
        }
    }
}
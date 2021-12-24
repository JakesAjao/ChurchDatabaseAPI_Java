package com.jakesajao.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

    public class LoginFormDto {
        @NotNull
        @Size(min = 2, max = 30)
        private String email;

        @NotNull
        @Min(5)
        private String password;

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String userName) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String toString() {
            return "LoginForm(Email: " + this.email + ", Password: " + this.password + ")";
        }
    }


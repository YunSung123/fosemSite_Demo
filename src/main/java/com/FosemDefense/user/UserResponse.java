package com.FosemDefense.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class JoinDTO {
        private Integer id;
        private String username;
        private String email;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }

    @Data
    public static class SessionDTO extends User {
        // password 처럼 민감한 정보는 내려주지 않는다.
        public SessionDTO(User user) {
            super.setId(user.getId());
            super.setUsername(user.getUsername());
            super.setEmail(user.getEmail());
        }
    }
}

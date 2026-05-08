package com.FosemDefense.board;

import com.FosemDefense.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

public class BoardRequest {

        @Data
        @Builder
        public static class SaveDTO {

            private String title;
            private String content;

            public Board toEntity(User user) {
                return Board.builder()
                        .title(title)
                        .user(user)
                        .content(content)
                        .build();
            }


            public void validate() {
                if(title == null || title.trim().isEmpty()) {
                    throw new IllegalArgumentException("제목은 필수입니다.");
                }
                if(content == null || content.length() < 3) {
                    throw new IllegalArgumentException("내용은 3글자 이상 작성해야 합니다.");
                }
            }
        }
}

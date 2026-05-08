package com.FosemDefense.board;

import lombok.Getter;

import java.time.LocalDateTime;

public class BoardResponse {

    @Getter
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private String username;
        private LocalDateTime createdAt;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.username = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt();
        }
    }
}

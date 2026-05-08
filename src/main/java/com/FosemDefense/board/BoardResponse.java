package com.FosemDefense.board;

import com.FosemDefense.util.MyDateUtil;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardResponse {

    // 게시글 목록 응답 DTO
    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        // username 평탄화 작업 : SSR 설계시 권장 방법, CSR 일 경우는 계층구조로 내려주는게 좋다
        private String username;
        private String createdAt;

        public ListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            // 방어적 코드 활용
            if (board.getUser() != null) {
                this.username = board.getUser().getUsername();
            }
            if (board.getCreatedAt() != null) {
                this.createdAt = MyDateUtil.timestampFormat(board.getCreatedAt());
            }
        }
    }  // end of ListDTO inner class

    // 게시글 상세 보기 응답 DTO
    @Data
    public static class DetailDTO {
        private Integer id; // board PK
        private String title;
        private String content;
        private String username;
        private Integer userId; //  user PK

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            if(board.getUser() != null) {
                this.username = board.getUser().getUsername();
                this.userId = board.getUser().getId();
            }
        }

    } // end of DetailDTO inner class
}

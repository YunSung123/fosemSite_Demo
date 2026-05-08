package com.FosemDefense.board;

import com.FosemDefense._core.errors.Exception403;
import com.FosemDefense.user.User;
import com.FosemDefense.util.MyDateUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "board_tb")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    // 외래 키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getTime(){
        return MyDateUtil.timestampFormat(createdAt);
    }

    // 수정 편의 기능 만들기
    public void update(BoardRequest.UpdateDTO updateDTO) {
        // this.username = updateDTO.getUsername(); 삭제 예정
        this.title = updateDTO.getTitle();
        this.content = updateDTO.getContent();
    }

    // 편의 기능 - 게시글 소유자 확인을 위한 기능 추가
    public boolean isOwner(Integer sessionUserId) {
        if(!this.user.getId().equals(sessionUserId)) {
            throw new Exception403("본인이 작성한 게시글이 아닙니다");
        }
        return true;
    }
}
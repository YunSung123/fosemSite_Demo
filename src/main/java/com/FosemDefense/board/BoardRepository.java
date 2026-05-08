package com.FosemDefense.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// 인터페이스
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 1. 등록 및 수정 save(Board entity)
    // 2. 단건 조회 : findById(Integer id)
    // 3. 전체 조회 : findAll()
    // 4. 삭제 : deleteById(Integer id)
    // 5. 데이터 개수: count()
    // 6. 존재 여부 확인: existsById(Integer id)

    // ID로 조회시 사용자 정보도 함께 가져오기
    @Query("select b from Board b join fetch b.user where b.id = :id")
    Optional<Board> findByIdJoinUser(Integer id);

    // 전체 게시글 조회
    @Query("""
        SELECT b FROM Board b JOIN FETCH b.user ORDER BY b.id DESC
            """)
    List<Board> findBoardList();
}

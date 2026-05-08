package com.FosemDefense.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b join fetch b.user where b.id = :id")
    Optional<Board> findByIdJoinUser(Integer id);

    @Query("""
        SELECT b FROM Board b JOIN FETCH b.user ORDER BY b.id DESC
            """)
    List<Board> findBoardList();
}

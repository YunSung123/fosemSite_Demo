package com.FosemDefense.board;

import com.FosemDefense._core.errors.Exception404;
import com.FosemDefense.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findBoardList() {
        List<Board> boardList = boardRepository.findBoardList();
        return boardList;
    }

    public Board findById(Integer id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

    public BoardResponse.DetailDTO findDetailPage(Integer id) {

        Board boardEntity = boardRepository.findByIdJoinUser(id).orElseThrow(() -> {
            return new Exception404("해당하는 게시글을 찾을 수 없습니다");
        });

        return new BoardResponse.DetailDTO(boardEntity);
    }

    @Transactional
    public void save(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        Board board = saveDTO.toEntity(sessionUser);
        boardRepository.save(board);
    }

    @Transactional
    public void delate(Integer id, User sessionUser) {
        Board boardEntity = boardRepository.findById(id).orElseThrow(
                () -> new Exception404("게시글을 찾을 수 없습니다")
        );
        boardEntity.isOwner(sessionUser.getId());
        boardRepository.deleteById(id);
    }
}

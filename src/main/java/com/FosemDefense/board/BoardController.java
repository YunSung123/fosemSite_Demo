package com.FosemDefense.board;

import com.FosemDefense.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 포샘디펜스 메인 화면 요청
     *
     * @return 메인 페이지 반환
     * 주소 설계 : http://localhost:8080/
     * 주소 설계 : http://localhost:8080/index
     */
    @GetMapping({"/", "/index"})
    public String showIndex(Model model) {
        List<Board> boardList = boardService.findBoardList();
        model.addAttribute("boardList", boardList);
        return "index";
    }

    /**
     * 게시글 상세 화면 요청
     *
     * @return 게시글 상세 화면 반환
     * 주소 설계 : http://localhost:8080/board/1
     */
    @GetMapping("/board/{id}")
    public String showDetail(@PathVariable(name = "id") Integer id, Model model) {
        BoardResponse.DetailDTO board = boardService.findDetailPage(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    /**
     * 게시글 작성 화면 요청
     *
     * @return 게시글 작성 화면 반환
     * 주소 설계 : http://localhost:8080/board/save-form
     */
    @GetMapping("/board/save-form")
    public String showSaveForm(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 인가 처리 : 로그인하지 않은 사용자는 글쓰기 화면 접근 불가
        if (sessionUser == null) {
            return "redirect:/user/login-form";
        }

        return "board/save-form";
    }

    /**
     * 게시글 작성 처리 요청
     *
     * @return 메인 화면으로 리다이렉트
     * 주소 설계 : http://localhost:8080/board/save
     */
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        saveDTO.validate();
        boardService.save(saveDTO, sessionUser);
        return "redirect:/";
    }

    // 삭제 기능 요청
    @PostMapping("/board/{id}/delete")
    public String deleteProc(@PathVariable(name = "id") Integer id, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.delate(id, sessionUser);
        return "redirect:/";
    }


    // http://localhost:8080/board/1/update-form
    // 게시글 수정 화면 요청
    @GetMapping("/board/{id}/update-form")
    public String updateFormPage(@PathVariable(name = "id") Integer id,
                                 Model model,
                                 HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO detailDTO = boardService.updateForm(id, sessionUser);
        model.addAttribute("board", detailDTO);
        return "board/update-form";
    }


    @PostMapping("/board/{id}/update")
    public String updateProc(@PathVariable(name = "id") Integer id,
                             BoardRequest.UpdateDTO updateDTO,
                             HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        updateDTO.validate();
        boardService.update(id, updateDTO, sessionUser);
        return "redirect:/board/" + id;
    }
}
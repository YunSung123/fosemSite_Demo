package com.FosemDefense.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller // IoC
@RequiredArgsConstructor // DI 처리
public class UserController {

    private final UserService userService;

    /**
     * 로그인 화면 요청
     *
     * @return 로그인 화면 반환
     * 주소 설계 : http://localhost:8080/user/login-form
     */
    @GetMapping("/user/login-form")
    public String showLoginForm() {
        return "user/login-form";
    }

    /**
     * 로그인 처리 요청
     *
     * @return 메인 화면 반환
     * 주소 설계 : http://localhost:8080/user/login
     */
    @PostMapping("/user/login")
    public String login(UserRequest.LoginDTO reqLoginDTO, HttpSession session) {
        reqLoginDTO.validate();

        UserResponse.SessionDTO sessionDTO = userService.loginUser(reqLoginDTO);
        session.setAttribute("sessionUser", sessionDTO);

        return "redirect:/";
    }

    /**
     * 로그아웃 처리 요청
     *
     * @return 메인 화면 반환
     * 주소 설계 : http://localhost:8080/user/logout
     */
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        // 세션 메모리에 내 정보를 없애 버림
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 회원 가입 화면 요청
     *
     * @return 회원 가입 화면 반환
     * 주소 설계 - http://localhost:8080/join-form
     */
    @GetMapping("/user/join-form")
    public String joinFormPage() {
        return "user/join-form";
    }

    /**
     * 회원 가입 처리 요청
     *
     * @return 메인 페이지 반환
     * 주소 설계 - http://localhost:8080/user/join
     */
    @PostMapping("/user/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        joinDTO.validate();
        userService.joinUser(joinDTO);
        return "redirect:/";
    }

    /**
     * 회원정보 보기 요청
     *
     * @return 회원 정보 페이지 반환
     * http://localhost:8080/user/update-form
     */
    @GetMapping("/user/update-form")
    public String updateFormPage(HttpSession session, Model model) {
        UserResponse.SessionDTO sessionUser = (UserResponse.SessionDTO) session.getAttribute("sessionUser");
        UserResponse.SessionDTO sessionDTO = userService.showUserInfo(sessionUser.getId());
        model.addAttribute("user", sessionDTO);
        return "user/update-form";
    }

    /**
     * 회원정보 처리 요청
     *
     * @return 회원 정보 페이지 반환
     * http://localhost:8080/user/update-form
     */
    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO updateDTO, HttpSession session) {
        updateDTO.validate();
        UserResponse.SessionDTO sessionUser = (UserResponse.SessionDTO) session.getAttribute("sessionUser");
        userService.updateUser(sessionUser.getId(), updateDTO, session);
        return "redirect:/";
    }
}

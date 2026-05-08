package com.FosemDefense.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
     * 회원 가입 화면 요청
     *
     * @return 회원 가입 화면 반환
     * 주소 설계 - http://localhost:8080/join-form
     */
    @GetMapping("/join-form")
    public String joinFormPage() {

        return "user/join-form";
    }

    /**
     * 회원 가입 처리 요청
     *
     * @return 메인 페이지 반환
     * 주소 설계 - http://localhost:8080/user/join
     */
    @PutMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        joinDTO.validate();
        userService.joinUser(joinDTO);
        return "redirect:/";
    }


//
//
//    // 프로필 수정 기능 요청
//    @PostMapping("/user/update")
//    public String updateProc(UserRequest.UpdateDTO updateDTO, HttpSession session) {
//        updateDTO.validate();
//        UserResponse.SessionDTO sessionUser = (UserResponse.SessionDTO) session.getAttribute("sessionUser");
//        userService.회원정보수정(sessionUser.getId(), updateDTO, session);
//        return "redirect:/";
//    }
//
//    // 프로필 화면 요청
//    @GetMapping("/user/update-form")
//    public String updateFormPage(HttpSession session, Model model) {
//        UserResponse.SessionDTO sessionUser = (UserResponse.SessionDTO) session.getAttribute("sessionUser");
//        UserResponse.SessionDTO sessionDTO = userService.회원정보수정화면(sessionUser.getId());
//        model.addAttribute("user", sessionDTO);
//        return "user/update-form";
//    }
//
//    // 로그인 화면 요청
//    // 주소 설계 - http://localhost:8080/login-form
//    @GetMapping("/login-form")
//    public String loginFormPage() {
//        // 인증 검사 x , 유효성 x
//        return "user/login-form";
//    }
//
//    // 로그인 기능 요청
//    @PostMapping("/login")
//    public String loginProc(UserRequest.LoginDTO reqLoginDTO, HttpSession session) {
//        // 인증 검사 x, 유효성 검사 o
//        reqLoginDTO.validate();
//        UserResponse.SessionDTO sessionDTO = userService.로그인(reqLoginDTO);
//        session.setAttribute("sessionUser", sessionDTO);
//        return "redirect:/";
//    }
//
//
//    // 로그아웃 기능 요청
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        // 세션 메모리에 내 정보를 없애 버림
//        session.invalidate();
//        return "redirect:/";
//    }
//
//
//
//    // 회원 가입 기능 요청
//    // 주소 설계 - http://localhost:8080/join
//    @PostMapping("/join")
//    public String joinProc(UserRequest.JoinDTO joinDTO) {
//        //  인증검사 x, 유효성 검사 하기 o
//        joinDTO.validate();
//        userService.회원가입(joinDTO);
//        return "redirect:/login-form";
//    }

}

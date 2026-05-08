package com.FosemDefense._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    // 400 Bad Request
// 확인 주소 예시: http://localhost:8080/err/400
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }

    // 401 Unauthorized
// 확인 주소 예시: http://localhost:8080/err/401
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }

    // 403 Forbidden
// 확인 주소 예시: http://localhost:8080/err/403
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception403 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/403";
    }

    // 404 Not Found
// 확인 주소 예시: http://localhost:8080/err/404
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }

    // 500 Internal Server Error
// 확인 주소 예시: http://localhost:8080/err/500
    @ExceptionHandler(Exception500.class)
    public String ex500(Exception500 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }

    // 기타 RuntimeException
// 확인 주소 예시: 직접 RuntimeException을 발생시키는 테스트 컨트롤러 필요
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }
}

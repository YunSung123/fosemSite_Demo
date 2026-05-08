package com.FosemDefense._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice // IoC
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)
    @ResponseBody
    public String ex401(Exception401 e, HttpServletRequest request) {
        String script = """
                <script>
                    alert('%s');
                    location.href='/login-form';
                </script>
                """.formatted(e.getMessage());
        return script;
    }

    @ExceptionHandler(Exception403.class)
    @ResponseBody // 파일 찾지 말고 데이터 반환
    public String ex403(Exception403 e, HttpServletRequest request) {
        String script = """
                <script>
                    alert('%s');
                    history.back();
                </script>
                """.formatted(e.getMessage());
        return script;
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }

    @ExceptionHandler(Exception500.class)
    public String ex500(Exception500 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }

    // 기타 모든 RuntimeException 처리 (최후의 보루) 해당 메세지는 로그로 출력
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.warn("=== 예상치 못한 런타임 에러 발생 ===");
        log.warn("요청 URL: {}", request.getRequestURL());
        log.warn("에러메시지: {}", e.getMessage());
        request.setAttribute("msg", "시스템 오류가 발생했습니다. 관리자에게 문의해주세요");
        return "err/500";
    }
}

package com.FosemDefense.user;

import com.FosemDefense._core.errors.Exception400;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    // 회원 가입
    private final UserRepository userRepository;
    @Transactional
    public UserResponse.JoinDTO joinUser(UserRequest.JoinDTO joinDTO) {

        // 람다식 사용
        // ifPresent() 박스안에 User가 있으면 던져라.
        userRepository.findByUsername(joinDTO.getUsername()).ifPresent(user -> {
            throw new Exception400("이미 존재하는 사용자 이름입니다");
        });

        User user = joinDTO.toEntity();
        User savedUserEntity = userRepository.save(user);
        return new UserResponse.JoinDTO(savedUserEntity);
    }

    // 로그인
    public UserResponse.SessionDTO loginUser(UserRequest.LoginDTO loginDTO) {
        User userEntity = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword())
                .orElseThrow(() -> {
                    return new Exception400("사용자명 또는 비밀번호가 올바르지 않습니다");
                });
        return new UserResponse.SessionDTO(userEntity);
    }

}

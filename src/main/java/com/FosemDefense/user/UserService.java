package com.FosemDefense.user;

import com.FosemDefense._core.errors.Exception400;
import com.FosemDefense._core.errors.Exception404;
import jakarta.servlet.http.HttpSession;
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

    /**
     * 사용자 정보 조회 (프로필 정보 보기 활용)
     * @param id (User PK)
     * @return UserEntity
     */
    public UserResponse.SessionDTO showUserInfo(Integer id) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new Exception404("사용자 정보를 찾을 수 없습니다");
        });
        return new UserResponse.SessionDTO(userEntity);
    }


    /**
     * 사용자 정보 수정 처리 (프로필 업데이트)
     * @param id  (User PK)
     * @param updateDTO (사용자가 요청한 데이터)
     * @return User
     */
    @Transactional
    public UserResponse.SessionDTO updateUser(Integer id, UserRequest.UpdateDTO updateDTO, HttpSession session) {

        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new Exception404("사용자 정보를 찾을 수 없습니다"));
        // 더티 체킹 활용
        userEntity.update(updateDTO);
        UserResponse.SessionDTO sessionDTO = new UserResponse.SessionDTO(userEntity);
        // 세션 동기화 처리
        session.setAttribute("sessionUser", sessionDTO);
        return sessionDTO;
    }

}

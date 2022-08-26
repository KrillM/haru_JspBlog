package com.blog.service;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean 등록을 해줌, IoC 해준다
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public void createCrew(User user){
        String rawPassword = user.getPassword(); // 원래대로 적으면
        String encPassword = encoder.encode(rawPassword); // 여기서 해쉬화(암호화)
        user.setPassword(encPassword);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Transactional
    public void updateCrew(User user){
        // 수정시 영속성 컨텍스트 User 오브젝트 영속화, 영속화된 User 오브젝트 수정
        // select 해서 User 오브젝트를 db로부터 가져오는 이유는 영속화 하기 위해서다
        // 영속화된 오브젝트를 변경하면 자동으로 db에 updat문을 날려준다
        User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setEmail(user.getEmail());
        // 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 자동으로 됨
        // 영속화된 persistence 객체의 변화가 감지되면 더티체킹되어 update문을 날려줌
    }
}
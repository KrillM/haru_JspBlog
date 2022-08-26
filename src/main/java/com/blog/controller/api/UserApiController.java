package com.blog.controller.api;

import com.blog.config.auth.PrincipalDetail;
import com.blog.dto.ResponseDto;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: call save");
        // 실제로 db에 insert를 하고 아래에서 return이 되면 OK!
        userService.createCrew(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.updateCrew(user);
        // 여기서 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐으나 세션값은 변경되지 않은 상태
        // 따라서 직접 세션값을 변경한다
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

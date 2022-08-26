package com.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter // 영상에서는 @Data라고 하지만 @Data = Getter+Setter라고 봐도 됩니다
@Setter // 에러 발생한다면 @Data로 변경하면 될 거 같아요
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 프로젝트에서 연결된 db의 넘버링 전략을 따라간다
    private Long id; // auto_increment

    @Column(nullable = false, length = 30, unique = true)
    // unique: 중복 제거
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}

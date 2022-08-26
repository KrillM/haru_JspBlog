package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter // 영상에서는 @Data라고 하지만 @Data = Getter+Setter라고 봐도 됩니다
@Setter // 에러 발생한다면 @Data로 변경하면 될 거 같아요
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 사용할때 씁니다
    private String content; // 썸머노트 라이브러리<html>태그가 섞여서 디자인 됩니다.

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER)  // Many = Board, One = User
    @JoinColumn(name="userId")
    private User user; // DB는 오브젝트를 저장할 수 없으나 FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    // mappedBy 연관관계의 주인(외래키)이 아니므로 DB에 칼럼을 만들지 마세요.
    // cascade에서 CascadeType.REMOVE 해야 댓글이 달린 게시글을 삭제할 수 있습니다.
    @JsonIgnoreProperties({"board"})
    // board 호출 후 reply에서 board 부분으로 가면 board인 줄 알고 다시 board 호출한다.
    // 결국 무한창조(루프)에 빠지므로 댓글을 작성할 때 @JsonIgnoreProperties 통해 reply의 board를 건너뛴다.
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private LocalDateTime createDate;
}
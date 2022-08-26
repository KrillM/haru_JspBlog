package com.blog.repository;

import com.blog.dto.ReplySaveRequestDto;
import com.blog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 방법 2
    @Modifying
    @Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(Long userId, Long boardId, String content); // 업데이트된 행의 개수를 리턴해줌.
    // ReplySaveRequestDto를 직접 넣을 수 없어서 각각 직접 넣어줬습니다.
}

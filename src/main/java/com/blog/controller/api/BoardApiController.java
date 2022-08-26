package com.blog.controller.api;

import com.blog.config.auth.PrincipalDetail;
import com.blog.dto.ReplySaveRequestDto;
import com.blog.dto.ResponseDto;
import com.blog.entity.Board;
import com.blog.entity.Reply;
import com.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    // 게시글
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.createPost(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id){
        boardService.deletePost(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id,@RequestBody Board board){
        boardService.updatePost(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 댓글

    // 데이터 받을 때 컨트롤러에서 dto를 만들어서 받아야 한다
    @PostMapping("api/board/{id}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.createReply(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable Long replyId) {
        boardService.replyDelete(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

package com.blog.service;

import com.blog.dto.ReplySaveRequestDto;
import com.blog.entity.Board;
import com.blog.entity.Reply;
import com.blog.entity.User;
import com.blog.repository.BoardRepository;
import com.blog.repository.ReplyRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean 등록을 해줌, IoC 해준다
public class BoardService {

    // 게시글
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void createPost(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> postList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board postDetail(Long id){
        return boardRepository.findById(id)
            .orElseThrow(()->{
                return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다");
            });
    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id, Board requestBoard){
        Board board = boardRepository.findById(id)
            .orElseThrow(()->{
                return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다");
            }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료 시(서비스 종료시) 트랜잭선 종료. 이때 더티체킹 발생 -> 자동 업데이트됨(db flush)
    }

    // 댓글
    // 방법 1때 사용
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void createReply(ReplySaveRequestDto replySaveRequestDto){

        // 방법 1
//        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패: 사용자 id를 찾을 수 없습니다");
//        }); // 영속화 완료
//
//        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다");
//        }); // 영속화 완료
//
//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();

//        replyRepository.save(reply);

        // 방법 2
        replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
    }

    @Transactional
    public void replyDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
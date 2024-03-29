package com.blog.controller;

import com.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
                        Model model, Pageable pageable){
        model.addAttribute("boards", boardService.postList(pageable));
        return "index"; // viewResolver 작동!
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model){
        model.addAttribute("board",boardService.postDetail(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model){
        model.addAttribute("board", boardService.postDetail(id));
        return "board/updateForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}

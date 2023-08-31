package com.hyeok.blog.test1;

import com.hyeok.blog.model.Board;
import com.hyeok.blog.model.Reply;
import com.hyeok.blog.repository.BoardRepository;
import com.hyeok.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get();
    }

    @GetMapping("/test/reply")
    public List<Reply> getReply() {
        return replyRepository.findAll();
    }
}

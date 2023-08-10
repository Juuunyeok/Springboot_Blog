package com.hyeok.blog.repository;

import com.hyeok.blog.model.Board;
import com.hyeok.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository  / 생략 가능하다.
public interface BoardRepository extends JpaRepository<Board, Integer> {

}

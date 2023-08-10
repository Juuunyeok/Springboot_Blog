package com.hyeok.blog.test1;

import com.hyeok.blog.model.RoleType;
import com.hyeok.blog.model.User;
import com.hyeok.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// date  를 리턴
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입
    private UserRepository userRepository;

    // save 함수는 id 를 전달하지 않으면 insert 를 해주고
    // save 함수는 id 를 전달할 때 해당 id 에 대한 데이터가 있을 시 update 를 해주고
    // save 함수는 id 를 전달할 때 해당 id 에 대한 데이터가 없을 시 insert 를 해준다.
    // email, password

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 id 는 db에 없습니다.";
        }
        return "데이터가 삭제 되었습니다. id: "+id;
    }

    @Transactional // save 를 하지 않아도 @Transactional 을 이용하여 update 를 할 수 있다. // 함수 종료 시에 자동 commit 이 됨
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 =>
        System.out.println("Id: "+id);                                            // JavaObject(MessageConverter 의 Jackson
        System.out.println("password: "+requestUser.getPassword());               // 라이브러리가 변환해서 받아준다.
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패했습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);

        // 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴 받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page <User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // id 를 찾을 때 데이터베이스에서 못 찾아올 경우 user 가 null 이 되기 때문에
        // return 값이 null 이 되어 문제를 야기할 수 있기 때문에
        // Optional 로 User 객체를 감싸서 가져오도록하여 null 값인지 판단한다.
        // 람다식
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        // 요청: 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹 브라우저가 이해할 수 있는 데이터로) -> json
        // 스프링부트 = MessageConverter 라는 것이 응답 시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json 으로 변환해서 브라우저에게 던져준다.
        return user;
    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http 의 body 에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) { // key = value (약속된 규칙)
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}

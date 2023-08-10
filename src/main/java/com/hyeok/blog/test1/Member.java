package com.hyeok.blog.test1;

import lombok.*;

@Data
@NoArgsConstructor
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder // 매개변수를 선택해서 쓰고 싶을떄 => Member m = Member.builder().id(a).build();
            // 추가적으로 생성자를 생성하지 않아도 , 순서 상관 x
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

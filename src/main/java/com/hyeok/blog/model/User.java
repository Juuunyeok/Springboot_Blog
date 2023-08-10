package com.hyeok.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


// ORM -> Java Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MYSQL 에 테이블이 생성된다.
// @DynamicInsert / insert 시에 null 인 필드를 제외시켜준다.
public class User {

    @Id /// primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment


    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'user'")
    // DB는 RoleType 이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // 원래는 Enum 을 쓰는게 좋다. // admin, user,

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}

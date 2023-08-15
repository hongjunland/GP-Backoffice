package com.example.demo.user.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private int position;
}

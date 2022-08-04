package com.teamdev.licenseservice.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class Account {

    @Id
    @Column(name = "account_id", columnDefinition ="VARCHAR(20)", updatable = false)
    @Size(max = 20)
    @Comment("계정ID")
    private String id;

    @Column(name = "password", nullable = false)
    @Size(max = 60)
    @Comment("비밀번호")
    private String password;

    //TODO: 2022.08.03.영속성관리 CASCADE 어떻게 할지 고민하고...
    //  AccountService.signUp에서 ROLE 값 중복 저장 처리 해결하기.
    @ManyToMany
    @JoinTable(
            name="account_role",
            joinColumns={@JoinColumn(name = "account_id")},
            inverseJoinColumns={@JoinColumn(name="role_id")})
    private Set<Role> roles;

    @Builder
    public Account(String id, String password, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.roles = roles;
    }
}

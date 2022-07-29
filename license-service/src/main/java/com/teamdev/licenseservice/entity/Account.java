package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class Account {

    @Id
    @Column(name = "account_id", columnDefinition ="INT(11) UNSIGNED", updatable = false)
    @Size(max = 20)
    @Comment("계정ID")
    private String id;

    @Column(name = "password", nullable = false)
    @Size(max = 20)
    @Comment("비밀번호")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    @Comment("역할ID")
    private Role role;

    @Builder
    public Account(String id, String password, Role role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }
}

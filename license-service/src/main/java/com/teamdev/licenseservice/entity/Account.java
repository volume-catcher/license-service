package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
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
}

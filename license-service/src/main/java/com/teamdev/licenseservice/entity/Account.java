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
@AttributeOverride(name = "id", column = @Column(name = "account_id"))
public class Account extends BaseEntity {

    @Column(name = "account_name", updatable = false, nullable = false)
    @Size(max = 20)
    @Comment("계정이름")
    private String name;

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
    public Account(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}

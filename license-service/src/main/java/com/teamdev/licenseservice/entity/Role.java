package com.teamdev.licenseservice.entity;

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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", columnDefinition ="INT(11) UNSIGNED")
    @Comment("역할ID")
    private Integer id;

    @Column(name = "role_name", unique = true, nullable = false)
    @Size(max = 15)
    @Comment("역할이름")
    private String name;

    @Builder
    public Role(String name) {
        this.name = name;
    }

}

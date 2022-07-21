package com.teamdev.licenseservice.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", columnDefinition = "INT(10) UNSIGNED")
    @Comment("역할ID")
    private Integer id;

    @Column(name = "role_name", nullable = false)
    @Size(max = 15)
    @Comment("역할이름")
    private String name;


}

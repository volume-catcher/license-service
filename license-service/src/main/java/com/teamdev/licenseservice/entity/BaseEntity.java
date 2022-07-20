package com.teamdev.licenseservice.entity;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition ="INT(10) UNSIGNED")
    private Integer id;

}

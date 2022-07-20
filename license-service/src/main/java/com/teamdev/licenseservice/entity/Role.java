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
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends BaseEntity {

    @Column(name = "role_name", nullable = false)
    @Size(max = 15)
    @Comment("역할이름")
    private String name;

    @Builder
    public Role(String name) {
        this.name = name;
    }

}

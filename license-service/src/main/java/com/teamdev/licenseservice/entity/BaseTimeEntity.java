package com.teamdev.licenseservice.entity;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(name = "create_at", updatable = false, nullable = false)
    @Comment("생성일시")
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    @Comment("수정일시")
    @LastModifiedDate
    private LocalDateTime updateAt;
}

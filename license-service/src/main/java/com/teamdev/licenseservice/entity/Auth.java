package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Auth {

    @Id
    @Column(name = "auth_id", columnDefinition ="INT(10) UNSIGNED")
    @Comment("인증ID")
    private Integer id;

    @Column(name = "device", columnDefinition ="BINARY(16)", nullable = false)
    @Comment("기기일련번호")
    private UUID device;

    @Column(name = "is_activated", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    @Comment("활성여부")
    private Boolean isActivated;

    @Column(name = "create_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Comment("생성일시")
    private LocalDateTime createAt;

    @Column(name = "update_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Comment("수정일시")
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "license_key", nullable = false)
    @JsonIgnore
    @Comment("라이선스키")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    @Comment("제품ID")
    private Product product;

}

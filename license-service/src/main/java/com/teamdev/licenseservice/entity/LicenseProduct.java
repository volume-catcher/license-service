package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "license_product")
@NoArgsConstructor
public class LicenseProduct {

    @Id
    @Column(name = "license_product_id", columnDefinition ="INT(10) UNSIGNED")
    @Comment("라이선스_제품ID")
    private Integer id;

    @Column(name = "num_of_auth_available", columnDefinition ="INT(10) UNSIGNED DEFAULT 1", nullable = false)
    @Comment("제품별 인증 가능 횟수")
    private Integer numOfAuthAvailable;

    @Column(name = "is_activated", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    @Comment("활성여부")
    private Boolean isActivated;

    @Column(name = "create_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Comment("생성일시")
    private LocalDateTime createAt;

    @Column(name = "update_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Comment("수정일시")
    private LocalDateTime updateAt;

    @Column(name = "expire_at", nullable = false)
    @Comment("만료일시")
    private LocalDateTime expireAt;

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

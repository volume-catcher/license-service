package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "license_product")
public class LicenseProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_product_id", columnDefinition ="INT(11) UNSIGNED")
    @Comment("라이선스_제품ID")
    private Integer id;

    @Setter
    @Column(name = "num_of_auth_available", columnDefinition ="INT(10) UNSIGNED", nullable = false)
    @ColumnDefault("1")
    @Comment("제품별 인증 가능 횟수")
    private Integer numOfAuthAvailable;

    @Setter
    @Column(name = "is_activated", nullable = false)
    @ColumnDefault("TRUE")
    @Comment("활성여부")
    private Boolean isActivated;

    @Setter
    @Column(name = "expire_at", nullable = false)
    @Comment("만료일시")
    private LocalDateTime expireAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "license_key", updatable = false, nullable = false)
    @JsonIgnore
    @Comment("라이선스키")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", updatable = false, nullable = false)
    @JsonIgnore
    @Comment("제품ID")
    private Product product;

    @Builder
    public LicenseProduct(Integer numOfAuthAvailable, Boolean isActivated, LocalDateTime expireAt, License license, Product product) {
        this.numOfAuthAvailable = numOfAuthAvailable;
        this.isActivated = isActivated;
        this.expireAt = expireAt;
        this.license = license;
        this.product = product;
    }

    @PrePersist
    public void prePersist() {
        this.isActivated = this.isActivated == null || this.isActivated;
        this.numOfAuthAvailable = this.numOfAuthAvailable == null ? 1 : this.numOfAuthAvailable;
    }
}

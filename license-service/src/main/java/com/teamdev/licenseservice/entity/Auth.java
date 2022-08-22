package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class Auth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id", columnDefinition ="INT(11) UNSIGNED")
    @Comment("인증ID")
    private Integer id;

    @Column(name = "device", columnDefinition ="BINARY(16)", updatable = false, nullable = false)
    @Comment("기기일련번호")
    private UUID device;

    @Column(name = "is_activated", nullable = false)
    @ColumnDefault("TRUE")
    @Comment("활성여부")
    private Boolean isActivated;

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
    public Auth(UUID device, Boolean isActivated, License license, Product product) {
        this.device = device;
        this.isActivated = isActivated;
        this.license = license;
        this.product = product;
    }

    @PrePersist
    public void prePersist() {
        this.isActivated = this.isActivated == null ? true : this.isActivated;
    }
}

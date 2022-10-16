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
@Entity(name = "auth")
public class AuthEntity extends BaseTimeEntity {

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
    @JoinColumn(name = "contract_id", updatable = false, nullable = false)
    @JsonIgnore
    @Comment("계약ID")
    private ContractEntity contract;

    @Builder
    public AuthEntity(UUID device, Boolean isActivated, ContractEntity contract) {
        this.device = device;
        this.isActivated = isActivated;
        this.contract = contract;
    }

    @PrePersist
    public void prePersist() {
        this.isActivated = this.isActivated == null ? true : this.isActivated;
    }
}

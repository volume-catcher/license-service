package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class License extends BaseTimeEntity {

    @Id
    @Column(name = "license_key", columnDefinition ="CHAR(19)", updatable = false)
    @Comment("라이선스키")
    private String key;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", updatable = false, nullable = false)
    @JsonIgnore
    @Comment("계정ID")
    private Account account;

    @Builder
    public License(String key, Account account) {
        this.key = key;
        this.account = account;
    }
}

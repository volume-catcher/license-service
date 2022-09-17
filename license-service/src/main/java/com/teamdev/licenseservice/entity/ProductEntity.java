package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "product")
public class ProductEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition ="INT(11) UNSIGNED")
    @Comment("제품ID")
    private Integer id;

    @Column(name = "product_name", nullable = false)
    @Size(max = 45)
    @Comment("제품이름")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", updatable = false, nullable = false)
    @JsonIgnore
    @Comment("계정ID")
    private AccountEntity account;

    @Builder
    public ProductEntity(String name, AccountEntity account) {
        this.name = name;
        this.account = account;
    }
}

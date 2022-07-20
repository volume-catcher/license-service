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
@Entity
@Table
@AttributeOverride(name = "id", column = @Column(name = "product_id"))
public class Product extends BaseTimeEntity {

    @Column(name = "product_name", nullable = false)
    @Size(max = 45)
    @Comment("제품이름")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", updatable = false, nullable = false)
    @JsonIgnore
    @Comment("계정ID")
    private Account account;


    @Builder
    public Product(String name, Account account) {
        this.name = name;
        this.account = account;
    }
}

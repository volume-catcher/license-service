package com.teamdev.licenseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id", columnDefinition ="INT(10) UNSIGNED")
    @Comment("제품ID")
    private Integer id;

    @Column(name = "product_name", nullable = false)
    @Size(max = 45)
    @Comment("제품이름")
    private String name;

    @Column(name = "create_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Comment("생성일시")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    @Comment("계정ID")
    private Account account;
}

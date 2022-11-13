package com.teamdev.licenseservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageDto<T> {
    private long totalElements;
    private List<T> content;

    @Builder
    public PageDto(long totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.content = content;
    }

    public static <T> PageDto<T> from(Page<T> content) {
        if (content == null) return null;

        return PageDto.<T>builder()
                .totalElements(content.getTotalElements())
                .content(content.getContent())
                .build();
    }
}

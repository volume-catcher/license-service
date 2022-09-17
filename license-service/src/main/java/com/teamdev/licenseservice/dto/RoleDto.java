package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.RoleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoleDto {
    private String name;

    @Builder
    public RoleDto(String name) {
        this.name = name;
    }

    public static RoleDto from(RoleEntity roleEntity) {
        if (roleEntity == null) return null;

        return RoleDto.builder()
                .name(roleEntity.getName())
                .build();
    }
}

package com.teamdev.licenseservice.dto;

import com.teamdev.licenseservice.entity.Role;
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

    public static RoleDto from(Role role) {
        if (role == null) return null;

        return RoleDto.builder()
                .name(role.getName())
                .build();
    }
}

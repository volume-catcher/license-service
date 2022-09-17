package com.teamdev.licenseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamdev.licenseservice.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AccountDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    private Set<RoleDto> roleDtoSet;

    @Builder
    public AccountDto(String id, String password, Set<RoleDto> roleDtoSet) {
        this.id = id;
        this.password = password;
        this.roleDtoSet = roleDtoSet;
    }

    public static AccountDto from(AccountEntity accountEntity) {
        if (accountEntity == null) return null;

        return AccountDto.builder()
                .id(accountEntity.getId())
                .password(accountEntity.getPassword())
                .roleDtoSet(accountEntity.getRoles().stream()
                        .map(role -> RoleDto.builder().name(role.getName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}

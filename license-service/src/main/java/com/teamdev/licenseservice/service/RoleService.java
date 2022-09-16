package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.RoleDto;
import com.teamdev.licenseservice.entity.Role;
import com.teamdev.licenseservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Role getOrSaveRole(RoleDto roleDto) {
        return roleRepository.findByName(roleDto.getName())
                .orElseGet(() -> {
                    Role role = Role.builder()
                            .name(roleDto.getName())
                            .build();
                    return roleRepository.save(role);
                });
    }
}

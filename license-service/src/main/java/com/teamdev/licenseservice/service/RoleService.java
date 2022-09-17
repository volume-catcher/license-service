package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.RoleDto;
import com.teamdev.licenseservice.entity.RoleEntity;
import com.teamdev.licenseservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public RoleEntity getOrSaveRole(RoleDto roleDto) {
        return roleRepository.findByName(roleDto.getName())
                .orElseGet(() -> {
                    RoleEntity roleEntity = RoleEntity.builder()
                            .name(roleDto.getName())
                            .build();
                    return roleRepository.save(roleEntity);
                });
    }
}

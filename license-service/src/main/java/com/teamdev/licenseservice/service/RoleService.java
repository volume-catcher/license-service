package com.teamdev.licenseservice.service;

import com.teamdev.licenseservice.dto.RoleDto;
import com.teamdev.licenseservice.entity.Role;
import com.teamdev.licenseservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role getOrSaveRole(RoleDto roleDto) {
        Optional<Role> preRole = roleRepository.findByName(roleDto.getName());
        if (preRole.isPresent()) {
            return preRole.get();
        }

        Role role = Role.builder()
                .name(roleDto.getName())
                .build();

        return roleRepository.save(role);
    }
}

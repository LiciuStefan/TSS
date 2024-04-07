package ro.unibuc.hello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.unibuc.hello.controllers.contracts.RoleCreateRequest;
import ro.unibuc.hello.dtos.RoleDTO;
import ro.unibuc.hello.entities.Policy;
import ro.unibuc.hello.entities.Role;
import ro.unibuc.hello.exceptions.EntityAlreadyExistsException;
import ro.unibuc.hello.exceptions.EntityNotFoundException;
import ro.unibuc.hello.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PolicyService policyService;

    public List<RoleDTO> getRoles() {
        var roles = roleRepository.findAll();
        return roles.stream().map(Role::toDTO).collect(Collectors.toList());
    }

    public RoleDTO getRoleByName(String name){
        return roleRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Role")
        ).toDTO();
    }

    public RoleDTO addRole(RoleCreateRequest roleCreateRequest) {
        var existingRole = roleRepository.findByName(roleCreateRequest.getName());
        if (existingRole.isPresent()) {
            throw new EntityAlreadyExistsException("Role");
        }
        List<Policy> policies = new ArrayList<>();
        roleCreateRequest.getPolicies().forEach(policyId -> {
            var policy = policyService.getPolicyById(policyId);
            policies.add(policy.toPolicy());
        });
        var role = new Role(roleCreateRequest.getName(), policies);
        roleRepository.save(role);
        return role.toDTO();
    }

    public RoleDTO updateRole(String name, RoleCreateRequest roleCreateRequest) {
        // Check if the new name we want to update is already taken by another role different from the one
        // we want to update (role names should be unique).
        var existingRole = roleRepository.findByName(roleCreateRequest.getName());
        if (existingRole.isPresent() && !existingRole.get().getName().equals(name)) {
            throw new EntityAlreadyExistsException("Role");
        }

        var role = roleRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Role")
        );

        List<Policy> policies = new ArrayList<>();
        roleCreateRequest.getPolicies().forEach(policyId -> {
            var policy = policyService.getPolicyById(policyId);
            policies.add(policy.toPolicy());
        });

        role.setPolicies(policies);
        role.setName(roleCreateRequest.getName());
        roleRepository.save(role);
        return role.toDTO();
    }

    public void deleteRole(String name) {
        var role = roleRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Role")
        );
        roleRepository.delete(role);
    }
}

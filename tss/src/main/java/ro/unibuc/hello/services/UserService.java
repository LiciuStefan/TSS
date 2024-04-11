package ro.unibuc.hello.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.controllers.contracts.UserCreateRequest;
import ro.unibuc.hello.dtos.UserDTO;
import ro.unibuc.hello.entities.Policy;
import ro.unibuc.hello.entities.Role;
import ro.unibuc.hello.entities.User;
import ro.unibuc.hello.exceptions.EntityAlreadyExistsException;
import ro.unibuc.hello.exceptions.EntityNotFoundException;
import ro.unibuc.hello.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PolicyService policyService;
    @Autowired
    private RoleService roleService;

    public UserDTO getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User")
        ).toDTO();
    }

    public UserDTO addUser(UserCreateRequest userCreateRequest) {
        var existingUser = userRepository.findById(userCreateRequest.getId());
        if (existingUser.isPresent()) {
            throw new EntityAlreadyExistsException("User");
        }
        List<Policy> policies = new ArrayList<>();
        List<Role> roles = new ArrayList<>();

        userCreateRequest.getPolicies().forEach(policyId -> {
            var policy = policyService.getPolicyById(policyId);
            policies.add(policy.toPolicy());
        });
        userCreateRequest.getRoles().forEach(roleId -> {
            var role = roleService.getRoleByName(roleId);
            roles.add(role.toRole());
        });

        var user = new User(userCreateRequest.getId(), policies, roles);
        userRepository.save(user);
        return user.toDTO();
    }

    public UserDTO updateUser(String id, UserCreateRequest userCreateRequest) {
        // Check if the new id we want to update is already taken by another user different from the one we want to update.
        var existingUser = userRepository.findById(userCreateRequest.getId());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new EntityAlreadyExistsException("User");
        }

        var user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User")
        );

        List<Policy> policies = new ArrayList<>();
        List<Role> roles = new ArrayList<>();

        userCreateRequest.getPolicies().forEach(policyId -> {
            var policy = policyService.getPolicyById(policyId);
            policies.add(policy.toPolicy());
        });
        userCreateRequest.getRoles().forEach(roleId -> {
            var role = roleService.getRoleByName(roleId);
            roles.add(role.toRole());
        });

        user.setPolicies(policies);
        user.setRoles(roles);
        user.setId(userCreateRequest.getId());
        userRepository.save(user);
        return user.toDTO();
    }

    public void deleteUser(String id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User")
        );
        userRepository.delete(user);
    }

    public List<UserDTO> getUsers() {
        var users = userRepository.findAll();
        return users.stream().map(User::toDTO).collect(Collectors.toList());
    }

    public UserDTO addPoliciesToUser(String userId, List<String> policyIds) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User")
        );
        List<Policy> policies = new ArrayList<>();
        policyIds.forEach(policy -> {
            var existingPolicy = policyService.getPolicyById(policy);
            policies.add(existingPolicy.toPolicy());
        });
        user.getPolicies().addAll(policies);
        userRepository.save(user);
        return user.toDTO();
    }
}
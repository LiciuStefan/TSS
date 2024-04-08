package ro.unibuc.hello.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.unibuc.hello.controllers.contracts.RoleCreateRequest;
import ro.unibuc.hello.dtos.PolicyDTO;
import ro.unibuc.hello.entities.Role;
import ro.unibuc.hello.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @Mock
    PolicyService policyService;

    @InjectMocks
    RoleService roleService = new RoleService();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("role1", new ArrayList<>()));
        roles.add(new Role("role2", new ArrayList<>()));

        when(roleRepository.findAll()).thenReturn(roles);

        var result = roleService.getRoles();

        assertEquals(2, result.size());
        assertEquals("role1", result.get(0).getName());
        assertEquals("role2", result.get(1).getName());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void test_getByName_returnsRoleDTO() {
        Role role = new Role("role1", new ArrayList<>());
        when(roleRepository.findByName("role1")).thenReturn(Optional.of(role));

        var result = roleService.getRoleByName("role1");

        assertEquals("role1", result.getName());
        verify(roleRepository, times(1)).findByName("role1");
    }

    @Test
    void test_getByName_throwsEntityNotFoundException() {
        when(roleRepository.findByName("role1")).thenReturn(Optional.empty());

        try {
            roleService.getRoleByName("role1");
        } catch (Exception e) {
            assertEquals("Entity: Role was not found", e.getMessage());
        }
        verify(roleRepository, times(1)).findByName("role1");
    }

    @Test
    void test_addRole_returnsRoleDTO() {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest("role1", List.of("1", "2"));

        when(roleRepository.findByName("role1")).thenReturn(Optional.empty());
        when(policyService.getPolicyById("1")).thenReturn(new PolicyDTO("1", "policy1", new ArrayList<>()));
        when(policyService.getPolicyById("2")).thenReturn(new PolicyDTO("2", "policy2", new ArrayList<>()));

        var result = roleService.addRole(roleCreateRequest);

        assertEquals("role1", result.getName());
        verify(roleRepository, times(1)).findByName("role1");
        verify(policyService, times(1)).getPolicyById("1");
        verify(policyService, times(1)).getPolicyById("2");
        verify(roleRepository, times(1)).save(any());
    }

    @Test
    void test_addRole_throwsEntityAlreadyExistsException() {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest("role1", List.of("1", "2"));

        when(roleRepository.findByName("role1")).thenReturn(Optional.of(new Role("role1", new ArrayList<>())));

        try {
            roleService.addRole(roleCreateRequest);
        } catch (Exception e) {
            assertEquals("Entity: Role already exists", e.getMessage());
        }
        verify(roleRepository, times(1)).findByName("role1");
    }

    @Test
    void test_updateRole_returnsRoleDTO() {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest("role1", List.of("1", "2"));
        Role role = new Role("role1", new ArrayList<>());

        when(roleRepository.findByName("role1")).thenReturn(Optional.of(role));
        when(policyService.getPolicyById("1")).thenReturn(new PolicyDTO("1", "policy1", new ArrayList<>()));
        when(policyService.getPolicyById("2")).thenReturn(new PolicyDTO("2", "policy2", new ArrayList<>()));

        var result = roleService.updateRole("role1", roleCreateRequest);

        assertEquals("role1", result.getName());
        verify(roleRepository, times(2)).findByName("role1");
        verify(policyService, times(1)).getPolicyById("1");
        verify(policyService, times(1)).getPolicyById("2");
        verify(roleRepository, times(1)).save(any());
    }

    @Test
    void test_updateRole_throwsEntityNotFoundException() {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest("newRoleName", List.of("1", "2"));

        when(roleRepository.findByName("newRoleName")).thenReturn(Optional.empty());
        when(roleRepository.findByName("currentRoleName")).thenReturn(Optional.empty());

        try {
            roleService.updateRole("currentRoleName", roleCreateRequest);
        } catch (Exception e) {
            assertEquals("Entity: Role was not found", e.getMessage());
        }
        verify(roleRepository, times(1)).findByName("newRoleName");
        verify(roleRepository, times(1)).findByName("currentRoleName");
        verify(roleRepository, times(0)).save(any());
    }

    @Test
    void test_updateRole_throwsEntityAlreadyExistsException() {
        RoleCreateRequest roleCreateRequest = new RoleCreateRequest("newRoleName", List.of("1", "2"));
        Role existingRole = new Role("newRoleName", new ArrayList<>());
        Role roleToUpdate = new Role("currentRoleName", new ArrayList<>());

        when(roleRepository.findByName("newRoleName")).thenReturn(Optional.of(existingRole));
        when(roleRepository.findByName("currentRoleName")).thenReturn(Optional.of(roleToUpdate));

        try {
            roleService.updateRole("currentRoleName", roleCreateRequest);
        } catch (Exception e) {
            assertEquals("Entity: Role already exists", e.getMessage());
        }
        verify(roleRepository, times(1)).findByName("newRoleName");
        verify(roleRepository, times(0)).findByName("currentRoleName");
        verify(roleRepository, times(0)).save(any());
    }

    @Test
    void test_deleteRole() {
        Role role = new Role("role1", new ArrayList<>());
        when(roleRepository.findByName("role1")).thenReturn(Optional.of(role));

        roleService.deleteRole("role1");

        verify(roleRepository, times(1)).findByName("role1");
        verify(roleRepository, times(1)).delete(role);
    }

    @Test
    void test_deleteRole_throwsEntityNotFoundException() {
        when(roleRepository.findByName("role1")).thenReturn(Optional.empty());

        try {
            roleService.deleteRole("role1");
        } catch (Exception e) {
            assertEquals("Entity: Role was not found", e.getMessage());
        }
        verify(roleRepository, times(1)).findByName("role1");
    }
}

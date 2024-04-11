package ro.unibuc.hello.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.unibuc.hello.controllers.contracts.PolicyCreateRequest;
import ro.unibuc.hello.dtos.PolicyDTO;
import ro.unibuc.hello.services.PolicyService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class PolicyControllerTest {

    @Mock
    private PolicyService policyService;

    @InjectMocks
    private PolicyController policyController;

    private final String id = UUID.randomUUID().toString();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_createPolicy() {
        PolicyDTO policyDTO = new PolicyDTO(id, "Test policy", new ArrayList<>());

        when(policyService.addPolicy("Test policy", new ArrayList<>())).thenReturn(policyDTO);

        PolicyCreateRequest policyCreateRequest = new PolicyCreateRequest("Test policy", new ArrayList<>());
        PolicyDTO result = policyController.addPolicy(policyCreateRequest);

        assertEquals(policyDTO, result);
        verify(policyService, times(1)).addPolicy("Test policy", new ArrayList<>());
    }

    @Test
    public void test_getPolicies() {
        when(policyService.getPolicies()).thenReturn(new ArrayList<>());
        List<PolicyDTO> result = policyController.getPolicies();

        assertEquals(0, result.size());
        verify(policyService, times(1)).getPolicies();
    }

    @Test
    public void test_getPolicyById() {
        PolicyDTO policyDTO = new PolicyDTO(id, "Test policy", new ArrayList<>());
        when(policyService.getPolicyById(id)).thenReturn(policyDTO);

        PolicyDTO result = policyController.getPolicyById(id);

        assertEquals(policyDTO, result);
        verify(policyService, times(1)).getPolicyById(id);
    }

    @Test
    public void test_updatePolicy() {
        PolicyDTO policyDTO = new PolicyDTO(id, "Test policy", new ArrayList<>());

        when(policyService.updatePolicy(id, "Test policy", new ArrayList<>())).thenReturn(policyDTO);

        PolicyCreateRequest policyCreateRequest = new PolicyCreateRequest("Test policy", new ArrayList<>());
        PolicyDTO result = policyController.updatePolicy(id, policyCreateRequest);

        assertEquals(policyDTO, result);
        verify(policyService, times(1)).updatePolicy(id, "Test policy", new ArrayList<>());
    }

    @Test
    public void test_deletePolicy() {
        policyController.deletePolicy(id);
        verify(policyService, times(1)).deletePolicy(id);
    }
}

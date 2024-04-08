package ro.unibuc.hello.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.unibuc.hello.common.EffectTypes;
import ro.unibuc.hello.controllers.contracts.PolicyCreateRequest;
import ro.unibuc.hello.dtos.PolicyDTO;
import ro.unibuc.hello.entities.Policy;
import ro.unibuc.hello.entities.Statement;
import ro.unibuc.hello.exceptions.EntityAlreadyExistsException;
import ro.unibuc.hello.exceptions.EntityNotFoundException;
import ro.unibuc.hello.repositories.PolicyRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PolicyServiceTest {

    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private PolicyService policyService;

    private String id1 = UUID.randomUUID().toString();
    private String id2 = UUID.randomUUID().toString();
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getPolicies() {
        List<Policy> policies = List.of(
                new Policy(id1, "name1", List.of()),
                new Policy(id2, "name2", List.of())
        );

        when(policyRepository.findAll()).thenReturn(policies);

        var result = policyService.getPolicies();

        assertEquals(2, result.size());
        assertEquals(id1, result.get(0).getId());
        assertEquals(id2, result.get(1).getId());
        verify(policyRepository, times(1)).findAll();

    }

    @Test
    public void test_getPolicyById_returnsPolicyDTO() {
        String[] actions = {"action1", "action2"};
        Policy policy = new Policy(id1, "name1",  List.of(new Statement(EffectTypes.ALLOW, actions)));
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.of(policy));

        var result = policyService.getPolicyById(id1);

        assertEquals(id1, result.getId());
        verify(policyRepository, times(1)).findById(id1);
    }

    @Test
    public void test_getPolicyById_throwsEntityNotFoundException() {
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.empty());

        try {
            policyService.getPolicyById(id1);
        } catch (EntityNotFoundException e) {
            assertEquals("Entity: Policy was not found", e.getMessage());
        }
        verify(policyRepository, times(1)).findById(id1);
    }

    @Test
    public void test_addPolicy_returnsPolicyDTO() {
        String[] actions = {"action1", "action2"};
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.empty());
        Policy policy = new Policy(id1, "name1", List.of(new Statement(EffectTypes.ALLOW, actions)));
        when(policyRepository.save(policy)).thenReturn(policy);

        var result = policyService.addPolicy("name1", List.of(new Statement(EffectTypes.ALLOW, actions)));

        assertEquals("name1", result.getName());
        assertEquals(1, result.getStatements().size());
        assertEquals(EffectTypes.ALLOW, result.getStatements().get(0).getEffect());
        assertEquals("action1", result.getStatements().get(0).getActions()[0]);
        assertEquals("action2", result.getStatements().get(0).getActions()[1]);
        verify(policyRepository, times(1)).save(any());

    }

    @Test
    public void test_addPolicy_throwsEntityAlreadyExistsException() {
        String[] actions = {"action1", "action2"};
        String currentId = null;
        try {
            PolicyDTO actualPolicy = policyService.addPolicy("name1", List.of(new Statement(EffectTypes.ALLOW, actions)));
            currentId = actualPolicy.getId();
            when(policyRepository.findById(currentId)).thenReturn(java.util.Optional.of(new Policy(currentId, "name1", List.of(new Statement(EffectTypes.ALLOW, actions)))));
        } catch (EntityAlreadyExistsException e) {
            assertEquals("Entity: Policy already exists", e.getMessage());
        }
    }


    @Test
    public void test_updatePolicy_returnsPolicyDTO() {
        String[] actions = {"action1", "action2"};
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.of(new Policy(id1, "name1", List.of(new Statement(EffectTypes.ALLOW, actions)))));

        var result = policyService.updatePolicy(id1, "name1", List.of(new Statement(EffectTypes.ALLOW, actions)));

        assertEquals("name1", result.getName());
        assertEquals(1, result.getStatements().size());
        assertEquals(EffectTypes.ALLOW, result.getStatements().get(0).getEffect());
        assertEquals("action1", result.getStatements().get(0).getActions()[0]);
        assertEquals("action2", result.getStatements().get(0).getActions()[1]);
        verify(policyRepository, times(1)).save(any());
    }

    @Test
    public void test_updatePolicy_throwsEntityNotFoundException() {
        String[] actions = {"action1", "action2"};
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.empty());
        try {
            policyService.updatePolicy(id1, "name1", List.of(new Statement(EffectTypes.ALLOW, actions)));
        } catch (EntityNotFoundException e) {
            assertEquals("Entity: Policy was not found", e.getMessage());
        }
        verify(policyRepository, times(1)).findById(id1);
    }

    @Test
    public void test_deletePolicy() {
        String[] actions = {"action1", "action2"};
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.of(new Policy(id1, "name1", List.of(new Statement(EffectTypes.ALLOW, actions)))));

        policyService.deletePolicy(id1);

        verify(policyRepository, times(1)).delete(any());
    }

    @Test
    public void test_deletePolicy_throwsEntityNotFoundException() {
        when(policyRepository.findById(id1)).thenReturn(java.util.Optional.empty());
        try {
            policyService.deletePolicy(id1);
        } catch (EntityNotFoundException e) {
            assertEquals("Entity: Policy was not found", e.getMessage());
        }
        verify(policyRepository, times(1)).findById(id1);
    }
}

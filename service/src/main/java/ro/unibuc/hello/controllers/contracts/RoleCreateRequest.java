package ro.unibuc.hello.controllers.contracts;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleCreateRequest {
    private String name;
    private List<String> policies;
}
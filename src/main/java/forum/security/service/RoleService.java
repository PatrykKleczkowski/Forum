package forum.security.service;

import forum.security.model.Role;
import forum.security.model.RoleName;
import forum.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName(RoleName.ROLE_USER.name());
    }

}

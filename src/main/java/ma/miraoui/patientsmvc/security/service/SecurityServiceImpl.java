package ma.miraoui.patientsmvc.security.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.miraoui.patientsmvc.security.entities.AppRole;
import ma.miraoui.patientsmvc.security.entities.AppUser;
import ma.miraoui.patientsmvc.security.repositories.AppRoleRepository;
import ma.miraoui.patientsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository userRepository;
    private AppRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) {
            throw new RuntimeException("Passwords doesn't match");
        }
        String hashedPassword = passwordEncoder.encode(password);
        AppUser user = new AppUser();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setActive(true);

        return userRepository.save(user);
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole role = roleRepository.findByRoleName(roleName);
        if(role!=null){
            throw new RuntimeException("Role "+roleName+" already exist");
        }
        role=new AppRole();
        role.setRoleName(roleName);
        role.setDescription(description);

        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        if(role==null) throw new RuntimeException("Role not found.");
        AppUser user = userRepository.findByUsername(username);
        if(user==null) throw new RuntimeException("User not found.");
        user.getAppRoles().add(role);

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        if(role==null) throw new RuntimeException("Role not found.");

        AppUser user = userRepository.findByUsername(username);
        if(user==null) throw new RuntimeException("User not found.");

        user.getAppRoles().remove(role);
    }

    @Override
    public AppUser LoadUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}

package ma.miraoui.patientsmvc.security.service;

import ma.miraoui.patientsmvc.security.entities.AppRole;
import ma.miraoui.patientsmvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser (String username, String password, String rePassword);
    AppRole saveNewRole (String roleName, String description);
    void addRoleToUser (String username, String roleName);
    void removeRoleFromUser (String username, String roleName);
    AppUser LoadUserByUserName (String username);
}

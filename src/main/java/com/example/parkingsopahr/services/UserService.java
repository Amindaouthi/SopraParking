package com.example.parkingsopahr.services;
import com.example.parkingsopahr.dao.RoleDao;
import com.example.parkingsopahr.dao.userDao;
import com.example.parkingsopahr.entities.Role;
import com.example.parkingsopahr.entities.user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements Iuser {

    @Autowired
    private userDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Integer registrerNewUser(user user) {
        Role role = roleDao.findOneByRoleName("Manager").get();
        if (role != null) {
            user.setRole(role);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            userDao.save(user);
            return 1;
        }
        else{
            return 0;
        }

    }

    @Override
    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleDao.save(adminRole);

        Role managerRole = new Role();
        managerRole.setRoleName("Manager");
        managerRole.setRoleDescription("default role for newly created record");
        roleDao.save(managerRole);

        user adminUser = new user();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserEmail("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setRole(adminRole);
        userDao.save(adminUser);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}

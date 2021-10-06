/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.config;

import com.hukacode.sandbox.springboot.oauth2.sociallogin.dto.SocialProvider;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.model.Role;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.model.User;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.repo.RoleRepository;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.repo.UserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadySetup = false;

  @Autowired private UserRepository userRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    if (alreadySetup) {
      return;
    }
    // Create initial roles
    Role userRole = createRoleIfNotFound(Role.ROLE_USER);
    Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
    Role modRole = createRoleIfNotFound(Role.ROLE_MODERATOR);
    createUserIfNotFound("admin@javachinna.com", Set.of(userRole, adminRole, modRole));
    alreadySetup = true;
  }

  @Transactional
  private final User createUserIfNotFound(final String email, Set<Role> roles) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      user = new User();
      user.setDisplayName("Admin");
      user.setEmail(email);
      user.setPassword(passwordEncoder.encode("admin@"));
      user.setRoles(roles);
      user.setProvider(SocialProvider.LOCAL.getProviderType());
      user.setEnabled(true);
      Date now = Calendar.getInstance().getTime();
      user.setCreatedDate(now);
      user.setModifiedDate(now);
      user = userRepository.save(user);
    }
    return user;
  }

  @Transactional
  private final Role createRoleIfNotFound(final String name) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = roleRepository.save(new Role(name));
    }
    return role;
  }
}

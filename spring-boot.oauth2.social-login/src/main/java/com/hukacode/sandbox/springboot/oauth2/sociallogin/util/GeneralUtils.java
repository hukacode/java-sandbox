/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.util;

import com.hukacode.sandbox.springboot.oauth2.sociallogin.dto.LocalUser;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.dto.SocialProvider;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.dto.UserInfo;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.model.Role;
import com.hukacode.sandbox.springboot.oauth2.sociallogin.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class GeneralUtils {

  public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  public static SocialProvider toSocialProvider(String providerId) {
    for (SocialProvider socialProvider : SocialProvider.values()) {
      if (socialProvider.getProviderType().equals(providerId)) {
        return socialProvider;
      }
    }
    return SocialProvider.LOCAL;
  }

  public static UserInfo buildUserInfo(LocalUser localUser) {
    List<String> roles =
        localUser.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
    User user = localUser.getUser();
    return new UserInfo(user.getId().toString(), user.getDisplayName(), user.getEmail(), roles);
  }
}

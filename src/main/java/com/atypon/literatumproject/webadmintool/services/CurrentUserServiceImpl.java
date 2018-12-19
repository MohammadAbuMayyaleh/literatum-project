package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.CurrentUser;
import com.atypon.literatumproject.webadmintool.model.Role;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        //// TODO: 12/15/18 Edit Admin
        return currentUser != null && (currentUser.getRole() == Role.SUPER_ADMIN || currentUser.getId().equals(userId));
    }

}

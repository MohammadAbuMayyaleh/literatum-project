package com.atypon.literatumproject.webadmintool.services;


import com.atypon.literatumproject.webadmintool.model.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}

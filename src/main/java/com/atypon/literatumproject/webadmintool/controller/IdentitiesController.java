package com.atypon.literatumproject.webadmintool.controller;

import com.atypon.literatumproject.webadmintool.form.UserCreateForm;
import com.atypon.literatumproject.webadmintool.services.UserService;
import com.atypon.literatumproject.webadmintool.validators.UserCreateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class IdentitiesController {

    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public IdentitiesController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping("identities/remove/{id}")
    public String removeIdentities(@PathVariable Long id){
        userService.removeUser(id);
        return "redirect:/identities";
    }

    @InitBinder("userCreateForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    //@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping("/identities/create/user")
    public String getUserCreatePage(@Valid @ModelAttribute("userCreateForm") UserCreateForm userCreateForm , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/identities";
        }
        try {
            userService.createUser(userCreateForm);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "redirect:/identities";
        }
        return "redirect:/identities";
    }
}

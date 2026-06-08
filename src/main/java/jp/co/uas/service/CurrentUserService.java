package jp.co.uas.service;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jp.co.uas.model.UserPrincipal;

@Service
public class CurrentUserService {

    public UserPrincipal getCurrentUser() {

        Authentication auth =
            SecurityContextHolder
                .getContext()
                .getAuthentication();

        return (UserPrincipal)
                auth.getPrincipal();
    }

    public UUID getCurrentUserId() {
        return getCurrentUser().getUserID();
    }
}

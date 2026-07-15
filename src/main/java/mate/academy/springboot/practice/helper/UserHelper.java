package mate.academy.springboot.practice.helper;

import mate.academy.springboot.practice.exception.LoginException;
import mate.academy.springboot.practice.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public User getUserOrThrow(Authentication authentication) {
        if (authentication != null
                && authentication.getPrincipal() instanceof User
        ) {
            return ((User) (authentication.getPrincipal()));
        }

        throw new LoginException("User not logged in");
    }
}

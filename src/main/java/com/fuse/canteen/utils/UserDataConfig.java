package com.fuse.canteen.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDataConfig {
    private final TokenStore tokenStore;

    public Long getLoggedInUserId() {
        try {
            OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            Map<String, Object> details = tokenStore.readAccessToken(auth2AuthenticationDetails.getTokenValue()).getAdditionalInformation();
            return Long.valueOf(String.valueOf(details.get("userId")));
        }catch (Exception e){
            return Long.valueOf(0);
        }
    }
}
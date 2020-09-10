package com.fuse.canteen.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Map;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfiguration {

    private final TokenStore tokenStore;

    public JpaAuditingConfiguration(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAware<Long>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                try {
                    if (SecurityContextHolder.getContext().getAuthentication() != null) {
                        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
                        Map<String, Object> details = tokenStore.readAccessToken(auth2AuthenticationDetails.getTokenValue()).getAdditionalInformation();

                        return Optional.of(Long.valueOf(String.valueOf(details.get("userId"))));
                    } else {
                        return Optional.of(Long.valueOf(0));
                    }
                }catch (Exception e){
                    return Optional.of(Long.valueOf(0));
                }
            }
        };
    }

}

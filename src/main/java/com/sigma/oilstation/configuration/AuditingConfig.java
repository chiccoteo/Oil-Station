package com.sigma.oilstation.configuration;

import com.sigma.oilstation.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    AuditorAware<User> auditorAware() {
        return new AuditAwareImpl();
    }

}

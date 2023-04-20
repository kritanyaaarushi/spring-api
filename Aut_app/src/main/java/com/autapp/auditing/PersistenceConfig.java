package com.autapp.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.autapp.entity.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class PersistenceConfig {
	
	@Bean
	public AuditorAware<User> auditorAware(){
		return new AuditorAwareImpl();
	}

}

package me.theyinspire.starbox.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 7:21 PM)
 */
@Configuration
@EnableJpaRepositories("me.theyinspire.starbox.db.repositories")
@EnableJpaAuditing
@EntityScan("me.theyinspire.starbox.db.entities")
public class DatabaseConfiguration {
}

package me.theyinspire.starbox.web.exec;

import me.theyinspire.starbox.web.config.ControllersConfiguration;
import me.theyinspire.starbox.web.config.DatabaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 7:18 PM)
 */
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
@Import({
        DatabaseConfiguration.class,
        ControllersConfiguration.class
})
public class Launcher {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Launcher.class, args);
    }

}

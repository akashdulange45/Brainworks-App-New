package in.ad.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.ad.main.entity.Admin;
import in.ad.main.repository.AdminRepository;
import in.ad.main.services.AdminService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private AdminService adminService;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth 
				.requestMatchers("/login").permitAll()
				.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/videos/**").authenticated()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated())
		.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/admin/dashboard", true)
				.permitAll())
		
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/lohin?logout")
				.permitAll())
		
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
	
	 @Bean
	    AuthenticationManager authenticationManager(
	            AuthenticationConfiguration configuration)
	            throws Exception {

	        return configuration.getAuthenticationManager();
	    }
	 
	 @Bean
	    public CommandLineRunner createAdmin(
	            AdminRepository repository,
	            BCryptPasswordEncoder encoder) {

	        return args -> {

	            if (repository.findByUsername("admin").isEmpty()) {

	                Admin admin = new Admin();

	                admin.setUsername("admin");
	                admin.setPassword(encoder.encode("admin123"));
	                admin.setRole("ADMIN");

	                repository.save(admin);

	                System.out.println("Admin created successfully.");
	            } else {
	                System.out.println("Admin already exists.");
	            }
	        };
	    }
	
	
}

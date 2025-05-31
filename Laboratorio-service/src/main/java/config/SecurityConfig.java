package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Endpoints públicos
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                // Endpoints apenas para ADMIN
                .requestMatchers("/api/professores/**").hasRole("ADMIN")
                .requestMatchers("/api/softwares/create", "/api/softwares/update/**", "/api/softwares/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/laboratorios/create", "/api/laboratorios/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/solicitacoes/update/**").hasRole("ADMIN")
                .requestMatchers("/api/relatos/all").hasRole("ADMIN")
                // Endpoints para usuários autenticados
                .requestMatchers("/api/softwares/**").authenticated()
                .requestMatchers("/api/laboratorios/**").authenticated()
                .requestMatchers("/api/solicitacoes/**").authenticated()
                .requestMatchers("/api/relatos/**").authenticated()
                // Qualquer outro endpoint requer autenticação
                .anyRequest().authenticated()
            );

        // Adiciona o filtro JWT antes do filtro de autenticação padrão
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


package com.example.support.config.security;

import com.example.support.filter.JwtAuthticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SysUserDetailsService sysUserDetailsService;

    @Autowired
    JwtAuthticationFilter jwtAuthticationFilter;

    /**
     * セキュリティフィルタチェインを設定する
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //CSRF対策を無効化する
        http.csrf(csrf->csrf.disable());

        //インターセプトを設定する
        http.authorizeHttpRequests(auth->auth.requestMatchers("/auth/sys").permitAll().anyRequest().authenticated());

        //form認証を開始する
        http.formLogin(Customizer.withDefaults());

        //クロスオリジンを設定する
        http.cors(cors->cors.configurationSource(corsConfigurationSource()));

        //フィルターを設定する
        http.addFilterBefore(jwtAuthticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //パスワードエンコーダを設定する
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManagerBuilderを設定する
    @Bean
    public AuthenticationManager sysUserAuthenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(sysUserDetailsService);

        return new ProviderManager(provider);
    }

    //クロスオリジンを解決する
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}

package com.dims.api.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtRequestFilter: JwtRequestFilter
) {

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    /**
     * Цепочка безопасности №1 (приоритетная) - ТОЛЬКО для H2 консоли.
     * @Order(1) делает ее первой в очереди на проверку.
     */
    @Bean
    @Order(1)
    fun h2ConsoleSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Применяем эти правила только к путям H2 консоли
            .securityMatcher(PathRequest.toH2Console())
            // Разрешаем все запросы к H2 консоли
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            // Отключаем CSRF для H2 консоли
            .csrf { it.disable() }
            // Разрешаем H2 консоли использовать фреймы
            .headers { it.frameOptions { frameOptions -> frameOptions.sameOrigin() } }

        return http.build()
    }

    /**
     * Цепочка безопасности №2 (основная) - для нашего API.
     * Обрабатывает все остальные запросы.
     */
    @Bean
    @Order(2)
    fun apiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            // Отключаем CORS, если он не нужен для API
            .cors { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/v2/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}

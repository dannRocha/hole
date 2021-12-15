package com.hole.security;

import com.hole.filter.AuthFilter;
import com.hole.services.AutenticacaoService;
import com.hole.services.UsuarioService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AutenticacaoService autenticacaoService;
  private final UsuarioService usuarioService;

  @Value("${hole.api.version}")
  private String apiVersion;

  @Value("${hole.platform}")
  private String platform;

  public SecurityConfig(@Lazy AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
    this.autenticacaoService = autenticacaoService;
    this.usuarioService = usuarioService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(usuarioService)
      .passwordEncoder(new BCryptPasswordEncoder());
  }

  private static final String[] AUTH_WHITELIST = {
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/v2/api-docs",
    "/webjars/**",
    "/h2-console/**"
  };

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(AUTH_WHITELIST);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
  

    http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.POST, String.format("/%s/auth", apiVersion)).permitAll()
      .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .addFilterBefore(new AuthFilter(autenticacaoService, usuarioService),  UsernamePasswordAuthenticationFilter.class)
    ;
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

}

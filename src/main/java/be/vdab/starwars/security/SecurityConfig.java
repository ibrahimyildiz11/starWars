package be.vdab.starwars.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
class SecurityConfig {
    private static final String GEBRUIKER = "gebruiker";
    private final DataSource dataSource;

    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcUserDetailsManager maakPrincipals() {
        var manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
                """
                select email as username, paswoord as password, true as enabled 
                from gebruikers 
                where email = ?
                """
        );
        manager.setAuthoritiesByUsernameQuery("select ?, 'gebruiker'");
        return manager;
    }
    @Bean
    public WebSecurityCustomizer configure(AuthenticationManagerBuilder auth) {
        return (web) -> web.ignoring().mvcMatchers("/images/**", "/css/**", "/js/**");
    }
    @Bean
    public SecurityFilterChain geefRechten(HttpSecurity http) throws Exception {
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        http.formLogin(login -> login.loginPage("/login"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/film/**", "/score/**").hasAuthority(GEBRUIKER)
                .mvcMatchers("/", "/login").permitAll()
                .mvcMatchers("/**").authenticated());
        return http.build();
    }
}

/*
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as username, paswoord as password, true as enabled from gebruikers where email = ?")
                .authoritiesByUsernameQuery("select email as username, id as authority from gebruikers where email = ?");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login"));
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/", "/login", "/gemiddeldeScores")
                .permitAll()
                .mvcMatchers("/filmZoeken/**").authenticated());
    }
}
 */

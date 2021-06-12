package ee.sda.exercise12.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   private static final String password = "{noop}Secret_123";

   @Override
   protected void configure(final HttpSecurity hs) throws Exception {
      hs.authorizeRequests().
              // the GET/api/cars path requires you to have the ADMIN or CARS role
              antMatchers(HttpMethod.GET, "/api/cars").hasAnyRole("ADMIN", "CARS").

              // the POST/api/cars path requires you to login
              antMatchers(HttpMethod.POST, "/api/cars").authenticated().

              // paths starting with /api/users/ require authority ROLE_USER_ADMIN
              antMatchers("/api/users/**").hasAuthority("ROLE_USER_ADMIN").

              anyRequest().permitAll(). // all other paths do not require authentication
              and().formLogin(). // Automatically generated login form
              and().httpBasic(). // Basic authentication
              and().logout().    // it is possible to log out
              and().csrf().ignoringAntMatchers("/api/**", "/h2-console/**"). //CSRF is not generated on certain paths
              and().headers().frameOptions().disable();
   }

   @Override
   protected void configure(final AuthenticationManagerBuilder amb) throws Exception {
      //user admin with the roles ADMIN and CARS
      amb.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN", "CARS").

         // user admin2 with authority ROLE_USER_ADMIN
         and().withUser("admin2").password(password).authorities("ROLE_USER_ADMIN").

         // user admin3 with role CARS
         and().withUser("admin3").password(password).roles("CARS");
   }
}

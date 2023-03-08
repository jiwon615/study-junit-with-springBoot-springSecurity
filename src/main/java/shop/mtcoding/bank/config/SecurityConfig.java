package shop.mtcoding.bank.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import shop.mtcoding.bank.domain.user.UserEnum;

@Configuration
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.debug("디버그: BCryptPasswordEncoder 빈 등록됨");
        return new BCryptPasswordEncoder();
    }

    // JWT 필터 등록이 필요함

    // JWT 서버를 만들 예정!! Session 사용안함
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable(); // iframe 허용안함
        http.csrf().disable(); // enable이면 post맨 작동안함
        http.cors().configurationSource(configurationSource()); // cors: 다른 서버에 있는 어떤 프로그램중에 자바스크립트로 요청되 Api controller쪽으로 오는걸 다 막겠다는것 (얘를 허용해줘야함)

        // jSessionId를 서버쪽에서 관리안하겠다는 뜻
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // reac, 앱으로 요청할 예정
        http.formLogin().disable();
        // httpBasic은 브라우저가 팝업창을 이용해 사용자 인증을 진행함
        http.httpBasic().disable();

        http.authorizeRequests()
                .antMatchers("/api/s/**").authenticated()
                .antMatchers("api/admin/**").hasRole(UserEnum.ADMIN.getValue()) // 최근 공식문서에는 ROLE_ 안붙여도 됨
                .anyRequest().permitAll();

        return http.build();
    }


    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (프론트엔드 IP만 허용 react)
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 주소요청에 (/**) configurationSource에 등록된 설정을 넣어주겠다는 뜻
        return source;
    }
}

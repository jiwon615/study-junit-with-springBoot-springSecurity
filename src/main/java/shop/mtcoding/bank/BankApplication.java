package shop.mtcoding.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class BankApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BankApplication.class, args);
//		String[] iocNames = context.getBeanDefinitionNames(); // 스프링 IoC 컨테이너에 등록된 빈 이름들
//		for (String name : iocNames) {
//			log.info(name);
//		}
	}

}

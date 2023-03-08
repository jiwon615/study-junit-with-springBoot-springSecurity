# study-junit-with-springBoot-springSecurity
개인공부 (스프링부트 JUnit 테스트 - 시큐리티를 활용한 Bank 애플리케이션)


### 사용하는 라이브러리
* Spring Web
* Lombok
* Spring Security
* H2 database
* MariaDB
* Spring Data JPA
* DevTools
* Validation


### Jpa LocalDateTime 자동으로 생성하는 법

- @EnableJpaAuditing (Main 클래스)
- @EntityListeners(AuditingListener.class) (Entity 클래스)
```
  @CreatedDate // inert
  @Column(nullable = false)
  private String createdAt;

  @LastModifiedDate // insert, update
  @Column(nullable = false)
  private String updatedAt;
````
package shop.mtcoding.bank.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shop.mtcoding.bank.domain.user.User;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 4)
    private Long number; // 계좌번호

    @Column(nullable = false, length = 4)
    private Long password; // 계좌비번

    @Column(nullable = false)
    private Long balance; // 잔액 (기본값 1000원)

    @ManyToOne(fetch = FetchType.LAZY) // account.getUser().아무필드호출 == Lazy 발동
    private User user; // user_id

    // 양방향 매핑 - 쿼리를 보면 맘에 안들게 나올 것

    @CreatedDate // inert
    @Column(nullable = false)
    private String createdAt;

    @LastModifiedDate // insert, update
    @Column(nullable = false)
    private String updatedAt;

    @Builder

    public Account(Long id, Long number, Long password, Long balance, User user, String createdAt, String updatedAt) {
        this.id = id;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

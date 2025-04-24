package ser.mil.bankblik.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class BlikCode {
    @Id
    private String id;
    private int code;
    private double amount;

    private LocalDateTime generatedAt;
    @ManyToOne
    private Account sourceAccount;

    public BlikCode(String id, int code, double amount, Account sourceAccount) {
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        generatedAt = LocalDateTime.now();
    }

    public BlikCode() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }
}

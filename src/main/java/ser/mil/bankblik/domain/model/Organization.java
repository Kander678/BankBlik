package ser.mil.bankblik.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Organization {
    @Id
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "organization")
    private List<BlikTransaction> transactions=new ArrayList<>();

    public Organization(String id, String name, Account account) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.transactions=new ArrayList<>();
    }

    public Organization() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<BlikTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BlikTransaction> transactions) {
        this.transactions = transactions;
    }
}

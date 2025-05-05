package ser.mil.bankblik.domain.model;

import jakarta.persistence.*;

@Entity
public class BlikTransaction {
    @Id
    private String id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    @OneToOne
    private BlikCode blikCode;

    public BlikCode getBlikCode() {
        return blikCode;
    }

    public void setBlikCode(BlikCode blikCode) {
        this.blikCode = blikCode;
    }

    public BlikTransaction(String id, double amount, TransactionStatus status, Organization organization, Account sourceAccount, Account destinationAccount) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.organization = organization;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.blikCode=null;
    }

    public BlikTransaction() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}

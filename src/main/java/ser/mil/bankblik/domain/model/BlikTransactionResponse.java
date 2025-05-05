package ser.mil.bankblik.domain.model;

import jakarta.persistence.Entity;


public record BlikTransactionResponse(String transactionId,String blikCodePath){
}
package com.enset.ebankservice.services;

import com.enset.ebankservice.entities.BankAccount;
import com.enset.ebankservice.model.Customer;
import com.enset.ebankservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EBankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return bankAccount;
    }


    public BankAccount saveBankAccount(BankAccount bankAccount) {
                bankAccount.setId(UUID.randomUUID().toString());
                bankAccount.setCreatedAt(new Date());
                return bankAccountRepository.save(bankAccount);
    }
}
package com.enset.ebankservice.services;

import com.enset.ebankservice.entities.BankAccount;
import com.enset.ebankservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class EBankService {
    private BankAccountRepository bankAccountRepository;
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

}
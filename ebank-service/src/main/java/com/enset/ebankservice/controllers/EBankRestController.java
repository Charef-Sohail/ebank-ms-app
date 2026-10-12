package com.enset.ebankservice.controllers;


import com.enset.ebankservice.entities.BankAccount;
import com.enset.ebankservice.services.EBankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EBankRestController {
    private EBankService eBankService;

    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {
        return eBankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return eBankService.getBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        return eBankService.saveBankAccount(bankAccount);
    }
}
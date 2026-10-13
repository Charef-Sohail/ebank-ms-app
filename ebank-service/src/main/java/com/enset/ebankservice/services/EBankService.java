package com.enset.ebankservice.services;

import com.enset.ebankservice.entities.BankAccount;
import com.enset.ebankservice.feign.CustomerRestClient;
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
    @Autowired
    private CustomerRestClient customerRestClient;

    @McpTool(description = "Get all of bank accounts")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @McpTool(description = "Get a bank account by id")
    public BankAccount getBankAccountById(@McpToolParam("The bank account id") String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);

        return bankAccount;
    }


    @McpTool(description = "Save a new bank Account")
    public BankAccount saveBankAccount(@McpToolParam("The bank account to save(balance, type, customer)") BankAccount bankAccount) {
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            if (customer != null) {
                bankAccount.setId(UUID.randomUUID().toString());
                bankAccount.setCreatedAt(new Date());
                return bankAccountRepository.save(bankAccount);
            }
        } catch (Exception e) {
            throw new RuntimeException("Customer not found or service unavailable");
        }
        return null;
    }
}
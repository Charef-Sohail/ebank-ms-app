package com.enset.ebankservice;

import com.enset.ebankservice.entities.BankAccount;
import com.enset.ebankservice.services.EBankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(EBankService eBankService) {
        return args -> {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 5; j++) {
                    eBankService.saveBankAccount(BankAccount.builder()
                            .customerId((long) i)
                            .balance(1000 + Math.random() * 60000)
                            .type(Math.random() > 0.5 ? "CURRENT_ACCOUNT" : "SAVING_ACCOUNT")
                            .build());
                }
            }
        };
    }
}

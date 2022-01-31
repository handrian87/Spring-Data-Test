package com.test.springdata.service;

import com.test.springdata.model.Account;
import com.test.springdata.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;
    public TransferService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Transactional
    public void transferMoney(long idSender, long idRecever, BigDecimal amount) throws AccountNotFoundException {
        Account sender = accountRepository.findById(idSender)
                .orElseThrow(() -> new AccountNotFoundException());
        Account receiver = accountRepository.findById(idRecever)
                .orElseThrow(() -> new AccountNotFoundException());
        BigDecimal senderNewAmount = receiver.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idRecever, receiverNewAmount);
    }
    public Iterable<Account>getAllAccounts(){
        return accountRepository.findAll();
    }
    public List<Account> findAccountsByName(String name){
        return accountRepository.findAccountsByName(name);
    }
}

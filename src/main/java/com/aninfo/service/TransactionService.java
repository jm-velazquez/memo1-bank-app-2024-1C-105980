package com.aninfo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.TransactionNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Collection<Transaction> getTransactionsByCbu(Long cbu) {
        return transactionRepository.findTransactionsByCbu(cbu);
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void deleteById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (!transactionOptional.isPresent()) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        Transaction transaction = transactionOptional.get();
        Account account = accountRepository.findAccountByCbu(transaction.getCbu());

        if (transaction.getType() == TransactionType.DEPOSIT) {
            Double newBalance = account.getBalance() - transaction.getSum();
            if (newBalance <= 0) {
                throw new DepositNegativeSumException("Cannot leave account with negative balance after deposit rollback");
            }
            account.setBalance(account.getBalance() - transaction.getSum());
        } else {
            account.setBalance(account.getBalance() + transaction.getSum());
        }
        accountRepository.save(account);

        transactionRepository.deleteById(id);
    }
}

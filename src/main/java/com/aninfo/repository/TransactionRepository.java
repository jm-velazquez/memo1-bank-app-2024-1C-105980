package com.aninfo.repository;

import com.aninfo.model.Transaction;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Transaction findTransactionById(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.cbu = :cbu")
    List<Transaction> findTransactionsByCbu(@Param("cbu") Long cbu);

    @Override
    List<Transaction> findAll();

}
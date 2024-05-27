package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long cbu;
	private TransactionType type;
	private Double sum;

	public Transaction() {
	}

	public Transaction(Long cbu, TransactionType type, Double sum) {
        this.cbu = cbu;
		this.type = type;
		this.sum = sum;
  }
}

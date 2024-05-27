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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCbu() {
		return this.cbu;
	}

	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}

	public TransactionType getType() {
		return this.type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}
}

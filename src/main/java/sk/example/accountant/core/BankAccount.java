package sk.example.accountant.core;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class BankAccount {
//	@Id // primary key
//	@GeneratedValue
//	private int id;
//	private Double accountBalance;
//	@Column
//	private int iban;
	//-------------
	@Id 
	private Long id;
	@Column
	private Double accountBalance;
	
	
	//References oneToOne in this case means: BanAccount Entity, is owner foreign entity Client,
	//Client have to create first, after is client created, BankAccount should be create.
	@OneToOne
	@JoinColumn(name = "person_id")
	private Client client;
	
	public BankAccount(Double accountBalance, Client client, Long iban) {
		this.accountBalance = accountBalance;
		this.client = client;
		this.id=iban;
	}
	
	public BankAccount() {
	}

	public Long getIban() {
		return id;
	}

	public void setIban(Long iban) {
		this.id = iban;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "BankAccount [iban=" + id + ", accountBalance=" + accountBalance + ", client=" + client + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, client, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(accountBalance, other.accountBalance) && Objects.equals(client, other.client)
				&& id == other.id;
	}


	

}

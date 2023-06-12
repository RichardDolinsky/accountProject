package sk.example.accountant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import sk.example.accountant.core.BankAccount;
import sk.example.accountant.core.Client;
@Entity
@Table(name = "person_account")
public class PersonAccount {
	@Id
	 @OneToOne
	    @JoinColumn(name = "account_id", referencedColumnName = "id")
	private BankAccount account;
	

}

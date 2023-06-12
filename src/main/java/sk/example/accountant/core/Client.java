package sk.example.accountant.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_table")
public class Client {
	@Column
	private String name;
	@Id 
	private Long personId;
	
//	@OneToOne(mappedBy = "client")
//	private BankAccount bankAccount;
	
	//default constructor 
	 public Client() {
	    }
	 
	public Client(String name, long personId) {
		this.name = name;
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", personId=" + personId + "]";
	}



	

}

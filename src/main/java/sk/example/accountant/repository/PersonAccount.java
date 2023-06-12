package sk.example.accountant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import sk.example.accountant.core.BankAccount;

public interface PersonAccount extends JpaRepository <BankAccount, Long>{

}

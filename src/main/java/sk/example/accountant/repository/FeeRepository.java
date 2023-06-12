package sk.example.accountant.repository;

import org.springframework.data.repository.CrudRepository;

import sk.example.accountant.model.Fees;

public interface FeeRepository extends CrudRepository<Fees,Long> {

}

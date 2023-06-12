package sk.example.accountant.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import sk.example.accountant.core.Client;
public interface ClientRepo extends JpaRepository<Client,Long> {

}

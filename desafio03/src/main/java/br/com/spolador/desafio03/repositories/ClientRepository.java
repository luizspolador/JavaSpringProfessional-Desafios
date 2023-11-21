package br.com.spolador.desafio03.repositories;

import br.com.spolador.desafio03.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

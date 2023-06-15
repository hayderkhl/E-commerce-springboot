package com.example.ecommercespringboot.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(
    "select t from Token t inner join Customer c on t.customer.id = c.id\n" +
            "    where c.id =:customerId and (t.expired=false or t.revoked=false )"
    )
    List<Token> findAllValidTokenByCustomer(Integer customerId);

    Optional<Token> findByToken(String token);
}

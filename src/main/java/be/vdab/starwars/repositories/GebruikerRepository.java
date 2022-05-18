package be.vdab.starwars.repositories;

import be.vdab.starwars.domain.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Long> {
    Optional<Gebruiker> findByEmail(String email);
}

package be.vdab.starwars.repositories;

import be.vdab.starwars.domain.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Long> {
    Gebruiker findByEmail(String email);
}

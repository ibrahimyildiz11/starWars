package be.vdab.starwars.services;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.repositories.GebruikerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GebruikerService {
    private final GebruikerRepository gebruikerRepository;

    public GebruikerService(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Gebruiker> findByEmail(String email) {
        return gebruikerRepository.findByEmail(email);
    }
}

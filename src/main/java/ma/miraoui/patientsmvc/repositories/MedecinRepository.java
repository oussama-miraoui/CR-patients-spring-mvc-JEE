package ma.miraoui.patientsmvc.repositories;

import ma.miraoui.patientsmvc.entities.Medecin;
import ma.miraoui.patientsmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Page<Medecin> findByNomContains(String nom, Pageable pageable);
}

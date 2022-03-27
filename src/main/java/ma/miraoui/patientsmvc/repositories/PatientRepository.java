package ma.miraoui.patientsmvc.repositories;

import ma.miraoui.patientsmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}

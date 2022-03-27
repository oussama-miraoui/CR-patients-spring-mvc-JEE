package ma.miraoui.patientsmvc;

import ma.miraoui.patientsmvc.entities.Patient;
import ma.miraoui.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args ->{
            Stream.of("Oussama", "Hamid", "Hassan", "Yasmine").forEach(name->{
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                patient.setScore((int) Math.floor(Math.random()*500));
                patientRepository.save(patient);
            });

            /* patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });*/
        };


    }

}

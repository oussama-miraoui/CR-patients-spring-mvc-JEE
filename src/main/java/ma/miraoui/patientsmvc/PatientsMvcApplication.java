package ma.miraoui.patientsmvc;

import ma.miraoui.patientsmvc.entities.Medecin;
import ma.miraoui.patientsmvc.entities.Patient;
import ma.miraoui.patientsmvc.repositories.MedecinRepository;
import ma.miraoui.patientsmvc.repositories.PatientRepository;
import ma.miraoui.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }


    @Bean
        //cré au démarrage un objet de type PasswordEncoder
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // @Bean
    CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository){
        return args ->{
            /*Stream.of("Oussama", "Hamid", "Hassan", "Yasmine", "Mehdi", "Ahmed", "Yassine").forEach(name->{
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                patient.setScore((int) Math.floor(Math.random()*500));
                patientRepository.save(patient);
            });*/

            Stream.of("Mehdi", "Yasser", "Ghali", "Achraf", "Idriss", "Redouane", "Yassine").forEach(name->{
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name+"@gmail.com");
                medecin.setSpecialite(Math.random() > 0.5 ? "Dentiste" : "Chirurgie");
                medecinRepository.save(medecin);
            });

            /* patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });*/
        };
    }

    // @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("oussama", "1234", "1234");
            securityService.saveNewUser("user", "1234", "1234");
            securityService.saveNewUser("admin", "1234", "1234");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");


            securityService.addRoleToUser("user", "USER");
            securityService.addRoleToUser("admin", "ADMIN");
            securityService.addRoleToUser("admin", "USER");
            securityService.addRoleToUser("oussama", "ADMIN");
            securityService.addRoleToUser("oussama", "USER");
        };
    }

}


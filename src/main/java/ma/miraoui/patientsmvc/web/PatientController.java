package ma.miraoui.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.miraoui.patientsmvc.entities.Patient;
import ma.miraoui.patientsmvc.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping(path = "index")
    public String patients(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patients";
    }
}

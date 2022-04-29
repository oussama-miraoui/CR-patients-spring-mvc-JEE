package ma.miraoui.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.miraoui.patientsmvc.entities.Medecin;
import ma.miraoui.patientsmvc.entities.Patient;
import ma.miraoui.patientsmvc.repositories.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MedecinController {

    private MedecinRepository medecinRepository;

    @GetMapping(path = "/user/medecins")
    public String medecins(Model model,
                           @RequestParam(name = "keyword", defaultValue ="") String keyword,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "page", defaultValue = "0") int page){
        Page<Medecin> pageMedecins = medecinRepository.findByNomContains(keyword, PageRequest.of(page,size));

        model.addAttribute("medecins", pageMedecins.getContent());
        model.addAttribute("pages", new int [pageMedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPage",pageMedecins.getTotalPages());
        return "medecins";
    }
    @GetMapping(path="/admin/delete/medecin")
    public String delete(Long id, String keyword, int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/admin/formMedecins")
    public String formMedecins(Model model){
        model.addAttribute("medecin", new Medecin());
        return "formMedecins";
    }


    @PostMapping(path = "/admin/save/medecin")
    public String save(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="keyword", defaultValue = "") String keyword){
        if(bindingResult.hasErrors()){
            return "formMedecins";
        }
        medecinRepository.save(medecin);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/admin/edit/medecin")
    public String editPatient(Long id, Model model , int page, String keyword){
        Medecin medecin= medecinRepository.findById(id).orElse(null);
        model.addAttribute("medecin", medecin);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editMedecin";
    }
}

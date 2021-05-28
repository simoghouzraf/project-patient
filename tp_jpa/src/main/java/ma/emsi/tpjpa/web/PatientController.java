package ma.emsi.tpjpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javassist.expr.NewArray;
import ma.emsi.tpjpa.entities.Patient;
import ma.emsi.tpjpa.repositories.PatientRepository;

@Controller 
public class PatientController {
	
	
	
	
	
	@Autowired
	private PatientRepository patientrepository;
	
	@GetMapping("/listpatient")
	public String listpatient(Model m, @RequestParam (defaultValue = "0", name = "page") int page, 
			                           @RequestParam (defaultValue = "4", name="size") int size,
			                           @RequestParam (defaultValue = "",name = "keyword") String keyword) {
		Page <Patient> pageliste = patientrepository.findByNomContains(keyword ,PageRequest.of(page, size));
		m.addAttribute("Patients",pageliste.getContent());
		m.addAttribute("pages",new int [pageliste.getTotalPages()]);
		m.addAttribute("current",page);
		m.addAttribute("keyword",keyword);
		return "listpatient";

}
	
 @GetMapping("/deletePatient")
 public String DeletePatient(Long id) {
	 
	 
	 patientrepository.deleteById(id);
	return  "redirect:/listpatient";
	 	 
	 
 }
 
 
 @GetMapping("/addpatient")
 public String addpatient(Model m) {
	 
	 m.addAttribute("patient",new Patient());
	 m.addAttribute("mode", "add");

	
	return "formpatient";	 
 }
	
 
 @PostMapping("/savepatient")
 public String AddPatient(Model m,Patient patient) {
	 
	 patientrepository.save(patient);
	return "redirect:/listpatient";
	 
	 
	 
 }
	
 
 @GetMapping("/editpatient")
 public String EditPatient(Model m,Long id) {
	 Patient patient = patientrepository.findById(id).get();
	 m.addAttribute("patient",patient);
	 m.addAttribute("mode", "edit");
	return "formpatient";}
 
 @GetMapping("/")
 public String HomePage() {
	return "welcome";
	 
 }
 
 
 
 
 
}

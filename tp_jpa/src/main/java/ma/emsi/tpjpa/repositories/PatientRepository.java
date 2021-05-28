package ma.emsi.tpjpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.tpjpa.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
	public Page<Patient> findByNomContains(String Nom, Pageable pageable);
	public List<Patient> findByMalade (boolean b);
	public List<Patient> findByNomContainsAndMalade (String Nom, boolean b);

}

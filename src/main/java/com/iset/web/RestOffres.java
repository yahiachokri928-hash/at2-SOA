package com.iset.web;

import com.iset.serviceoffreemploi.dao.OffreRepository;
import com.iset.serviceoffreemploi.entities.Offre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/Offres")
public class RestOffres {
    @Autowired
    OffreRepository offreRepository;

    // GET ALL
    @GetMapping
    public List<Offre> getAll() {
        return offreRepository.findAll();
    }

    // GET BY ID
    @GetMapping("/{uid}")
    public Offre getbyid(@PathVariable Long uid) {
        return offreRepository.findById(uid).get();
    }

    // POST / ADD[cite: 5]
    @PostMapping
    public Offre saveoffre(@RequestBody Offre newoffre) {
        return offreRepository.save(newoffre);
    }

    @PutMapping("/{id}")
    public Offre updateOffre(@PathVariable Long id, @RequestBody Offre updatedOffre) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        offre.setIntitule(updatedOffre.getIntitule());
        offre.setSpecialite(updatedOffre.getSpecialite());
        offre.setSociete(updatedOffre.getSociete());
        offre.setNbpostes(updatedOffre.getNbpostes());
        offre.setPays(updatedOffre.getPays());
        return offreRepository.save(offre);
    }

    // DELETE[cite: 6]
    @DeleteMapping("/{id}")
    public void deleteoffre(@PathVariable Long id) {
        offreRepository.deleteById(id);
    }
}
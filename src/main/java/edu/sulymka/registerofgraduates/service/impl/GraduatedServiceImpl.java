package edu.sulymka.registerofgraduates.service.impl;

import edu.sulymka.registerofgraduates.exceptions.NullEntityReferenceException;
import edu.sulymka.registerofgraduates.model.Graduated;
import edu.sulymka.registerofgraduates.repository.GraduatedRepository;
import edu.sulymka.registerofgraduates.service.GraduatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GraduatedServiceImpl implements GraduatedService {
    private final GraduatedRepository graduatedRepository;

    @Autowired
    public GraduatedServiceImpl(GraduatedRepository graduatedRepository) {
        this.graduatedRepository = graduatedRepository;
    }


    @Override
    public Graduated addGraduated(Graduated graduated) {
        return graduatedRepository.save(graduated);
    }

    @Override
    public Graduated updateGraduated(Graduated graduated) {
        if (graduated != null) {
            Graduated oldGraduated = readById(graduated.getId());
            if (oldGraduated != null) {
                return graduatedRepository.save(graduated);
            }
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public void deleteGraduated(Long id) {
        graduatedRepository.deleteGraduatedById(id);
    }

    @Override
    public Graduated readById(Long id) {
        return graduatedRepository.findGraduatedById(id)
                .orElseThrow(() -> new NullEntityReferenceException("User by id " + id + " was not found"));
    }

    @Override
    public List<Graduated> getAll() {
        List<Graduated> graduates = graduatedRepository.findAll();
        return graduates.isEmpty() ? new ArrayList<>() : graduates;
    }
}

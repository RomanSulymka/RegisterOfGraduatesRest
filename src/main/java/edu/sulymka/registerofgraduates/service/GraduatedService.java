package edu.sulymka.registerofgraduates.service;

import edu.sulymka.registerofgraduates.model.Graduated;

import java.util.List;

public interface GraduatedService {
     Graduated addGraduated(Graduated graduated);
     Graduated updateGraduated(Graduated graduated);
     void deleteGraduated(Long id);
     Graduated readById(Long id);
     List<Graduated> getAll();
}

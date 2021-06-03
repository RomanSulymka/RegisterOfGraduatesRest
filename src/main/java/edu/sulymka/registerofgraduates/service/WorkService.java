package edu.sulymka.registerofgraduates.service;

import edu.sulymka.registerofgraduates.model.Work;

import java.util.List;

public interface WorkService {
    Work create(Work work);
    Work readById(Long id);
    Work update(Work work);
    void delete(Long id);

    List<Work> getAll();
    List<Work> getByWorkId(Long workId);
    List<Work> getWorksByCompany();
}

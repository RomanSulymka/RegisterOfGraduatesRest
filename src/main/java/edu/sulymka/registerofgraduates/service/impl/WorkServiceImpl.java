package edu.sulymka.registerofgraduates.service.impl;

import edu.sulymka.registerofgraduates.exceptions.NullEntityReferenceException;
import edu.sulymka.registerofgraduates.model.Work;
import edu.sulymka.registerofgraduates.repository.WorkRepository;
import edu.sulymka.registerofgraduates.service.WorkService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkServiceImpl implements WorkService {
    private WorkRepository workRepository;

    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public Work create(Work work) {
        try {
            return workRepository.save(work);
        } catch (IllegalArgumentException e) {
            throw new NullEntityReferenceException("Work cannot be 'null'");
        }
    }

    @Override
    public Work readById(Long id) {
        Optional<Work> optional = workRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Work with id " + id + " not found");
    }

    @Override
    public Work update(Work work) {
        if (work != null) {
            Work oldWork = readById(work.getId());
            if (oldWork != null) {
                return workRepository.save(work);
            }
        }
        throw new NullEntityReferenceException("Work cannot be 'null'");
    }

    @Override
    public void delete(Long id) {
        Work work = readById(id);
        if (work != null) {
            workRepository.delete(work);
        } else {
            throw new EntityNotFoundException("Work with id " + id + " not found");
        }
    }

    @Override
    public List<Work> getAll() {
        List<Work> works = workRepository.findAll();
        return works.isEmpty() ? new ArrayList<>() : works;
    }

    @Override
    public List<Work> getByWorkId(Long workId) {
        List<Work> works = workRepository.getByWorkId(workId);
        return works.isEmpty() ? new ArrayList<>() : works;
    }
}

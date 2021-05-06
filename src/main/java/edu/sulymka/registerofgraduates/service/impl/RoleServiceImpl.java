package edu.sulymka.registerofgraduates.service.impl;

import edu.sulymka.registerofgraduates.exceptions.NullEntityReferenceException;
import edu.sulymka.registerofgraduates.model.Role;
import edu.sulymka.registerofgraduates.repository.RoleRepository;
import edu.sulymka.registerofgraduates.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        try {
            return roleRepository.save(role);
        }catch (IllegalArgumentException e){
            throw new NullEntityReferenceException("role can`t be 'null'");
        }
    }

    @Override
    public Role update(Role role) {
        if (role != null){
            Role oldRole = readById(role.getId());
            if (oldRole != null){
                return roleRepository.save(role);
            }
        }
        throw new NullEntityReferenceException("role can`t be 'null'");
    }

    @Override
    public Role readById(Long id) {
        return roleRepository.findRoleById(id)
                .orElseThrow(() -> new NullEntityReferenceException("Role by id " + id + " was not found"));
    }

    @Override
    public void delete(Long id) {
        Role role = readById(id);
        if (role != null){
            roleRepository.delete(role);
        }else{
            throw new NullEntityReferenceException("Role cannot be 'null'");
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}

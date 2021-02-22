package edu.sulymka.registerofgraduates.service;

import edu.sulymka.registerofgraduates.model.Role;

import java.util.List;

public interface RoleService {
    Role create (Role role);
    Role update (Role role);
    Role readById (Long id);
    void delete (Long id);
    List<Role> getAll();
}

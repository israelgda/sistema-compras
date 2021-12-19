package com.israelgda.sistemacomprasapi.repositories;

import com.israelgda.sistemacomprasapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

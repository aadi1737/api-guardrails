package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.USER;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<USER,Long> {
}

package com.bedigital.application.repositories;

import com.bedigital.application.domain.Client;
import com.bedigital.application.domain.Employee;
import com.bedigital.application.domain.PhysicalClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalClientRepository extends JpaRepository<PhysicalClient, Long> {

}

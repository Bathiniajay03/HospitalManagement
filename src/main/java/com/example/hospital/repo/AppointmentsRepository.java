package com.example.hospital.repo;

import com.example.hospital.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    // Add custom methods if needed
}


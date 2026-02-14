package com.vrize.conferenceroom.repository;

import com.vrize.conferenceroom.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
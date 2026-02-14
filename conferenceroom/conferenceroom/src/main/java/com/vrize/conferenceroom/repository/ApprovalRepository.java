package com.vrize.conferenceroom.repository;

import com.vrize.conferenceroom.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}

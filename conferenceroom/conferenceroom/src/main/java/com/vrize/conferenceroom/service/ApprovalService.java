package com.vrize.conferenceroom.service;

import com.vrize.conferenceroom.dto.ApprovalRequestDTO;
import com.vrize.conferenceroom.entity.Approval;
import com.vrize.conferenceroom.entity.Booking;
import com.vrize.conferenceroom.entity.BookingStatus;
import com.vrize.conferenceroom.entity.Employee;
import com.vrize.conferenceroom.exception.ResourceNotFoundException;
import com.vrize.conferenceroom.repository.ApprovalRepository;
import com.vrize.conferenceroom.repository.BookingRepository;
import com.vrize.conferenceroom.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final BookingRepository bookingRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void approveBooking(Long bookingId, ApprovalRequestDTO dto) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        Employee approver = employeeRepository.findById(dto.getApprovedByEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        booking.setStatus(BookingStatus.APPROVED);

        Approval approval = new Approval();
        approval.setBooking(booking);
        approval.setApprovedBy(approver);
        approval.setApprovalTime(LocalDateTime.now());
        approval.setRemarks(dto.getRemarks());

        approvalRepository.save(approval);
    }
}

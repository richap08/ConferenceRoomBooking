package com.vrize.conferenceroom.controller;

import com.vrize.conferenceroom.dto.ApprovalRequestDTO;
import com.vrize.conferenceroom.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @PutMapping("/{id}/approve")
    public String approveBooking(
            @PathVariable Long id,
            @RequestBody ApprovalRequestDTO dto) {

        approvalService.approveBooking(id, dto);
        return "Booking approved successfully";
    }

    @PutMapping("/{id}/reject")
    public String rejectBooking(
            @PathVariable Long id,
            @RequestBody ApprovalRequestDTO dto) {

        approvalService.approveBooking(id, dto);
        return "Booking rejected successfully";
    }
}

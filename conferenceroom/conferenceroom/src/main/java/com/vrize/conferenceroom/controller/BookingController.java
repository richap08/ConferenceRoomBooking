package com.vrize.conferenceroom.controller;

import com.vrize.conferenceroom.dto.BookingRequestDTO;
import com.vrize.conferenceroom.dto.BookingResponseDTO;
import com.vrize.conferenceroom.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO dto) {
        return bookingService.createBooking(dto);
    }

    @PutMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully";
    }
}

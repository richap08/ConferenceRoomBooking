package com.vrize.conferenceroom.service;

import com.vrize.conferenceroom.dto.BookingRequestDTO;
import com.vrize.conferenceroom.dto.BookingResponseDTO;
import com.vrize.conferenceroom.entity.Booking;
import com.vrize.conferenceroom.entity.BookingStatus;
import com.vrize.conferenceroom.entity.Employee;
import com.vrize.conferenceroom.entity.Room;
import com.vrize.conferenceroom.exception.ConflictException;
import com.vrize.conferenceroom.exception.ResourceNotFoundException;
import com.vrize.conferenceroom.repository.BookingRepository;
import com.vrize.conferenceroom.repository.EmployeeRepository;
import com.vrize.conferenceroom.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EmployeeRepository employeeRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public BookingResponseDTO createBooking(BookingRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (bookingRepository.existsConflictingBooking(
                room.getId(), dto.getStartTime(), dto.getEndTime())) {
            throw new ConflictException("Room already booked in this time slot");
        }

        Booking booking = modelMapper.map(dto, Booking.class);
        booking.setEmployee(employee);
        booking.setRoom(room);
        booking.setStatus(BookingStatus.PENDING);

        Booking saved = bookingRepository.save(booking);

        return modelMapper.map(saved, BookingResponseDTO.class);
    }

    public void cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}

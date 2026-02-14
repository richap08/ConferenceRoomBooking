package com.vrize.conferenceroom.repository;

import com.vrize.conferenceroom.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
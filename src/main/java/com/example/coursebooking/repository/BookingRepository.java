package com.example.coursebooking.repository;

import com.example.coursebooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> getBookingByAppUser_Email(String email);
}

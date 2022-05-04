package com.example.coursebooking.service.impl;

import com.example.coursebooking.exception.AppBasicException;
import com.example.coursebooking.exception.CourseNotFoundException;
import com.example.coursebooking.model.Booking;
import com.example.coursebooking.repository.AppUserRepository;
import com.example.coursebooking.repository.BookingRepository;
import com.example.coursebooking.repository.CourseRepository;
import com.example.coursebooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImplementation implements BookingService {
    private final BookingRepository bookingRepository;
    private final CourseRepository courseRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByAppUserEmail(String email){return bookingRepository.getBookingByAppUser_Email(email);}

    @Override
    public void deleteBookingById(Long id){
        bookingRepository.deleteById(id);
    }

    @Override
    public void assignCourse(Long courseId, Long appUserId)throws AppBasicException{
        Booking booking=new Booking();
        booking.setCourse(courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new));
        booking.setAppUser(appUserRepository.findUserById(appUserId));
        bookingRepository.save(booking);
    }

}

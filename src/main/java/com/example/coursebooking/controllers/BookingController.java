package com.example.coursebooking.controllers;

import com.example.coursebooking.model.AppUser;
import com.example.coursebooking.model.Booking;
import com.example.coursebooking.service.AppUserService;
import com.example.coursebooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final AppUserService appUserService;

    @GetMapping("/bookings")
    public String getAllBookings(Model model){
        List<Booking> bookingList=bookingService.getAllBookings();
        model.addAttribute("bookings",bookingList);
        return "bookings";
    }

    @GetMapping("/book/{id}")
    public String bookCourse(@PathVariable("id") Long id, Principal principal){
        AppUser appUser=appUserService.getAppUserByEmail(principal.getName());
        bookingService.assignCourse(id,appUser.getId());
        return "redirect:/courses";
    }

    @GetMapping("/users_bookings")
    public String usersBookings(Model model, Principal principal){
        List<Booking> bookings=bookingService.getBookingsByAppUserEmail(principal.getName());
        model.addAttribute("user_bookings", bookings);
        return "user_bookings";
    }

    @GetMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/bookings";
    }

    @GetMapping("/deleteUserBooking/{id}")
    public String deleteUserBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/users_bookings";
    }

}

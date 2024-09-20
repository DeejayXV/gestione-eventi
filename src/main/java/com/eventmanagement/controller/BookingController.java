package com.eventmanagement.controller;

import com.eventmanagement.entity.Booking;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.User;
import com.eventmanagement.service.BookingService;
import com.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @PostMapping("/book/{eventId}")
    public Booking bookEvent(@PathVariable Long eventId, @AuthenticationPrincipal User user) {
        Event event = eventService.getEventById(eventId);
        return bookingService.bookEvent(user, event);
    }

    @GetMapping
    public List<Booking> getUserBookings(@AuthenticationPrincipal User user) {
        return bookingService.getUserBookings(user);
    }
}

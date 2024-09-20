package com.eventmanagement.service;

import com.eventmanagement.entity.Booking;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.User;
import com.eventmanagement.repository.BookingRepository;
import com.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    public Booking bookEvent(User user, Event event) {
        if (event.getAvailableSeats() > 0) {
            event.setAvailableSeats(event.getAvailableSeats() - 1);
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setEvent(event);
            booking.setBookingDate(LocalDateTime.now());
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("No seats available for this event.");
    }

    public List<Booking> getUserBookings(User user) {
        return bookingRepository.findByUser(user);
    }
}

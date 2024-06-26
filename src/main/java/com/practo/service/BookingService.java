package com.practo.service;

import com.practo.config.TimeSlotManager;
import com.practo.entity.Booking;
import com.practo.payload.BookingDto;
import com.practo.rpository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TimeSlotManager timeslot;
    public void bookAnAppointment(BookingDto dto){
        List<String> availableTimeSlots = timeslot.getAvailaleTimeSlots();

       Booking booking =new Booking();

       for (String slots:availableTimeSlots){
           if (slots.equals(dto.getBookingTime())){
               booking.setBookingTime(dto.getBookingTime());
               availableTimeSlots.remove(slots);
               timeslot.setAvailableTimeSlots(availableTimeSlots);
           }
       }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() ->{
            System.out.println("Executing code every 24 hours...");
            availableTimeSlots.add("10:15 AM");
            availableTimeSlots.add("11:15 AM");
            availableTimeSlots.add("12:15 PM");
        }, 0, 24, TimeUnit.HOURS);


       booking.setDoctorId(dto.getDoctorId());
       booking.setPatientId(dto.getPatientId());

       if (booking.getBookingTime()!=null){
           bookingRepository.save(booking);
       }else {
           System.out.println("Time Slot Not Available");
       }
    }
}

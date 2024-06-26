package com.practo.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeSlotManager {
    private List<String> availaleTimeSlots;

    public TimeSlotManager(List<String> initialTimeslots){
        this.availaleTimeSlots = initialTimeslots;
    }

    public List<String> getAvailaleTimeSlots(){
        return availaleTimeSlots;
    }

    public List<String> setAvailableTimeSlots (List<String> availableTimeSlots){
        return availaleTimeSlots;
    }


}

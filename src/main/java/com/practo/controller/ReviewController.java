package com.practo.controller;

import com.practo.entity.Doctor;
import com.practo.entity.Review;
import com.practo.payload.DoctorDto;
import com.practo.rpository.DoctorRepository;
import com.practo.service.PatientService;
import com.practo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        return  new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getReviewsByDoctorId(@PathVariable long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).get();
        List<Review> reviews = reviewService.getReviewByDoctorId(doctorId);
        double totalRating =0;
        for(Review review : reviews){
            totalRating += review.getRating();
        }
        double averageRating = totalRating / reviews.size();
        double ratingPercentage = (averageRating/5.0)*100.0;


        DoctorDto dto = new DoctorDto();
        dto.setDoctor(doctor);
        dto.setReviews(reviews);
        dto.setRatingPercentage(ratingPercentage);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

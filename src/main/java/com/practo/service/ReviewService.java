package com.practo.service;

import com.practo.entity.Doctor;
import com.practo.entity.Patient;
import com.practo.entity.Review;
import com.practo.rpository.DoctorRepository;
import com.practo.rpository.PatientRepository;
import com.practo.rpository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    public Review createReview(Review review){
        Doctor doctor = doctorRepo.findById(review.getDoctorId()).get();

        Patient patient = patientRepo.findById(review.getPatientId()).get();

        Review savedReview=null;
        if(doctor!=null || patient!=null){
            savedReview = reviewRepo.save(review);
        }
        return savedReview;
    }

    public List<Review> getReviewByDoctorId(long doctorId){
        List<Review> reviews = reviewRepo.findByDoctorId(doctorId);
        return reviews;
    }

}

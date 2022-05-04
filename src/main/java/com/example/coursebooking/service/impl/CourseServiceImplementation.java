package com.example.coursebooking.service.impl;

import com.example.coursebooking.exception.AppBasicException;
import com.example.coursebooking.model.Course;
import com.example.coursebooking.repository.AppUserRepository;
import com.example.coursebooking.repository.BookingRepository;
import com.example.coursebooking.repository.CourseRepository;
import com.example.coursebooking.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Page<Course> getAllCourses(int pageable){
        return courseRepository.findAll(PageRequest.of(pageable,10));
    }

    @Override
    public Page<Course> getAllCoursesByKeyword(String keyword, int pageable){
        return courseRepository.findByKeyword(keyword, PageRequest.of(pageable,10));
    }

    @Override
    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }

    @Override
    public void addCourse(Course course) {
        try{
            courseRepository.save(course);
        }catch (PersistenceException e){
            throw new AppBasicException("Course can't be saved!");
        }
    }

}

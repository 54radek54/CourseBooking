package com.example.coursebooking.controllers;

import com.example.coursebooking.exception.AppBasicException;
import com.example.coursebooking.model.Course;
import com.example.coursebooking.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @RequestMapping(path = {"/courses", "/search"})
    public String getAllCourses(Model model, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page) {
        Page<Course> courseList;
        if (keyword == null) {
            courseList = courseService.getAllCourses(page);
        } else {
            courseList = courseService.getAllCoursesByKeyword(keyword, page);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("courses", courseList);
        return "courses";
    }

    @GetMapping("/course")
    public String showCourse(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    @PostMapping("/add_course")
    public String addCourse(@Valid @ModelAttribute("course") Course course) throws AppBasicException {
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

}

package com.Perseo_Platform.controllers;


import com.Perseo_Platform.models.Course;
import com.Perseo_Platform.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/carts")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping ("")
    public ResponseEntity<List<Course>> getAllCourses() {List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);}

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") int courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));}

    @PostMapping("")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);}

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") int courseId, @RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(courseId, course);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") int courseId) {courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

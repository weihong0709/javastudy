package com.eric.stream;

import java.util.Optional;

public class OptionalExample {

    public static   String getCourseName(Student student){
        if (student == null){
            return "";
        }
        if (student.getCourse() == null){
            return "";
        }
        if (student.getCourse().getName() == null){
            return "";
        }
        return student.getCourse().getName();
    }

    public static String getCourseName(Optional<Student> studentOptional){
        return studentOptional.map(student -> student.getCourse())
                .map(course->course.getName())
                .orElse("");
    }
}

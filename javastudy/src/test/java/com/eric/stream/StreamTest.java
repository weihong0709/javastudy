package com.eric.stream;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {
    private ArrayList<Student> list = new ArrayList<>();
    @BeforeAll
    public void before() {
        Student student = new Student();
        student.setAge(10);
        student.setName("liuweihong");
        list.add(student);
        Student studentOne = new Student();
        studentOne.setAge(20);
        studentOne.setName("liuhong");
        list.add(studentOne);
    }
    @Test
    public void testToList() {
        List<Student> result = list.stream().filter(student -> student.getAge() == 10).collect(Collectors.toList());
        assertEquals(1,result.size());
        assertEquals("liuweihong",result.get(0).getName());
    }

}

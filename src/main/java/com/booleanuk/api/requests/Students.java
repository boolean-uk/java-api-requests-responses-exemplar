package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName) {
        Student chosenStudent = null;
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                chosenStudent = student;
            }
        }
        return chosenStudent;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student studentDetails) {
        Student updatedStudent = null;
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getFirstName().equals(firstName)) {
                this.students.set(i, studentDetails);
                updatedStudent = this.students.get(i);
                break;
            }
        }
        return updatedStudent;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName) {
        Student studentToDelete = null;
        boolean studentFound = false;
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                studentFound = true;
                studentToDelete = student;
            }
        }
        if (studentFound) {
            this.students.remove(studentToDelete);
        }
        return studentToDelete;
    }
}

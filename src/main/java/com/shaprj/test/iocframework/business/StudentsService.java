package com.shaprj.test.iocframework.business;

import com.shaprj.test.iocframework.business.model.Student;
import com.shaprj.test.iocframework.core.annotation.Injectable;
import com.shaprj.test.iocframework.core.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Injectable
public class StudentsService {

    private Map<String, Student> map = new HashMap<>();

    public StudentsService() {
    }

    @PostConstruct
    public void init() {
        List.of(Student.builder().name("Balbes1").build(),
                        Student.builder().name("Balbes2").build(),
                        Student.builder().name("Balbes3").build()).stream()
                .forEach(s -> addStudent(s));
    }

    public void addStudent(Student student) {
        map.put(student.getName(), student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(map.values());
    }
}

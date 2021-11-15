package com.shaprj.test.iocframework.business;

import com.shaprj.test.iocframework.business.model.Professor;
import com.shaprj.test.iocframework.core.annotation.Injectable;
import com.shaprj.test.iocframework.core.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Injectable
public class ProfessorsService {

    private Map<String, Professor> map = new HashMap<>();

    public ProfessorsService() {
    }

    @PostConstruct
    public void init() {
        List.of(Professor.builder().name("Petr Ivanovich").build()).stream()
                .forEach(p -> addProfessor(p));
    }

    public void addProfessor(Professor professor) {
        map.put(professor.getName(), professor);
    }

    public List<Professor> getAllProfessors() {
        return new ArrayList<>(map.values());
    }

}

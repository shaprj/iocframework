package com.shaprj.test.iocframework.business;

import com.shaprj.test.iocframework.core.annotation.DependencyInject;
import com.shaprj.test.iocframework.core.annotation.Injectable;

@Injectable
public class UniversityReporterService {

    @DependencyInject
    private StudentsService studentsService;
    @DependencyInject
    private ProfessorsService professorsService;
    @DependencyInject
    private Advertisement ad;

    public void printReport() {
        printStudentsInfo(studentsService);
        printProfessors(professorsService);
        ad.printAd("Toyota the best!");
    }

    private void printProfessors(ProfessorsService professorsService) {
        professorsService.getAllProfessors()
                .stream()
                .forEach(System.out::println);
    }

    private void printStudentsInfo(StudentsService studentsService) {
        studentsService.getAllStudents()
                .stream()
                .forEach(System.out::println);
    }

}

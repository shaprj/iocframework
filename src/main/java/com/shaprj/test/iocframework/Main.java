package com.shaprj.test.iocframework;

import com.shaprj.test.iocframework.business.UniversityReporterService;
import com.shaprj.test.iocframework.core.ApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        final UniversityReporterService reporter = context.getInjectable(UniversityReporterService.class);
        reporter.printReport();
    }
}

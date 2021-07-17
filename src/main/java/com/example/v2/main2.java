package com.example.v2;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.v2.configFiles.student.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main2 {


    int a = 1;
    int b=2;

    public static void test() {

        List<Student> listDevs = getDevelopers();

       ISum summer = new ISum(){
            @Override
            public int sum(final int a, final int b){
                return a+b;
            }
        };

        //lambda must return some type
        //1. Compilator will watch left side and there a function lambda that will implement the ISum
        //2. compilator will put types (int) automatically int a, int b
        //3. what will return sum function in  ISum.sum that is int so a+b must be int

         ISum summer2 = (a , b) -> a + b;

        listDevs.sort((Student s1, Student s2) -> s1.getStudentId() - s2.getStudentId());
        listDevs.sort((Student s1, Student s2) -> s1.getStudentName().compareTo( s2.getStudentName()));

        listDevs.forEach(Student -> {System.out.println(Student);});


    }

    private static List<Student> getDevelopers() {
        List<Student> result = new ArrayList<Student>();

        result.add(new Student(33,"mkyong" ));
        result.add(new Student(20 ,"alvin"));
        result.add(new Student(10, "jason"));
        result.add(new Student(15,"iris") );

        return result;


    }

    @FunctionalInterface
    public interface ISum{
        public int sum(int a, int b);


    }


}

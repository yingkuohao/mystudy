package com.mylearn.bdd.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/5/22
 * Time: 下午2:00
 * CopyRight: taobao
 * Descrption:
 */

public class ClassRoom {
    private List<Student> students = new ArrayList<Student>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudent(String studentName) {
        for (Student student : students) {
            if (studentName.equals(student.getName())) {
                return student;
            }
        }
        return null;
    }
}

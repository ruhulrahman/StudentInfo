package com.example.studentinfo.model;

public class Semester {
    private String studentId;
    private String studentSemester;
    private double studentCgpa;

    public Semester() {

    }

    public Semester(String studentId, String studentSemester, double studentCgpa) {
        this.studentId = studentId;
        this.studentSemester = studentSemester;
        this.studentCgpa = studentCgpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentSemester() {
        return studentSemester;
    }

    public double getStudentCgpa() {
        return studentCgpa;
    }
}

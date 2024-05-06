/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9_java.school_bussiness_tour_management.models;

import java.util.List;

/**
 *
 * @author PC
 */
public class Class {

    public static int count = 0;

    private int id;
    private String code;
    private String name;
    private List<Student> students;

    public Class() {
    }

    public Class(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return id + "," + code + "," + name + "," + students;
    }

    public String toStringFile() {
        return id + "," + code + "," + name;
    }

}

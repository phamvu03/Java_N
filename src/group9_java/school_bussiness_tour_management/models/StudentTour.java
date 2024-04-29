/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9_java.school_bussiness_tour_management.models;

/**
 *
 * @author PC
 */
public class StudentTour {

    private int studentId;
    private int tourId;
    private int rate;

    public StudentTour() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return studentId + "," + tourId + "," + rate;
    }
    
    

}

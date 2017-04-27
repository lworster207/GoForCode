/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ClassRosterController {

    ClassRosterView view;
    ClassRosterDao dao;

    //private UserIO io = new UserIOConsoleImpl();
    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassRosterDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterDaoException {
        // display banner
        view.displayCreateStudentBanner();

        // create the student object
        Student newStudent = view.getNewStudentInfo();

        // store the new student
        dao.addStudent(newStudent.getStudentId(), newStudent);

        // show user the results
        view.displayCreateSuccessBanner();
    }

    private void listStudents() throws ClassRosterDaoException {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterDaoException {
        // show the banner
        view.displayDisplayStudentBanner();

        // get the studenty id
        String studentId = view.getStudentIdChoice();

        // get the student object for that id
        Student student = dao.getStudent(studentId);

        // diplay the student info
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterDaoException {
        // display banner
        view.displayRemoveStudentBanner();

        // get the student id to remove
        String studentId = view.getStudentIdChoice();

        // remove the student
        dao.removeStudent(studentId);

        // display a banner
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
        this.dao = dao;
        this.view = view;
    }

}

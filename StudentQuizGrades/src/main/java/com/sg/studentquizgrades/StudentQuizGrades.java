/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.studentquizgrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StudentQuizGrades {

    public static final int QUIT = 1;
    public static final int ADDSTUDENT = 1;
    public static final int REMOVESTUDENT = 2;
    public static final int SHOWSTUDENTLIST = 3;
    public static final int SHOWSTUDENTAVERAGE = 4;
    public static final int SHOWSTUDENTSCORES = 5;

    public static HashMap<String, ArrayList<Integer>> studentMap = new HashMap<>();
    public static MyUserIO myIO = new MyUserIO();

    public static void main(String[] args) {
        initStudentMap();
        showStudentAverage("Bob");
        showStudentAverage("Jill");

    }
    // initialize student data

    // display menu options
    // capture option
    // while option != quit
    public static int getOption() {
        // prompt the user for what they wish to do
        // return 0 when exiting.

        String prompt = ADDSTUDENT + " - Add User\n" + REMOVESTUDENT + " - Remove User\n" + SHOWSTUDENTLIST + " - List Students\n" + SHOWSTUDENTAVERAGE + " - Student Average\n" + SHOWSTUDENTSCORES + " - Student Scores\n" + QUIT + " - Exit";
        int command;
        command = myIO.readInt(prompt, QUIT, SHOWSTUDENTSCORES);

        while (command != QUIT) {
            switch (command) {
                case ADDSTUDENT:
                    // addStudent();
                    break;
                case REMOVESTUDENT:
                    break;
                case SHOWSTUDENTLIST:
                    break;
                case SHOWSTUDENTAVERAGE:
                    break;
                case SHOWSTUDENTSCORES:
                    break;

            }
            command = myIO.readInt(prompt, QUIT, SHOWSTUDENTSCORES);
        }
        return (command);
    }

    public static void addStudent(String name, ArrayList scores) {
        ArrayList<Integer> tempScores = new ArrayList<>(scores);
        //tempScores = Collection(scores);
        studentMap.put(name, tempScores);

    }

    public static void removeStudent() {

    }

    public static void showStudentList() {

    }

    public static void showStudentAverage(String studentKey) {
        Set<String> keys = studentMap.keySet();
        ArrayList<Integer> quizScores = studentMap.get(studentKey);

        Iterator itr = quizScores.iterator();

        int quizTotal = 0;

        while (itr.hasNext()) {
            //quizTotal += (Integer) itr.next();
            myIO.print(quizScores.itr.next());
        }
        quizTotal = 0;
        myIO.println("Iterator Total: " + quizTotal);
        if (quizScores.size() > 0) {
            for (Integer quizGrade : quizScores) {
                quizTotal += quizGrade;
            }
            myIO.println(studentKey + "Total " + quizTotal + " average quiz score is: " + quizTotal / quizScores.size());
        } else {
            myIO.println(studentKey + " has no recorded quiz scores.");
        }

    }

    public static void showStudentScores() {
        Set<String> keys = studentMap.keySet();
        //Collection<ArrayList<Integer>> quizScores = studentMap.values();
        ArrayList<Integer> quizScores;
        for (String key : keys) {
            quizScores = studentMap.get(key);
            myIO.print(key);
            for (Integer score : quizScores) {
                myIO.print(" " + score.toString());
            }
            myIO.println("");
        }
    }

    public static void initStudentMap() {
        Random getScore = new Random();
        ArrayList<Integer> studentScores = new ArrayList<>();
        String[] initialStudents = {"Joe", "Bob", "Ted", "Fred", "Moe", "Curly", "Jill"};

        // give the students some quiz grades
        for (int studentCount = 0; studentCount < initialStudents.length; studentCount++) {
            for (int scoreCounter = 0; scoreCounter <= 9; scoreCounter++) {
                studentScores.add(getScore.nextInt(40) + 61);
            }

            addStudent(initialStudents[studentCount], studentScores);
            studentScores.clear();
        }
        showStudentScores();

    }

}

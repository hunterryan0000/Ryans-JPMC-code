package com.techelevator;

public class HomeworkAssignment {

    private int earnedMarks;
    private int possibleMarks;
    private String submitterName;
    private String letterGrade;

    public int getEarnedMarks() {
        return earnedMarks;
    }

    public void setEarnedMarks(int earnedMarks) {
        this.earnedMarks = earnedMarks;
    }

    public int getPossibleMarks() {
        return possibleMarks;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public String getLetterGrade() {
        double grade = (double) getEarnedMarks() / getPossibleMarks();
            // Cast to double in order to get the proper decimal value
        if (grade >= 0.9)
            letterGrade = "A";
        else if (grade >= 0.8)
            letterGrade = "B";
        else if (grade >= 0.7)
            letterGrade = "C";
        else if (grade >= 0.6)
            letterGrade = "D";
        else
            letterGrade = "F";
        return letterGrade;
    }

    public HomeworkAssignment(int possibleMarks, String submitterName) {
        this.possibleMarks = possibleMarks;
        this.submitterName = submitterName;
    }

    public HomeworkAssignment() {
    }
}

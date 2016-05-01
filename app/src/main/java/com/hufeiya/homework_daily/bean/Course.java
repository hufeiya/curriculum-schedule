package com.hufeiya.homework_daily.bean;

import java.util.ArrayList;

/**
 * Created by hufeiya on 16/4/25.
 */
public class Course {
    private int id;
    private String name;
    private String room;
    private String teacher;
    private int week;
    private ArrayList<Integer> pitchNumbers;
    private ArrayList<Integer> startEndWeekNumbers;
    private ArrayList<Time> startEndTimes;
    public  static String[] weekName = {"周一","周二","周三","周四","周五","周六","周日"};

    public Course(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public ArrayList<Integer> getPitchNumbers() {
        return pitchNumbers;
    }

    public void setPitchNumbers(ArrayList<Integer> pitchNumbers) {
        this.pitchNumbers = pitchNumbers;
    }

    public ArrayList<Integer> getStartEndWeekNumbers() {
        return startEndWeekNumbers;
    }

    public void setStartEndWeekNumbers(ArrayList<Integer> startEndWeekNumbers) {
        this.startEndWeekNumbers = startEndWeekNumbers;
    }

    public ArrayList<Time> getStartEndTimes() {
        return startEndTimes;
    }

    public void setStartEndTimes(ArrayList<Time> startEndTimes) {
        this.startEndTimes = startEndTimes;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + room  + "  " + teacher + "  " + week  + "  " + pitchNumbers.get(0) + "  " + pitchNumbers.get(1);
    }
}

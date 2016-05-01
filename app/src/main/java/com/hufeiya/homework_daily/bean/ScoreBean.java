package com.hufeiya.homework_daily.bean;

import java.io.Serializable;

/**
 * Created by Robert on 2015/9/21.
 */
public class ScoreBean implements Serializable {

    private String course;
    private String credit;
    private String score;

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getCredit() {
        return credit;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return course + "  " + credit + "  " + score;
    }
}

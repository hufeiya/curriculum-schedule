package com.hufeiya.homework_daily.bean;

import java.io.Serializable;

/**
 * Created by hufeiya on 2016/5/1.
 */
public class StudentInfoBean implements Serializable {

    private String studentId;
    private String studentName;
    private String studentClass;

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

}

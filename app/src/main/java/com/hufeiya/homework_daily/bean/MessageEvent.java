package com.hufeiya.homework_daily.bean;

/**
 * Created by hufeiya on 16/4/26.
 */
public class MessageEvent {
    public static final int SHOW_SAVE_BUTTON = 0;
    public static final int HIDE_COURSE_FAB = 1;
    public static final int SHOW_COURSE_FAB = 2;
    public static final int UPDATE_COURSE_LIST = 3;
    public final int message;
    public String jsonCourse;

    public MessageEvent(int message) {
        this.message = message;
    }
    public MessageEvent(int message,String jsonCourse) {
        this.message = message;
        this.jsonCourse = jsonCourse;
    }
}
package com.hufeiya.homework_daily.utils;


import com.hufeiya.homework_daily.bean.Course;
import com.hufeiya.homework_daily.bean.StudentInfoBean;
import com.hufeiya.homework_daily.bean.ScoreBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2015/9/21.
 */
public class HtmlParse {

    private Document doc;

    public HtmlParse(String htmlStr){
        doc = Jsoup.parse(htmlStr);
    }

    public StudentInfoBean getStudentInfo(){

        StudentInfoBean std = new StudentInfoBean();
        Element stdId = doc.select("span#lblXh").first();
        Element stdName = doc.select("span#lblXm").first();
        Element stdClass = doc.select("span#lblBjmc").first();

        std.setStudentId(stdId.text());
        std.setStudentName(stdName.text());
        std.setStudentClass(stdClass.text());
        return std;
    }

    public String getQueryViewState(){
        String viewState=null;
        Elements input = doc.getElementsByTag("input");
        for(int i=0;i<input.size();i++){
            Element ele_viewState = input.get(i);
            if(ele_viewState.attr("name").equals("__VIEWSTATE")){
                viewState = ele_viewState.val();
                break;
            }
        }
        return viewState;
    }

    public List<ScoreBean> getScore()
    {

        List<ScoreBean> scoreList = new ArrayList<ScoreBean>();

        Element	 table = doc.select("table#DataGrid1").first();
        Elements trs = table.select("tr");
        for(int i=1;i<trs.size();i++)
        {
            ScoreBean scorebean = new ScoreBean();
            Elements td = trs.get(i).select("td");
            Element eleCourse = td.get(1);
            Element eleScore = td.get(3);
            Element eleCredit = td.get(5);
            scorebean.setCourse(eleCourse.text());
            scorebean.setCredit(eleCredit.text());
            scorebean.setScore(eleScore.text());
            scoreList.add(scorebean);
        }
        return scoreList;
    }

    public List<Course> getCourseList(){
        List<Course> courseList = new ArrayList<>();
        Element	 table = doc.select("table#DG_GXK").first();
        Elements trs = table.select("tr");
        for(int i=1;i<trs.size();i++) {
            Elements td = trs.get(i).select("td");
            for(int j =1; j < td.size();j++){
                String content = td.get(j).text();
                if( ! content.isEmpty()){
                    String[] strings = content.split("/");
                    if(strings.length == 0){
                        continue;
                    }
                    Course course = new Course(courseList.size());
                    course.setName(strings[0]);
                    if(strings.length > 1){
                        String[] weeksAndLocation = strings[1].split("\\|");
                        String weeks = null,location = null;
                        if(weeksAndLocation.length == 1){
                            if(weeksAndLocation[0].contains("(")){
                                weeks = weeksAndLocation[0];
                            }else{
                                location = weeksAndLocation[0];
                            }
                        }else  if(weeksAndLocation.length == 2){
                            weeks = weeksAndLocation[0];
                            location = weeksAndLocation[1];
                        }
                        if(weeks != null && weeks.contains("-") && weeks.endsWith("周")){
                            weeks = weeks.substring(0,weeks.length()-1);
                            String[] weeksNumber = weeks.split("-");
                            if(weeksNumber.length == 2 && weeksNumber[0].matches("[0-9]+") && weeksNumber[1].matches("[0-9]+")){
                                ArrayList<Integer> startEndWeeks = new ArrayList<>();
                                startEndWeeks.add(Integer.parseInt(weeksNumber[0]));
                                startEndWeeks.add(Integer.parseInt(weeksNumber[1]));
                                course.setStartEndWeekNumbers(startEndWeeks);
                            }
                        }
                        if(location != null){
                            course.setRoom(location);
                        }
                        if(strings.length > 2){
                            course.setRoom(strings[2]);
                            if(strings.length > 3){
                                course.setTeacher(strings[3]);
                            }
                        }

                    }
                    course.setWeek(j-1);
                    ArrayList<Integer> pitchNumbers = new ArrayList<>();
                    pitchNumbers.add(i-1);
                    int courseLen = 1;
                    try{
                        courseLen = Integer.parseInt(td.get(j).attr("rowspan"));
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    pitchNumbers.add(i+courseLen-1);
                    course.setPitchNumbers(pitchNumbers);
                    //TODO:Bug Not Fix
                    /*
                    for(int k = i+1; k < i+courseLen;k++){
                        trs.get(k).select("td").add(j,new Element(Tag.valueOf("td"),""));
                    }*/
                    courseList.add(course);
                }
            }
        }
        return courseList;
    }
}

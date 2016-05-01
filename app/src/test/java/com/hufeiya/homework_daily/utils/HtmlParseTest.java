package com.hufeiya.homework_daily.utils;

import org.junit.Test;

/**
 * Created by hufeiya on 16/5/1.
 */
public class HtmlParseTest {
    @Test
    public void testGetCourseList(){
        HtmlParse htmlParse = new HtmlParse(COURSE_LIST_TEST_1);
        System.out.print(htmlParse.getCourseList().toString());

    }

    private static final String COURSE_LIST_TEST_1 = "<tr>\n" +
            "\t\t\t\t\t<td style=\"HEIGHT: 116px\" valign=\"top\"><font face=\"宋体\">\n" +
            "\t\t\t\t\t\t\t<table id=\"Table3\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" align=\"center\" border=\"0\">\n" +
            "\t\t\t\t\t\t\t\t<tbody><tr>\n" +
            "\t\t\t\t\t\t\t\t\t<td style=\"COLOR: #006699; HEIGHT: 14px\" align=\"left\" bgcolor=\"#c9d3e9\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<div style=\"DISPLAY: inline; FONT-SIZE: 13px; WIDTH: 71px; COLOR: black; HEIGHT: 16px\" ms_positioning=\"FlowLayout\">&nbsp;学&nbsp; \n" +
            "\t\t\t\t\t\t\t\t\t\t\t期：</div>\n" +
            "\t\t\t\t\t\t\t\t\t\t<select name=\"Cbo_Xueqi\" id=\"Cbo_Xueqi\">\n" +
            "\t<option value=\"1999/2000(1)\">1999/2000(1)</option>\n" +
            "\t<option value=\"1999/2000(2)\">1999/2000(2)</option>\n" +
            "\t<option value=\"2000/2001(1)\">2000/2001(1)</option>\n" +
            "\t<option value=\"2000/2001(2)\">2000/2001(2)</option>\n" +
            "\t<option value=\"2001/2002(1)\">2001/2002(1)</option>\n" +
            "\t<option value=\"2001/2002(2)\">2001/2002(2)</option>\n" +
            "\t<option value=\"2002/2003(1)\">2002/2003(1)</option>\n" +
            "\t<option value=\"2002/2003(2)\">2002/2003(2)</option>\n" +
            "\t<option value=\"2003/2004(1)\">2003/2004(1)</option>\n" +
            "\t<option value=\"2003/2004(2)\">2003/2004(2)</option>\n" +
            "\t<option value=\"2004/2005(1)\">2004/2005(1)</option>\n" +
            "\t<option value=\"2004/2005(2)\">2004/2005(2)</option>\n" +
            "\t<option value=\"2005/2006(1)\">2005/2006(1)</option>\n" +
            "\t<option value=\"2005/2006(2)\">2005/2006(2)</option>\n" +
            "\t<option value=\"2006/2007(1)\">2006/2007(1)</option>\n" +
            "\t<option value=\"2006/2007(2)\">2006/2007(2)</option>\n" +
            "\t<option value=\"2007/2008(1)\">2007/2008(1)</option>\n" +
            "\t<option value=\"2007/2008(2)\">2007/2008(2)</option>\n" +
            "\t<option value=\"2008/2009(1)\">2008/2009(1)</option>\n" +
            "\t<option value=\"2008/2009(2)\">2008/2009(2)</option>\n" +
            "\t<option value=\"2009/2010(1)\">2009/2010(1)</option>\n" +
            "\t<option value=\"2009/2010(2)\">2009/2010(2)</option>\n" +
            "\t<option value=\"2010/2011(1)\">2010/2011(1)</option>\n" +
            "\t<option value=\"2010/2011(2)\">2010/2011(2)</option>\n" +
            "\t<option value=\"2011/2012(1)\">2011/2012(1)</option>\n" +
            "\t<option value=\"2011/2012(2)\">2011/2012(2)</option>\n" +
            "\t<option selected=\"selected\" value=\"2012/2013(1)\">2012/2013(1)</option>\n" +
            "\t<option value=\"2012/2013(2)\">2012/2013(2)</option>\n" +
            "\t<option value=\"2013/2014(1)\">2013/2014(1)</option>\n" +
            "\t<option value=\"2013/2014(2)\">2013/2014(2)</option>\n" +
            "\t<option value=\"2014/2015(1)\">2014/2015(1)</option>\n" +
            "\t<option value=\"2014/2015(2)\">2014/2015(2)</option>\n" +
            "\t<option value=\"2015/2016(1)\">2015/2016(1)</option>\n" +
            "\t<option value=\"2015/2016(2)\">2015/2016(2)</option>\n" +
            "\t<option value=\"2016/2017(1)\">2016/2017(1)</option>\n" +
            "\t<option value=\"2016/2017(2)\">2016/2017(2)</option>\n" +
            "\t<option value=\"2017/2018(1)\">2017/2018(1)</option>\n" +
            "\t<option value=\"2017/2018(2)\">2017/2018(2)</option>\n" +
            "\t<option value=\"2018/2019(1)\">2018/2019(1)</option>\n" +
            "\t<option value=\"2018/2019(2)\">2018/2019(2)</option>\n" +
            "\t<option value=\"2098/2099(1)\">2098/2099(1)</option>\n" +
            "\n" +
            "</select>\n" +
            "\t\t\t\t\t\t\t\t\t\t<input type=\"submit\" name=\"Button1\" value=\"按课程查询\" id=\"Button1\">&nbsp;\n" +
            "\t\t\t\t\t\t\t\t\t\t<input type=\"submit\" name=\"Button2\" value=\"按课表查询\" id=\"Button2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"Lb_jsinfo\"><font size=\"2\">学生:胡飞亚的课表信息</font></span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"Lbl_ZYroom\"><font size=\"2\"></font></span></td>\n" +
            "\t\t\t\t\t\t\t\t</tr>\n" +
            "\t\t\t\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t\t\t\t<td style=\"POSITION: static\" valign=\"top\" align=\"middle\">\n" +
            "\t\t\t\t\t\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t\t\t\t</tr>\n" +
            "\t\t\t\t\t\t\t</tbody></table>\n" +
            "\t\t\t\t\t\t\t</font><p><font face=\"宋体\">\n" +
            "\t\t\t\t\t\t</font>\n" +
            "\t\t\t\t\t\t<table class=\"style6\" cellspacing=\"0\" cellpadding=\"1\" rules=\"all\" bordercolor=\"#2650A6\" border=\"2\" id=\"DG_GXK\" bgcolor=\"#F1F3F9\" height=\"100%\" width=\"100%\">\n" +
            "\t<tbody><tr bgcolor=\"#C9D3E9\">\n" +
            "\t\t<td><font color=\"White\" size=\"2\"><b>节</b></font></td><td><font color=\"White\" size=\"2\"><b>星期一</b></font></td><td><font color=\"White\" size=\"2\"><b>星期二</b></font></td><td><font color=\"White\" size=\"2\"><b>星期三</b></font></td><td><font color=\"White\" size=\"2\"><b>星期四</b></font></td><td><font color=\"White\" size=\"2\"><b>星期五</b></font></td><td><font color=\"White\" size=\"2\"><b>星期六</b></font></td><td><font color=\"White\" size=\"2\"><b>星期日</b></font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_LblXiaoQU\">第1节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ1\">高等数学AⅠ/(1-16周)|广知楼/广A101/寿华好</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ2\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ3\">大学英语读写译Ⅱ/(1-16周)|语林楼/语203/金艳</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ5\">C++程序设计Ⅰ/(1-16周)|法学楼/法学A201/顾国民</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl2_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl3_LblXiaoQU\">第2节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl3_XQ2\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl3_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl3_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl3_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_LblXiaoQU\">第3节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ1\">C++程序设计Ⅰ/(1-16周)|法学楼/法学A202/顾国民</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ2\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ3\">高等数学AⅠ/(1-16周)|广知楼/广A101/寿华好</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ5\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl4_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl5_LblXiaoQU\">第4节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl5_XQ2\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl5_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl5_XQ5\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl5_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl5_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_LblXiaoQU\">第5节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ1\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ2\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ3\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ5\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl6_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_LblXiaoQU\">第6节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ1\">中国文化要览(A)Ⅰ/(1-16周)|广知楼/广A215/沈小仙</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ2\">离散数学AⅠ/(1-16周)|郁文楼/郁文B410/张永良</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ3\">大学英语视听说Ⅱ/(1-16周,双周不上)|语林楼/语203/金艳</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ5\">高等数学AⅠ/(1-16周)|广知楼/广A101/寿华好</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl7_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl8_LblXiaoQU\">第7节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl8_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl8_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl8_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_LblXiaoQU\">第8节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ1\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ2\">体育:乒乓球/(1-16周)/吴振华</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ3\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ5\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl9_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_LblXiaoQU\">第9节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_XQ1\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_XQ3\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_XQ5\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl10_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_LblXiaoQU\">第10节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ1\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"3\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ2\">思想道德修养与法律基/(1-16周)|仁和楼/仁和211/耿依娜</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ3\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ4\">计算机科学导论Ⅰ/(1-8周)/王万良 王卫红</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td rowspan=\"2\"><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ5\">离散数学AⅠ/(1-16周,单周不上)|仁和楼/仁和103/张永良</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl11_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl12_LblXiaoQU\">第11节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl12_XQ1\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl12_XQ3\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl12_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl12_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr><tr>\n" +
            "\t\t<td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_LblXiaoQU\">第12节</span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_XQ1\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_XQ3\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_XQ4\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_XQ5\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_XQ6\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td><td><font size=\"2\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span id=\"DG_GXK__ctl13_XQ7\"></span>\n" +
            "\t\t\t\t\t\t\t\t\t</font></td>\n" +
            "\t</tr>\n" +
            "</tbody></table></p>\n" +
            "\t\t\t\t\t</td>\n" +
            "\t\t\t\t</tr>";
}

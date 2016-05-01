package com.hufeiya.homework_daily.utils;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by Robert on 2015/9/21.
 * Modified by hufeiya on 2016/5/1.
 */
public class HttpUtil {

    private String userId;
    private String password;
    private String term;
    private Cookie[] user_cookie;

    public HttpUtil(String userId, String password, String term)
    {
        this.userId = userId;
        this.password = password;
        this.term = term;
    }

    /**
     * 登入功能的post
     */
    public String login() throws Exception
    {
        HttpClient loginClient = new HttpClient();
        PostMethod loginPost = new PostMethod(Constant.URL_LOGIN);

        loginPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gb2312");
        loginPost.addParameter("__EVENTTARGET", "");
        loginPost.addParameter("__EVENTARGUMENT", "");
        loginPost.addParameter("__VIEWSTATE", Constant.LOGIN_ConstantParam);
        loginPost.addParameter("Cbo_LX", "学生");
        loginPost.addParameter("Txt_UserName", userId);
        loginPost.addParameter("Txt_Password", password);
        loginPost.addParameter("Img_DL.x", "0");
        loginPost.addParameter("Img_DL.y", "0");


        int loginStatusCode = loginClient.executeMethod(loginPost);

        // 得到用户cookie信息
        user_cookie = loginClient.getState().getCookies();

        if (user_cookie.length != 0)
        {
            if(loginStatusCode == 302)
            {
                loginPost.releaseConnection();
                return "LoginSuccess";
            }
            else
            {
                return "LoginFail";
            }

        } else
        {
            return "SystemError";
        }

    }

    public String getViewStateHtml() throws Exception
    {
        HttpClient viewStateClient = new HttpClient();
        GetMethod viewStateGet = new GetMethod(Constant.URL_XQKB);
        viewStateGet.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gb2312");
        viewStateGet.setRequestHeader("Cookie", user_cookie[0].toString()+";"+user_cookie[1].toString());
        viewStateClient.executeMethod(viewStateGet);

        String viewStateResponse = new String(viewStateGet.getResponseBody(),"gb2312");
        viewStateGet.releaseConnection();

        return viewStateResponse;
    }

    /**
     * 查询学期功能的post
     */

    public String query(String viewState) throws Exception
    {

        HttpClient queryClient = new HttpClient();
        PostMethod queryPost = new PostMethod(Constant.URL_CJCX);
        queryPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gb2312");
        queryPost.addParameter("__EVENTTARGET", "");
        queryPost.addParameter("__EVENTARGUMENT", "");
        queryPost.addParameter("__VIEWSTATE", viewState);
        queryPost.addParameter("1", "rbtnXq");
        queryPost.addParameter("ddlXq", term);
        queryPost.addParameter("ddlKc", "＝所有课程＝");
        queryPost.addParameter("Button1", "普通考试成绩查询");
        queryPost.setRequestHeader("Cookie", user_cookie[0].toString()+";"+user_cookie[1].toString());
        queryClient.executeMethod(queryPost);

        String queryResponse = new String(queryPost.getResponseBody(),"gb2312");
        queryPost.releaseConnection();
        return queryResponse;
    }

    /**
     * 查询学年功能的post
     */
    public String queryYear(String viewState) throws Exception
    {
        HttpClient queryYearClient = new HttpClient();
        PostMethod queryYearPost = new PostMethod(Constant.URL_CJCX);
        queryYearPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gb2312");
        queryYearPost.addParameter("__EVENTTARGET", "");
        queryYearPost.addParameter("__EVENTARGUMENT", "");
        queryYearPost.addParameter("__VIEWSTATE", viewState);
        queryYearPost.addParameter("1", "rbtnXn");
        queryYearPost.addParameter("ddlXn", term);
        queryYearPost.addParameter("ddlKc", "＝所有课程＝");
        queryYearPost.addParameter("Button1", "普通考试成绩查询");
        queryYearPost.setRequestHeader("Cookie", user_cookie[0].toString());
        queryYearClient.executeMethod(queryYearPost);

        // 得到返回页面并打印
        String queryResponse = new String(queryYearPost.getResponseBody(),"gb2312");
        queryYearPost.releaseConnection();
        return queryResponse;
    }

    /**
     * 查询学期课表功能的post
     */

    public String queryCourses(String viewState) throws Exception
    {

        HttpClient queryClient = new HttpClient();
        PostMethod queryPost = new PostMethod(Constant.URL_XQKB);
        queryPost.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gb2312");
        queryPost.addParameter("__EVENTTARGET", "");
        queryPost.addParameter("__EVENTARGUMENT", "");
        queryPost.addParameter("__VIEWSTATE", viewState);
        queryPost.addParameter("Cbo_Xueqi", term);
        queryPost.addParameter("Button2", "按课表查询");
        queryPost.setRequestHeader("Cookie", user_cookie[0].toString()+";"+user_cookie[1].toString());
        //queryPost.toString();
        queryClient.executeMethod(queryPost);

        String queryResponse = new String(queryPost.getResponseBody(),"gb2312");
        queryPost.releaseConnection();
        return queryResponse;
    }
}

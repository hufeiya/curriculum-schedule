package com.hufeiya.homework_daily;

/**
 * Created by hufeiya on 16/5/1.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hufeiya.homework_daily.bean.Course;
import com.hufeiya.homework_daily.bean.ScoreBean;
import com.hufeiya.homework_daily.bean.StudentInfoBean;
import com.hufeiya.homework_daily.utils.Constant;
import com.hufeiya.homework_daily.utils.HtmlParse;
import com.hufeiya.homework_daily.utils.HttpUtil;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    private int yearFlag = 0;
    private int termFlag = 0;
    private String strUserId;
    private String strPassword;
    private int count = 0;
    private ProgressDialog pd;// 进度条对话框
    private String finalTerm;
    private HttpUtil myHttpUtil;
    private String isLogin;
    private HtmlParse queryHtmlParse, viewStateHtmlParse;
    public MyHandler myHandler = new MyHandler(this);
    private String queryViewState;// 查询时要截取的viewstate
    private StudentInfoBean studentBean;
    private List<Course> courseList;
    @BindView(R.id.login_button)Button loginButton;
    @BindView(R.id.username) AutoCompleteTextView usernameView;
    @BindView(R.id.password) AutoCompleteTextView passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        final ImageView girl22 = (ImageView)findViewById(R.id.girl_22);
        final ImageView girl33 = (ImageView)findViewById(R.id.girl_33);
        AutoCompleteTextView usernmeView = (AutoCompleteTextView)findViewById(R.id.username);
        AutoCompleteTextView passwordView = (AutoCompleteTextView)findViewById(R.id.password);
        assert usernmeView != null;
        usernmeView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    girl22.setImageResource(R.drawable.ic_22);
                    girl33.setImageResource(R.drawable.ic_33);
                }
            }
        });
        assert passwordView != null;
        passwordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                girl22.setImageResource(R.drawable.ic_22_hide);
                girl33.setImageResource(R.drawable.ic_33_hide);
            }
        });

        //年份Spinner
        Spinner spinner_year = (Spinner) findViewById(R.id.spinner_year);
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_spinner_item, Constant.year);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_year.setAdapter(adapter_year);

        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int flag, long arg3) {
                yearFlag = flag;
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        spinner_year.setSelection(8,true);

        //学期Spinner
        Spinner spinner_term = (Spinner) findViewById(R.id.spinner_term);
        ArrayAdapter<String> adapter_term = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_spinner_item, Constant.term);
        adapter_term.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_term.setAdapter(adapter_term);

        spinner_term.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int flag, long arg3) {
                termFlag = flag;
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        setView();
    }

    private void setView() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                strUserId = usernameView.getText().toString();
                strPassword = passwordView.getText().toString();
                if (strUserId.equals("") || strPassword.equals("")) {
                    new AlertDialog.Builder(LoginActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                            .setTitle("绩点宝提示")
                            .setMessage("请先输入学号或密码=^_^=")
                            .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                } else {
                    // 进行数据的获取、计算和传递到result中
                    count = 0;
                    // 创建ProgressDialog对象
                    pd = new ProgressDialog(LoginActivity.this);
                    // 设置进度条风格，风格为长形
                    pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    // 设置ProgressDialog标题
                    pd.setTitle("绩点宝提示");
                    // 设置ProgressDialog 提示信息
                    pd.setMessage("正在拼命加载中......");
                    // 设置ProgressDialog 进度条进度
                    pd.setProgress(100);
                    // 设置ProgressDialog 的进度条是否不明确
                    pd.setIndeterminate(false);
                    // 设置ProgressDialog 是否可以按退回按键取消
                    pd.setCancelable(false);
                    // 让ProgressDialog显示
                    pd.show();

                    new Thread() {
                        public void run() {
                            try {
                                Message msg;
                                Bundle b1 = new Bundle();// 存放数据

                                while (count <= 100) {
                                    // 由线程来控制进度
                                    pd.setProgress(count++);

                                    if (count == 33) {
                                        String term_Of_year = termResult(Constant.term[termFlag]);
                                        finalTerm = Constant.year[yearFlag] + term_Of_year;
                                        myHttpUtil = new HttpUtil(strUserId, strPassword, finalTerm);
                                        isLogin = myHttpUtil.login();

                                        // LoginFail表示学号或密码错误
                                        if (isLogin.equals("LoginFail")) {
                                            pd.cancel();
                                            b1.putString("flg", "1");
                                            msg = new Message();
                                            msg.setData(b1);
                                            myHandler.sendMessage(msg);
                                        }
                                        // SystemError表示原创系统出现故障
                                        if (isLogin.equals("SystemError")) {
                                            pd.cancel();
                                            b1.putString("flg", "2");
                                            msg = new Message();
                                            msg.setData(b1);
                                            myHandler.sendMessage(msg);
                                        }
                                    }

                                    else if (count == 66) {
                                        viewStateHtmlParse = new HtmlParse(myHttpUtil.getViewStateHtml());
                                        queryViewState = viewStateHtmlParse.getQueryViewState();
                                    }

                                    else if (count == 100 && isLogin.equals("LoginSuccess")) {
                                        queryHtmlParse = new HtmlParse(myHttpUtil.queryCourses(queryViewState));

                                        //studentBean = queryHtmlParse.getStudentInfo();
                                        courseList = queryHtmlParse.getCourseList();

                                        // 判断查询的学年是否有课程
                                        if (courseList.size() == 0) {
                                            b1.putString("flg", "3");
                                            msg = new Message();
                                            msg.setData(b1);
                                            myHandler.sendMessage(msg);
                                        }

                                    }

                                }

                                pd.cancel();

                                if (courseList != null && courseList.size() != 0) {
                                    for(Course course : courseList){
                                        Log.d("fucc",course.toString());
                                        saveCourseList();
                                        setResult(RESULT_OK);
                                        finish();
                                    }

                                }

                            } catch (Exception e) {
                                pd.cancel();
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
    }

    private void saveCourseList(){
        if(courseList == null || courseList.size() == 0){
            return;
        }
        Type type = new TypeToken<List<Course>>(){}.getType();
        String jsonArray = new Gson().toJson(courseList,type);
        SharedPreferences.Editor editor = getSharedPreferences(Constant.SHARED_PREFERENCES_NAME,MODE_PRIVATE).edit();
        editor.putString(Constant.SHARED_PREFERENCES_NAME,jsonArray).apply();

    }

    public String termResult(String term)
    {
        if(term.equals("第一学期"))
        {
            term="(1)";
        }
        if(term.equals("第二学期"))
        {
            term="(2)";
        }
        if(term.equals("全年"))
        {
            term="";
        }
        return term;
    }

    private static class MyHandler extends Handler {
        WeakReference<LoginActivity> activity;

        MyHandler(LoginActivity activity) {

            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoginActivity activity = this.activity.get();
            if (activity != null){
                activity.doHandler(msg);
            }

        }
    }
    protected void doHandler(Message msg) {
        Bundle b2 = msg.getData();
        String flg = b2.getString("flg");

        if (flg != null) {
            if (flg.equals("1")) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示").setMessage("学号或密码错误，请重新输入！")
                        .setPositiveButton("确定", null).show();
                return;
            }

            if (flg.equals("2")) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示").setMessage("原创系统出故障了，请稍后重试！")
                        .setPositiveButton("确定", null).show();
            }

            if (flg.equals("3")) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示").setMessage("该学期目前没有你的课表！")
                        .setPositiveButton("确定", null).show();
            }
        }
    }
}

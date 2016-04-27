package com.hufeiya.homework_daily;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hufeiya.homework_daily.bean.Course;
import com.hufeiya.homework_daily.bean.MessageEvent;
import com.hufeiya.homework_daily.customview.SyncScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout frameLinearLayout;
    public List<Course> courseList;
    private boolean [][]haveCourse = new boolean[12][7];
    private Button [][] buttons = new Button[12][7];
    private static final int ROW_NUM = R.id.frame_linear_layout;
    private static final int COLUM_NUM = R.id.scrollView_main;
    private List<LinearLayout> linearLayoutList = new ArrayList<>();
    private static final int PITCH_NUMBER = 12; //number of course in 1 day.
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        frameLinearLayout = (LinearLayout)findViewById(R.id.frame_linear_layout);
        init();
    }

    private void init(){
        for(int i = 0;i < 7;i++){
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(160, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.VERTICAL);
            for(int j = 0;j < PITCH_NUMBER;j++){
                Button button = (Button)getLayoutInflater().inflate(R.layout.bt_templete,null);
                //Button button = new Button(new ContextThemeWrapper(MainActivity.this, R.style.empty_button));

                //button.setText("test");
                button.setTag(ROW_NUM,j);
                button.setTag(COLUM_NUM,i);
                button.setHeight(160);
                button.setOnClickListener(this);
                button.setText(String.valueOf(j));
                layout.addView(button);
                buttons[j][i] = button;

            }
            linearLayoutList.add(layout);
            frameLinearLayout.addView(layout);
        }
        initTheSyncScrollView();
    }

    private void initTheSyncScrollView(){
        SyncScrollView topView = (SyncScrollView) findViewById(R.id.scrollView_top);
        SyncScrollView mainView = (SyncScrollView) findViewById(R.id.scrollView_main);
        topView.setScrollView(mainView);
        mainView.setScrollView(topView);
    }

    private void initExistedCourses(){
        SharedPreferences preferences = getSharedPreferences("courses",MODE_PRIVATE);
        String jsonarray = preferences.getString("courses",null);
        if(jsonarray != null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Course>>(){}.getType();
            courseList = gson.fromJson(jsonarray,type);
        }

    }

    private static int dip2px(Context context, float dipValue) {
        dipValue /= 3; // Why? I really don't konw. FUCK!
        return (int) (dipValue * context.getResources().getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View v) {
        Log.d("fuck","" + v.getTag(ROW_NUM) + "  " + v.getTag(COLUM_NUM));
        int row = (Integer) v.getTag(ROW_NUM);
        int colum = (Integer) v.getTag(COLUM_NUM);
        if(haveCourse[row][colum]){

        }else{
            Course course = new Course(courseList == null ? 0 :courseList.size());
            course.setWeek(colum);
            ArrayList<Integer> pitchNumber = new ArrayList<>(2);
            pitchNumber.add(0,row);
            pitchNumber.add(1,row+1);
            course.setPitchNumbers(pitchNumber);
            CourseInfoDialogFragment fragment = new CourseInfoDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("course",new Gson().toJson(course));
            fragment.setArguments(bundle);
            fragment.show(getFragmentManager(),"course_fragment");
        }

    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        switch (event.message){
            case MessageEvent.UPDATE_COURSE_LIST:
                Log.d("fuck","update course msg received!!!");
                insertCourseToCourseList(event.jsonCourse);
                showCourses();
                break;
        }
    }

    private void insertCourseToCourseList(String jsonCourse){
        if(courseList == null){
            courseList = new ArrayList<>();
        }
        Course course = new Gson().fromJson(jsonCourse,Course.class);
        int row = course.getPitchNumbers().get(0);
        int colum = course.getWeek();
        int courseLen = course.getPitchNumbers().get(1) - row;
        boolean courseInterupted = haveCourse[row][colum];
        for(int i = row + 1;i < row+courseLen;i++){
            if( i >= PITCH_NUMBER || haveCourse[i][colum]){
                courseInterupted = true;
                break;
            }
        }
        if(courseInterupted){
            Toast.makeText(this,"课程冲突啦,请重新添加",Toast.LENGTH_LONG).show();
            return;
        }
        courseList.add(course);
    }

    private void showCourses(){
        if(courseList == null){
            return;
        }
        for(Course course : courseList){
            int row = course.getPitchNumbers().get(0);
            int colum = course.getWeek();
            if( ! haveCourse[row][colum]){
                Button button = buttons[row][colum];
                int courseLen = course.getPitchNumbers().get(1) - row;
                button.setHeight(button.getHeight()*courseLen);
                button.setText(course.getName() + "@" + course.getRoom());
                GradientDrawable gd = new GradientDrawable();
                gd.setColor(getResources().getColor(R.color.green)); // Changes this drawbale to use a single color instead of a gradient
                gd.setCornerRadius(20);
                gd.setStroke(1, 0xFF000000);
                button.setBackgroundDrawable(gd);
                //button.setBackgroundColor(getResources().getColor(R.color.green));
                button.setTextColor(getResources().getColor(R.color.white));
                button.setAlpha(0.7f);

                LinearLayout parent = linearLayoutList.get(colum);
                for(int i = row + 1;i < row+courseLen;i++){
                    buttons[i][colum].setVisibility(View.GONE);
                }

                haveCourse[row][colum] = true;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

package com.hufeiya.homework_daily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
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
import com.hufeiya.homework_daily.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout frameLinearLayout;
    public List<Course> courseList;
    private boolean [][]haveCourse ;
    private Button [][] buttons ;

    private List<LinearLayout> linearLayoutList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        frameLinearLayout = (LinearLayout)findViewById(R.id.frame_linear_layout);
        if( ! isLogin()) {
            startLoginActivity();
        }
        else{
            init();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void startLoginActivity(){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent,0);
    }

    private void init(){
        linearLayoutList.clear();
        frameLinearLayout.removeAllViews();
        if (courseList != null) courseList.clear();
        haveCourse = new boolean[12][7];
        buttons = new Button[12][7];

        for(int i = 0;i < 7;i++){
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(new LinearLayout.LayoutParams(160, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.VERTICAL);
            for(int j = 0;j < Constant.PITCH_NUMBER;j++){
                Button button = (Button)getLayoutInflater().inflate(R.layout.bt_templete,null);
                button.setTag(Constant.ROW_NUM,j);
                button.setTag(Constant.COLUM_NUM,i);
                button.setHeight(160);
                button.setOnClickListener(this);
                //button.setText(String.valueOf(j));//Just for debug
                layout.addView(button);
                buttons[j][i] = button;

            }
            linearLayoutList.add(layout);
            frameLinearLayout.addView(layout);
        }
        initTheSyncScrollView();
        initExistedCourses();
    }

    private void initTheSyncScrollView(){
        SyncScrollView topView = (SyncScrollView) findViewById(R.id.scrollView_top);
        SyncScrollView mainView = (SyncScrollView) findViewById(R.id.scrollView_main);
        topView.setScrollView(mainView);
        mainView.setScrollView(topView);
    }

    private void initExistedCourses(){
        SharedPreferences preferences = getSharedPreferences(Constant.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        String jsonArray = preferences.getString(Constant.SHARED_PREFERENCES_NAME,null);
        if(jsonArray != null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Course>>(){}.getType();
            courseList = gson.fromJson(jsonArray,type);
            Log.d("fuck",courseList.toString());
            showCourses();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("fuck","result COde:" +resultCode);
        switch (resultCode){
            case RESULT_OK:
                Log.d("fuck","Activity back!");
                setLogined();
                init();
                break;
        }
    }

    private boolean isLogin(){
        SharedPreferences preferences = getSharedPreferences(Constant.SHARED_PREFERENCES_IS_LOGIN,MODE_PRIVATE);
        return preferences.getBoolean(Constant.SHARED_PREFERENCES_IS_LOGIN,false);
    }

    private void setLogined(){
        SharedPreferences.Editor editor = getSharedPreferences(Constant.SHARED_PREFERENCES_IS_LOGIN,MODE_PRIVATE).edit();
        editor.putBoolean(Constant.SHARED_PREFERENCES_IS_LOGIN, true).apply();

    }

    private void setLogout(){
        SharedPreferences.Editor editor = getSharedPreferences(Constant.SHARED_PREFERENCES_IS_LOGIN,MODE_PRIVATE).edit();
        editor.putBoolean(Constant.SHARED_PREFERENCES_IS_LOGIN, false).apply();
    }

    private static int dip2px(Context context, float dipValue) {
        dipValue /= 3; // Why? I really don't konw. FUCK!
        return (int) (dipValue * context.getResources().getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View v) {
        Log.d("fuck","" + v.getTag(Constant.ROW_NUM) + "  " + v.getTag(Constant.COLUM_NUM));
        int row = (Integer) v.getTag(Constant.ROW_NUM);
        int colum = (Integer) v.getTag(Constant.COLUM_NUM);
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
                saveCourseList();
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
            if( i >= Constant.PITCH_NUMBER || haveCourse[i][colum]){
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
                button.setHeight(160*courseLen);
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

    private void saveCourseList(){
        if(courseList == null || courseList.size() == 0){
            return;
        }
        Type type = new TypeToken<List<Course>>(){}.getType();
        String jsonArray = new Gson().toJson(courseList,type);
        SharedPreferences.Editor editor = getSharedPreferences(Constant.SHARED_PREFERENCES_NAME,MODE_PRIVATE).edit();
        editor.putString(Constant.SHARED_PREFERENCES_NAME,jsonArray).apply();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if (id == R.id.quit){
            setLogout();
            startLoginActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

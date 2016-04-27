package com.hufeiya.homework_daily;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hufeiya.homework_daily.bean.Course;
import com.hufeiya.homework_daily.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

/**
 * Created by hufeiya on 16/4/26.
 */
public class CourseInfoDialogFragment extends BlurDialogFragment {

    private Course course;
    @BindView(R.id.course_name) AutoCompleteTextView nameView;
    @BindView(R.id.course_room) AutoCompleteTextView roomView;
    @BindView(R.id.course_teacher) AutoCompleteTextView teacherView;
    @BindView(R.id.course_pitch) AutoCompleteTextView pitchView;
    @BindView(R.id.course_pitch1) AutoCompleteTextView pitchView1;
    @BindView(R.id.course_pitch2) AutoCompleteTextView pitchView2;
    @BindView(R.id.course_week) AutoCompleteTextView weekView;
    @BindView(R.id.course_fab) FloatingActionButton courseFab;
    Unbinder unbinder;

    public CourseInfoDialogFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_info_layout,container);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(1000,1000);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String json = bundle.getString("course","null");
        if( ! json.equals("null")){
            this.course = new Gson().fromJson(json,Course.class);
        }
        Log.d("fuck","week" + course.getWeek());
        Log.d("fuck","rrr" + course.getPitchNumbers().get(1));

        if(course != null){
            if(course.getName() != null) nameView.setText(course.getName());
            if(course.getRoom() != null) roomView.setText(course.getRoom());
            if(course.getTeacher() != null) teacherView.setText(course.getTeacher());
            pitchView.setText(Course.weekName[course.getWeek()].toString());
            pitchView1.setText(""+(course.getPitchNumbers().get(0)+1));
            pitchView2.setText("" + String.valueOf(course.getPitchNumbers().get(1)+1));
        }

        //ObjectAnimator.ofFloat(courseFab,"translationY",50).start();
        //ObjectAnimator.ofFloat(courseFab,"translationX",50).start();
        courseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fuck","CourseFab clicked!!");
                if( ! generateCourseFromInput()){
                    Toast.makeText(getActivity(),"节数填写错误哟",Toast.LENGTH_LONG).show();
                    return;
                }
                EventBus.getDefault().post(new MessageEvent(MessageEvent.UPDATE_COURSE_LIST,new Gson().toJson(course)));
                dismiss();
            }
        });
    }
    private boolean generateCourseFromInput(){
        course.setName(nameView.getText().toString());
        course.setRoom(roomView.getText().toString());
        course.setTeacher(teacherView.getText().toString());
        // don't need to save pitchview and pitchview1
        Integer pitchNum2 = 0;
        try{
            pitchNum2 = Integer.parseInt(pitchView2.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        if (pitchNum2 < course.getPitchNumbers().get(0)){
            return false;
        }
        course.getPitchNumbers().set(1,pitchNum2);
        //TODO: other setting
        return true;
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event){
        if(event.message == MessageEvent.HIDE_COURSE_FAB && courseFab.getVisibility() == View.VISIBLE){

            courseFab.setVisibility(View.INVISIBLE);
        }else if(event.message == MessageEvent.SHOW_COURSE_FAB && courseFab.getVisibility() == View.INVISIBLE){
            courseFab.setVisibility(View.VISIBLE);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(courseFab,"rotation",0,180),
                    ObjectAnimator.ofFloat(courseFab,"translationY",200,0)
            );
            animatorSet.setDuration(200).start();
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}

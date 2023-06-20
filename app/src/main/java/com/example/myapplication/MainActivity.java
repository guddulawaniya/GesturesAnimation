package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    SwipeGesturesanimation SwipeGesturesanimation;

    ConstraintLayout container;
    TextView skiptext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        skiptext = findViewById(R.id.skip);


        SwipeGesturesanimation = new SwipeGesturesanimation(container);
    }

    private class SwipeGesturesanimation implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeGesturesanimation(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(@NonNull MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    float xdiff = e2.getX() - e1.getX();
                    float ydiff = e2.getY() - e1.getY();

                    try {
                        if (Math.abs(xdiff) > Math.abs(ydiff)) {
                            if (Math.abs(xdiff) > threshold && Math.abs(velocityX) > velocity_threshold) {

                                if (xdiff > 0) {
                                    skiptext.setText("Swipe Right ");
                                    Toast.makeText(view.getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
                                } else {
                                    skiptext.setText("Swipe Left ");
                                    Toast.makeText(view.getContext(), "Swipe Left", Toast.LENGTH_SHORT).show();

                                }
                                return true;
                            }
                        }

                        else {
                            if (Math.abs(ydiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                if (ydiff > 0) {
                                    skiptext.setText("Swipe Down ");
                                    Toast.makeText(view.getContext(), "Swipe Down", Toast.LENGTH_SHORT).show();
                                } else {
                                    skiptext.setText("Swipe Top ");
                                    Toast.makeText(view.getContext(), "Swipe Top", Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };

            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}
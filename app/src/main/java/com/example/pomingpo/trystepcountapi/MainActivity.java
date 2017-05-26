package com.example.pomingpo.trystepcountapi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {
    private TextView tvCount;
    private Button btStart;
    private Button btStop;
    private SensorManager mSensorManager;
    private Sensor mStepCount;
    private float startCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvCount = (TextView) findViewById(R.id.tv_count);
        btStart = (Button) findViewById(R.id.bt_start);
        btStop = (Button) findViewById(R.id.bt_stop);
        btStop.setOnClickListener(this);
        btStart.setOnClickListener(this);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mStepCount = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            //event.values[0]为计步历史累加值

            if (startCount == 0)
                startCount = event.values[0];

            tvCount.setText(event.values[0] - startCount + "步");

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                mSensorManager.registerListener(this, mStepCount, SensorManager.SENSOR_DELAY_FASTEST);
                break;
            case R.id.bt_stop:
                mSensorManager.unregisterListener(this, mStepCount);
                startCount = 0;
                break;
        }

    }
}

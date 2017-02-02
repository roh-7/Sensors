package com.example.sensors;

/**
 * Created by rohitramaswamy on 24/01/17.
 */

public class Comments {


    /*

private SensorManager mSensorManager;
private Sensor mSensor;
private SensorEvent mSensorEvent;
public static final float EPSILON = 0.000000001f;


private static final float NS2S = 1.0f / 1000000000.0f;
private float timestamp;
private final float[] deltaRotationVector = new float[4];

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    TextView textView = (TextView) findViewById(R.id.helw);
    String str = String.valueOf(mSensor);
    textView.setText(str);
    Log.d("bro1",str);
    //onSensorChanged(mSensorEvent);
}
@Override
public void onSensorChanged(SensorEvent event) {
    // This timestep's delta rotation to be multiplied by the current rotation
    // after computing it from the gyro sample data.
    if (timestamp != 0) {
        final float dT = (event.timestamp - timestamp) * NS2S;
        // Axis of the rotation sample, not normalized yet.
        float axisX = event.values[0];
        float axisY = event.values[1];
        float axisZ = event.values[2];

        // Calculate the angular speed of the sample
        float omegaMagnitude = (float) Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

        // Normalize the rotation vector if it's big enough to get the axis
        // (that is, EPSILON should represent your maximum allowable margin of error)
        if (omegaMagnitude > EPSILON) {
            axisX /= omegaMagnitude;
            axisY /= omegaMagnitude;
            axisZ /= omegaMagnitude;
        }

        // Integrate around this axis with the angular speed by the timestep
        // in order to get a delta rotation from this sample over the timestep
        // We will convert this axis-angle representation of the delta rotation
        // into a quaternion before turning it into the rotation matrix.
        float thetaOverTwo = omegaMagnitude * dT / 2.0f;
        float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
        float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
        deltaRotationVector[0] = sinThetaOverTwo * axisX;
        deltaRotationVector[1] = sinThetaOverTwo * axisY;
        deltaRotationVector[2] = sinThetaOverTwo * axisZ;
        deltaRotationVector[3] = cosThetaOverTwo;
    }
    timestamp = event.timestamp;
    float[] deltaRotationMatrix = new float[9];
    SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
    //for(int i=0;i<4;i++)

    Log.d("bro",String.valueOf(deltaRotationVector[0]));
    Log.d("bro",String.valueOf(deltaRotationVector[1]));
    Log.d("bro",String.valueOf(deltaRotationVector[2]));
    Log.d("bro",String.valueOf(deltaRotationVector[3]));

    // User code should concatenate the delta rotation we computed with the current rotation
    // in order to get the updated rotation.
    // rotationCurrent = rotationCurrent * deltaRotationMatrix;
}

@Override
public void onAccuracyChanged(Sensor sensor, int i) {

}
}

*/
/*

// Create a constant to convert nanoseconds to seconds.
private static final float NS2S = 1.0f / 1000000000.0f;
private final float[] deltaRotationVector = new float[4]();
private float timestamp;

public void onSensorChanged(SensorEvent event) {
// This timestep's delta rotation to be multiplied by the current rotation
// after computing it from the gyro sample data.
if (timestamp != 0) {
final float dT = (event.timestamp - timestamp) * NS2S;
// Axis of the rotation sample, not normalized yet.
float axisX = event.values[0];
float axisY = event.values[1];
float axisZ = event.values[2];

// Calculate the angular speed of the sample
float omegaMagnitude = sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

// Normalize the rotation vector if it's big enough to get the axis
// (that is, EPSILON should represent your maximum allowable margin of error)
if (omegaMagnitude > EPSILON) {
  axisX /= omegaMagnitude;
  axisY /= omegaMagnitude;
  axisZ /= omegaMagnitude;
}

// Integrate around this axis with the angular speed by the timestep
// in order to get a delta rotation from this sample over the timestep
// We will convert this axis-angle representation of the delta rotation
// into a quaternion before turning it into the rotation matrix.
float thetaOverTwo = omegaMagnitude * dT / 2.0f;
float sinThetaOverTwo = sin(thetaOverTwo);
float cosThetaOverTwo = cos(thetaOverTwo);
deltaRotationVector[0] = sinThetaOverTwo * axisX;
deltaRotationVector[1] = sinThetaOverTwo * axisY;
deltaRotationVector[2] = sinThetaOverTwo * axisZ;
deltaRotationVector[3] = cosThetaOverTwo;
}
timestamp = event.timestamp;
float[] deltaRotationMatrix = new float[9];
SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
// User code should concatenate the delta rotation we computed with the current rotation
// in order to get the updated rotation.
// rotationCurrent = rotationCurrent * deltaRotationMatrix;
}
}





 <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginBottom="10dp"
                android:text="Max Acceleration : "
                android:textSize="20sp" />


            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginTop="20dp"
                android:text="Max Acceleration : X axis "
                android:textSize="15sp" />

            <TextView
                android:id="@+id/maxX"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginBottom="10dp"
                android:text="0.0"
                android:textSize="20sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginTop="20dp"
                android:text="Max Acceleration : Y axis "
                android:textSize="15sp" />

            <TextView
                android:id="@+id/maxY"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginTop="20dp"
                android:text="0.0"
                android:textSize="20sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginBottom="20dp"
                android:text="Max Acceleration : Z axis "
                android:textSize="15sp" />

            <TextView
                android:id="@+id/maxZ"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginTop="20dp"
                android:text="0.0"
                android:textSize="20sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginTop="10dp"
                android:text="Current Acceleration"
                android:textSize="10sp" />


            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#ffffff"
                android:gravity="center|top"
                android:orientation="horizontal">

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="0.33"
                    android:background="#ffffff"
                    android:gravity="center"
                    android:orientation="vertical">

                    <TextView
                        android:layout_width="wrap_content"

                        android:layout_height="wrap_content"

                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="X-Axis"
                        android:textSize="15dp" />

                    <TextView
                        android:id="@+id/currentX"
                        android:layout_width="wrap_content"

                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="0.0"
                        android:textSize="15dp" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="0.33"
                    android:background="#ffffff"
                    android:gravity="center"
                    android:orientation="vertical">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="Y-Axis"
                        android:textSize="15dp" />

                    <TextView
                        android:id="@+id/currentY"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="0.0"
                        android:textSize="15dp" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="0.33"
                    android:background="#ffffff"
                    android:gravity="center"
                    android:orientation="vertical">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="Z-Axis"
                        android:textSize="15dp" />

                    <TextView
                        android:id="@+id/currentZ"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="0.0"
                        android:textSize="15dp" />

                </LinearLayout>

            </LinearLayout>

            <LinearLayout
                android:layout_width="387dp"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="0.33"
                    android:orientation="vertical">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="Gyro X"
                        android:textSize="15sp" />

                    <TextView
                        android:id="@+id/GyroX"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:text="0.0"
                        android:textSize="15sp" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="0.33"
                    android:orientation="vertical">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="Gyro Y"
                        android:textSize="15sp" />

                    <TextView
                        android:id="@+id/GyroY"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:text="0.0"
                        android:textSize="15sp" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="0.33"
                    android:orientation="vertical">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:layout_marginTop="10dp"
                        android:text="Gyro Z"
                        android:textSize="15sp" />

                    <TextView
                        android:id="@+id/GyroZ"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:text="0.0"
                        android:textSize="15sp" />
                </LinearLayout>
            </LinearLayout>

            <Button
                android:id="@+id/btn"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:text="Click me to start!"
                android:textSize="20sp" />


*/


}

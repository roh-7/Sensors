package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    PostToDB postToDB;
    //public int c = 0;

    public VideoView video;
    public EditText address;
    public int port;
    public String ip;

    private float lastX, lastY, lastZ;

    public boolean connected = false;

    private SensorManager sensorManager;
    private SensorManager getSensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    public EditText porte;
    public EditText ipade;

    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;


    public float deltaX = 0;
    public float deltaY = 0;
    public float deltaZ = 0;

    Thread newThread = new Thread(new ClientThre());


    public float delGX = 0;
    public float delGY = 0;
    public float delGZ = 0;

    public Button btn;

    private float vibrateThreshold = 0;

    private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;

    private TextView gyroX, gyroY, gyroZ;

    public Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MediaController mediaController = new MediaController(this);
        setContentView(R.layout.activity_main);
        initialiseViews();
        video = (VideoView) findViewById(R.id.Video);
        ipade = (EditText) findViewById(R.id.ipad);
        porte = (EditText) findViewById(R.id.port);
        Log.v("tag", "tag");
        btn = (Button) findViewById(R.id.Start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    address = (EditText) findViewById(R.id.StreamAddress);
                    String adds = address.getText().toString();
                    String address = "http://" + adds;
                    Log.v("tag", address);
                    Uri uri = Uri.parse(address);
                    Log.v("tag", "tag12");

                    video.setMediaController(mediaController);
                    video.setVideoURI(uri);
                    Log.v("tag", "tag433");
                    trustEveryone();
                    video.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
            // fail! we do not have a sensor
        }
        // initialise vibration
        getSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (getSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            gyroscope = getSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            getSensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
//        btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(connectListener);


        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public class ClientThre implements Runnable {
        PrintWriter out;
        Socket socket;

        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(ip);
                Log.v("boo", "hii");
                socket = new Socket(serverAddr, port);
                Log.v("boo", "kjkh");
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Log.v("boo", "gfds");
                while (true) {
                    out.printf("A:%10.2f\t %10.2f\t %10.2f\n", deltaX, deltaY, deltaZ);
                    out.printf("G:%10.2f\t %10.2f\t %10.2f\n", delGX, delGY, delGZ);
                    Log.v("boo", "ijo");
                    out.flush();
                    Thread.sleep(2);
                    if (!connected) {
                        break;
                    }
                }
            } catch (Exception e) {
                Log.v("boo", "uidfn");
                e.printStackTrace();
            } finally {
                try {
                    Log.v("socket", "closed");
                    socket.close();
                } catch (Exception e) {
                    Log.v("boo", "fiajcak");
                    e.printStackTrace();
                }
            }

        }
    }

    ;
    private Button.OnClickListener connectListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!connected) {
                ip = ipade.getText().toString();
                port = Integer.parseInt(porte.getText().toString());
                if (!ip.equals("")) {
                    //connectPhones.setText("Stop Streaming");
                    Thread cThread = new Thread(new ClientThre());
                    cThread.start();
                    connected = true;
                }
            } else {
                //connectPhones.setText("Start Streaming");
                connected = false;
                Log.v("button", "closed " + connected);
                // acc_disp=false;
            }
        }
    };

    public void initialiseViews() {
//        currentX = (TextView) findViewById(R.id.currentX);
//        currentY = (TextView) findViewById(R.id.currentY);
//        currentZ = (TextView) findViewById(R.id.currentZ);
//
//        maxX = (TextView) findViewById(R.id.maxX);
//        maxY = (TextView) findViewById(R.id.maxY);
//        maxZ = (TextView) findViewById(R.id.maxZ);
//
//        gyroX = (TextView) findViewById(R.id.GyroX);
//        gyroY = (TextView) findViewById(R.id.GyroY);
//        gyroZ = (TextView) findViewById(R.id.GyroZ);
    }

    //register the sensor onResume
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        getSensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // unregister in onPause

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        getSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //clean current values
        //displayCleanValues();
        // display the current values
        //displayCurrentValues();
        // display the max values
        //displayMaxValues();
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // get the change in x,y,z values of the accelerometer
            deltaX = Math.abs(lastX - sensorEvent.values[0]);
            deltaY = Math.abs(lastY - sensorEvent.values[1]);
            deltaZ = Math.abs(lastZ - sensorEvent.values[2]);
//            Thread newThread = new Thread(new ClientThread(deltaX,deltaY,deltaZ));
//            newThread.start();
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            delGX = Math.abs(sensorEvent.values[0]);
            delGY = Math.abs(sensorEvent.values[1]);
            delGZ = Math.abs(sensorEvent.values[2]);
        }
        // if the change is below 2, it is just plain noise

//        if (deltaX < 2)
//            deltaX = 0;
//        if (deltaY < 2)
//            deltaY = 0;
        //if ((deltaX > vibrateThreshold) || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold))
        //v.vibrate(600);
    }

//    public void displayCleanValues() {
//        currentX.setText("0.0");
//        currentY.setText("0.0");
//        currentZ.setText("0.0");
//
//
//        gyroX.setText("0.0");
//        gyroY.setText("0.0");
//        gyroZ.setText("0.0");
//    }

//    public void displayCurrentValues() {
//        //c++;
//        String curx = Float.toString(deltaX);
//        String cury = Float.toString(deltaY);
//        String curz = Float.toString(deltaZ);
//        currentX.setText(curx);
//        currentY.setText(cury);
//        currentZ.setText(curz);
//
//        String curgx = Float.toString(delGX);
//        String curgy = Float.toString(delGY);
//        String curgz = Float.toString(delGZ);
//
//        gyroX.setText(curgx);
//        gyroY.setText(curgy);
//        gyroZ.setText(curgz);

    //String time = new SimpleDateFormat("DD/MM/YYYY.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
    //Log.v("time", time);
    //postToDB = new PostToDB(curx, cury, curz, time);
    //if(c%678==0)
    //postToDB.post();

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

   /* private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

}

//    public void displayMaxValues() {
//        if (deltaX > deltaXMax) {
//            deltaXMax = deltaX;
//            maxX.setText(Float.toString(deltaXMax));
//        }
//        if (deltaY > deltaYMax) {
//            deltaYMax = deltaY;
//            maxY.setText(Float.toString(deltaYMax));
//        }
//        if (deltaZ > deltaZMax) {
//            deltaZMax = deltaZ;
//            maxZ.setText(Float.toString(deltaZMax));
//        }
//
//    }








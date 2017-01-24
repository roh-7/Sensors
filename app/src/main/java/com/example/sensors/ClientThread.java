package com.example.sensors;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread implements Runnable {
    Float x, y, z;

    public ClientThread(Float x, Float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public int port = 9001;
    public String ip = "192.168.43.140";

    PrintWriter out;

    Socket socket;

    @Override
    public void run() {
        Log.v("boo","jhihij");
        try {
            InetAddress serverAddr = InetAddress.getByName(ip);
            Log.v("boo","hii");
            socket = new Socket(serverAddr, port);
            Log.v("boo","kjkh");
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            Log.v("boo","gfds");
            while (true) {
                out.printf("%10.2f\t %10.2f\t %10.2f\n", x, y, z);
                Log.v("boo", "ijo");
                out.flush();
                Thread.sleep(2);
            }
        } catch (Exception e) {
            Log.v("boo","uidfn");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                Log.v("boo","fiajcak");
                e.printStackTrace();
            }
        }
    }
}
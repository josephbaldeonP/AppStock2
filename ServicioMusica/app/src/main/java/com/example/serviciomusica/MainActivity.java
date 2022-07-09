package com.example.serviciomusica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button arrancar = (Button) findViewById(R.id.boton_arrancar);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startService(new Intent(MainActivity.this,
                        ServicioMusica.class));
                //createNotificationChannel();
                createNotification();

            }
        });
        Button detener = (Button) findViewById(R.id.boton_detener);
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,
                        ServicioMusica.class));
                closeNotification();
            }
        });

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    private void createNotification(){
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Servicio de música");
        builder.setContentText("Escucha su música siempre");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

    private void closeNotification(){
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.cancel(NOTIFICACION_ID);
    }


}
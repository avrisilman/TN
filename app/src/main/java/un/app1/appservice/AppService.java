package un.app1.appService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AppService extends Service {

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        sendBroadcast(new Intent(this, AppService.class));
        Toast.makeText(this, "YouWillNeverKillMe", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

}
package ng.codeinn.oaunet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String test() {
        Log.i(" ", "onCreate: textin");
            Long unixTimeStamp = 1524218291L;
            Log.d("LONG PASSED", String.valueOf(Long.parseLong(String.valueOf(unixTimeStamp))));
            Date date = new Date(Long.parseLong(String.valueOf(unixTimeStamp)));

            Log.d("DATE ========== ", DateUtils.formatDateTime(this, Long.parseLong(String.valueOf(unixTimeStamp)), DateUtils.FORMAT_SHOW_TIME));

            SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
//            return dateFormatter.format(date);
//        }

    }
}

package xgn.com.my_basesdk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import xgn.com.my_basesdk.MyApplication;
import xgn.com.my_basesdk.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, MyApplication.getMyAppInstance().mInstance.getXgRest().toString(),Toast.LENGTH_SHORT).show();
    }
}

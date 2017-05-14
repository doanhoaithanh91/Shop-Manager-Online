package com.shopmanageronline.shopmanageronline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.shopmanageronline.shopmanageronline.entity.Provider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private View btnProvider;
    private View btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInfo = (View) findViewById(R.id.btn_main_info);
        btnInfo.setOnClickListener(btnInfoOnClickListener);

        btnProvider = (View) findViewById(R.id.btn_nhacungcap);
        btnProvider.setOnClickListener(btnProviderOnClickListener);
    }

    View.OnClickListener btnInfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Xin liên hệ 0868 969 071. Thank you ^^", Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener btnProviderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ProviderActivity.class);
            startActivity(intent);
        }
    };
}

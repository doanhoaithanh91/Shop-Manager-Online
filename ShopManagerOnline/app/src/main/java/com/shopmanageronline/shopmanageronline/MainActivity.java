package com.shopmanageronline.shopmanageronline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.shopmanageronline.shopmanageronline.entity.Provider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Provider> providers;

    Button btnProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProvider = (Button) findViewById(R.id.btn_nhacungcap);
        btnProvider.setOnClickListener(btnProviderOnClickListener);
    }

    View.OnClickListener btnProviderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ProviderActivity.class);
            startActivity(intent);
        }
    };
}

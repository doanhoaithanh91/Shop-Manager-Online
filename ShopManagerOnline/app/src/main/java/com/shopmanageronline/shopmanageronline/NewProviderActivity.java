package com.shopmanageronline.shopmanageronline;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shopmanageronline.shopmanageronline.entity.Provider;
import com.shopmanageronline.shopmanageronline.helper.DBHelper;
import com.shopmanageronline.shopmanageronline.helper.DBManager;
import com.shopmanageronline.shopmanageronline.helper.SyncData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NewProviderActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextAddress;
    private EditText editTextNote;
    private View btnAddProvider;
    private View btnUpdateProvider;
    private View btnBack;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_provider);

        editTextName = (EditText) findViewById(R.id.ed_tennhaccungcap);
        editTextPhone = (EditText) findViewById(R.id.ed_dtnhacungcap);
        editTextEmail = (EditText) findViewById(R.id.ed_emailnhaccungcap);
        editTextAddress = (EditText) findViewById(R.id.ed_diachinhacungcap);
        editTextNote = (EditText) findViewById(R.id.ed_notenhacungcap);

        btnBack = (View) findViewById(R.id.btn_newprovide_back);
        btnBack.setOnClickListener(backOnClickListener);

        btnAddProvider = (View) findViewById(R.id.btn_themnhacungcap);
        btnAddProvider.setOnClickListener(addButtonOnClickListener);

        btnUpdateProvider = (View) findViewById(R.id.btn_capnhatnhacungcap);
        btnUpdateProvider.setOnClickListener(updateButtonOnClickListener);
    }

    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Provider provider = new Provider();
            provider.setName(editTextName.getText().toString());
            provider.setPhone(editTextPhone.getText().toString());
            provider.setEmail(editTextEmail.getText().toString());
            provider.setAddress(editTextAddress.getText().toString());
            provider.setNote(editTextNote.getText().toString());

            dbManager.create(provider);

            finish();
        }
    };

    View.OnClickListener updateButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Provider provider = new Provider();
            provider.setId(getIntent().getExtras().getLong("id"));
            provider.setName(editTextName.getText().toString());
            provider.setPhone(editTextPhone.getText().toString());
            provider.setEmail(editTextEmail.getText().toString());
            provider.setAddress(editTextAddress.getText().toString());
            provider.setNote(editTextNote.getText().toString());

            dbManager.update(provider);

            finish();
        }
    };

    private void setVisibilityButton() {
        if (getIntent().hasExtra("id")) {
            btnAddProvider.setVisibility(View.GONE);
            btnUpdateProvider.setVisibility(View.VISIBLE);

            for (Provider  provider : dbManager.getAllProviders()) {

                editTextName.setText(provider.getName());
                editTextPhone.setText(provider.getPhone());
                editTextEmail.setText(provider.getEmail());
                editTextAddress.setText(provider.getAddress());
                editTextNote.setText(provider.getNote());
            }
        } else {
            btnUpdateProvider.setVisibility(View.GONE);
            btnAddProvider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        dbManager = new DBManager(this);
        dbManager.open();

        setVisibilityButton();

        super.onStart();
    }

    @Override
    protected void onStop() {
        dbManager.close();
        super.onStop();
    }
}

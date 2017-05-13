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
    private Button btnUpdateProvider;

    private Provider providerUpdate;
    private NewProviderActivity newProviderActivity;
    private DBManager dbManager;
    List<Provider> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_provider);
        setTitle(R.string.label_add_nhacungcap);

        editTextName = (EditText) findViewById(R.id.ed_tennhaccungcap);
        editTextPhone = (EditText) findViewById(R.id.ed_dtnhacungcap);
        editTextEmail = (EditText) findViewById(R.id.ed_emailnhaccungcap);
        editTextAddress = (EditText) findViewById(R.id.ed_diachinhacungcap);
        editTextNote = (EditText) findViewById(R.id.ed_notenhacungcap);

        extraDataHandle(providers);

        btnUpdateProvider = (Button) findViewById(R.id.btn_capnhatnhacungcap);
        btnUpdateProvider.setOnClickListener(updateButtonOnClickListener);
    }

    private void extraDataHandle(List<Provider> providers) {
        dbManager = new DBManager(this);
        dbManager.open();
        providers = dbManager.getAllProviders();

        if (getIntent().hasExtra("id")) {
            for (Provider provider : providers) {
                if (provider.getId() == getIntent().getExtras().getLong("id")) {
                    providerUpdate = provider;
                    break;
                }
            }

            if (providerUpdate != null) {
                editTextName.setText(providerUpdate.getName());
                editTextPhone.setText(providerUpdate.getPhone());
                editTextEmail.setText(providerUpdate.getEmail());
                editTextAddress.setText(providerUpdate.getAddress());
                editTextNote.setText(providerUpdate.getNote());
            }
        }
    }

    View.OnClickListener updateButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Provider provider = new Provider();
            provider.setName(editTextName.getText().toString());
            provider.setPhone(editTextPhone.getText().toString());
            provider.setEmail(editTextEmail.getText().toString());
            provider.setAddress(editTextAddress.getText().toString());
            provider.setNote(editTextNote.getText().toString());

            if (providerUpdate != null) {
                dbManager.deleteProvider(providerUpdate.getId());
            }

            dbManager.create(provider);

            ProviderActivity.setListViewAdapter();
            finish();
        }
    };
}

package com.shopmanageronline.shopmanageronline;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.shopmanageronline.shopmanageronline.adapter.ProviderAdapter;
import com.shopmanageronline.shopmanageronline.entity.Provider;
import com.shopmanageronline.shopmanageronline.helper.DBManager;

import java.util.ArrayList;
import java.util.List;

public class ProviderActivity extends AppCompatActivity {
    private View viewAdd;
    private View btnBack;
    private ListView listView;

    private DBManager dbManager;
    private List<Provider> providers;
    private ProviderActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        btnBack = (View) findViewById(R.id.btn_provide_back);
        btnBack.setOnClickListener(backOnClickListener);

        viewAdd = (View) findViewById(R.id.btn_provide_add);
        viewAdd.setOnClickListener(addButtonOnClickListener);

        listView = (ListView) findViewById(R.id.lv_nhacungcap);
        listView.setOnItemClickListener(listViewOnItemClickListener);
        listView.setOnItemLongClickListener(listViewOnItemLongClickListener);
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
            Intent intent = new Intent(getApplicationContext(), NewProviderActivity.class);
            startActivity(intent);
        }
    };

    AdapterView.OnItemClickListener listViewOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), NewProviderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("id", providers.get(position).getId());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    AdapterView.OnItemLongClickListener listViewOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Cảnh báo !!!");
            alert.setMessage("Bạn có thật sự muốn xóa không?");
            alert.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dbManager.deleteProvider(providers.get(position).getId());
                    reloadListView();
                    dialog.dismiss();
                }
            });
            alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
            return true;
        }
    };

    private void reloadListView() {
        providers = dbManager.getAllProviders();
        ProviderAdapter providerAdapter = new ProviderAdapter(this, R.layout.listview_item, providers);
        listView.setAdapter(providerAdapter);
    }

    @Override
    protected void onStart() {
        context = this;

        dbManager = new DBManager(this);
        dbManager.open();
        reloadListView();

        super.onStart();
    }

    @Override
    protected void onStop() {
        dbManager.close();
        super.onStop();
    }
}

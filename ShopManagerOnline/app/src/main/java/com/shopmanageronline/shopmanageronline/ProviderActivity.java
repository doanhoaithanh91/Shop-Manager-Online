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

import com.shopmanageronline.shopmanageronline.entity.Provider;
import com.shopmanageronline.shopmanageronline.helper.DBManager;

import java.util.ArrayList;
import java.util.List;

public class ProviderActivity extends AppCompatActivity {
    private Button btnAdd;
    private static ListView listView;

    private static ProviderActivity providerActivity;
    private static DBManager dbManager;
    private static List<Provider> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        providerActivity = this;
        setTitle(R.string.lable_nhacungcap);

        dbManager = new DBManager(this);
        dbManager.open();

        btnAdd = (Button) findViewById(R.id.btn_add_nhacungcap);
        btnAdd.setOnClickListener(addButtonOnClickListener);

        listView = (ListView) findViewById(R.id.lv_nhacungcap);
        setListViewAdapter();
        listView.setOnItemClickListener(listViewOnItemClickListener);
        listView.setOnItemLongClickListener(listViewOnItemLongClickListener);
    }

    public static void setListViewAdapter() {
        providers = dbManager.getAllProviders();
        ArrayAdapter<Provider> arrayAdapter = new ArrayAdapter(providerActivity, R.layout.listview_item, R.id.textView1, providers);
        listView.setAdapter(arrayAdapter);
    }

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
            AlertDialog.Builder alert = new AlertDialog.Builder(providerActivity);
            alert.setTitle("Cảnh báo !!!");
            alert.setMessage("Bạn có thật sự muốn xóa không?");
            alert.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dbManager.deleteProvider(providers.get(position).getId());
                    setListViewAdapter();
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

    @Override
    protected void onResume() {
        dbManager.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dbManager.close();
        super.onPause();
    }
}

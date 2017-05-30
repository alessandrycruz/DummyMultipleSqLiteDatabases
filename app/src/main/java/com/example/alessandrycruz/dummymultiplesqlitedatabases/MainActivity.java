package com.example.alessandrycruz.dummymultiplesqlitedatabases;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.helpers.Base_Helper;
import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.utils.AgencySettings_Util;
import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.utils.Base_Util;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AgencySettings_Util mAgencySettings_Util;
    private Context mContext;
    private Base_Helper mBase_Helper;
    private Base_Util mBase_Util;
    private EditText mEditTextDatabaseName;
    private EditText mEditTextDummyData;
    private TextView mTextViewData;
    private TextView mTextViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        mEditTextDatabaseName = (EditText) findViewById(R.id.edit_text_database_name);
        mEditTextDummyData = (EditText) findViewById(R.id.edit_text_database_dummy_data);
        mTextViewData = (TextView) findViewById(R.id.text_view_data);
        mTextViewStatus = (TextView) findViewById(R.id.textview_status);

        mBase_Util = new Base_Util();
        mAgencySettings_Util = new AgencySettings_Util();

        if (Base_Helper.DEBUG) {
            mBase_Util.createDatabasePath();
        }
    }

    public void onClickCreateNewDatabase(View view) {
        openDatabase();
    }

    public void onClickInsertDummyData(View view) {
        if (mBase_Helper != null) {
            String dummyData = mEditTextDummyData.getText().toString();

            if (!dummyData.isEmpty()) {
                mAgencySettings_Util.secureInsertEntry(mBase_Helper, dummyData);
                Toast.makeText(mContext, "Entry Inserted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "Field Empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickOpenDatabase(View view) {
        closeDataBase();

        openDatabase();
    }

    public void onClickCloseDatabase(View view) {
        closeDataBase();
    }

    public void onClickSeeDatabaseData(View view) {
        if (mBase_Helper != null) {
            List<String> entries = mAgencySettings_Util.readEntries(mBase_Helper);

            mTextViewData.setText(entries.get(entries.size() - 1));

            Toast.makeText(mContext, "Entries Read.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        closeDataBase();
    }

    private void openDatabase() {
        String databaseName = mEditTextDatabaseName.getText().toString();

        if (!databaseName.isEmpty()) {
            mBase_Helper = new Base_Helper(getApplicationContext(), databaseName);
            mTextViewStatus.setText("Current Database: " + databaseName);
            Toast.makeText(mContext, "Database Opened.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Field Empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void closeDataBase() {
        if (mBase_Helper != null) {
            mBase_Helper.close();
            mBase_Helper = null;

            Toast.makeText(mContext, "Database Closed.", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.alessandrycruz.dummymultiplesqlitedatabases.database.columns;

import android.provider.BaseColumns;

/**
 * Created by alessandry.cruz on 5/23/2017.
 */

public final class Base_Column {
    private Base_Column() {
    }

    public static class AgencySettigs implements BaseColumns {
        public static final String TABLE_NAME = "AgencySettings";
        public static final String COLUMN_NAME_JSON = "json";
    }
}

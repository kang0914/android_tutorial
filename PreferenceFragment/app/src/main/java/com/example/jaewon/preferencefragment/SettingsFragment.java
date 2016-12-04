package com.example.jaewon.preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Jaewon on 2016-12-04.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_activity);
    }
}

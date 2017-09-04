package com.devtom.plugintest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by tomliu on 2017/9/3.
 */

public class TestActivity extends DummyActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }
}

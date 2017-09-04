package com.devtom.plugintest;

import android.util.Log;

import dalvik.system.PathClassLoader;

/**
 * Created by tomliu on 2017/9/3.
 */

public class HookClassLoader extends PathClassLoader {


    public HookClassLoader(String dexPath, ClassLoader parent) {
        super(dexPath, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Log.e("HookClassLoader", name + "is loaded");
        if (name.equals("com.devtom.plugintest.DummyActivity")) {
            return super.loadClass("com.devtom.plugintest.TestActivity");
        }
        return super.loadClass(name);
    }
}

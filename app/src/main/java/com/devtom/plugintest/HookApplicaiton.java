package com.devtom.plugintest;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by tomliu on 2017/9/3.
 */

public class HookApplicaiton extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        patch(this);
    }


    public static boolean patch(Application application) {
        try {
            // 获取Application的BaseContext （来自ContextWrapper）
            Context oBase = application.getBaseContext();


            // 获取mBase.mPackageInfo
            // 1. ApplicationContext - Android 2.1
            // 2. ContextImpl - Android 2.2 and higher
            // 3. AppContextImpl - Android 2.2 and higher


            Object oPackageInfo = ReflectUtils.readField(oBase, "mPackageInfo");
            if (oPackageInfo == null) {

                return false;
            }

            // mPackageInfo的类型主要有两种：
            // 1. android.app.ActivityThread$PackageInfo - Android 2.1 - 2.3
            // 2. android.app.LoadedApk - Android 2.3.3 and higher


            // 获取mPackageInfo.mClassLoader
            ClassLoader oClassLoader = (ClassLoader) ReflectUtils.readField(oPackageInfo, "mClassLoader");
            if (oClassLoader == null) {
                return false;
            }

            // 外界可自定义ClassLoader的实现，但一定要基于RePluginClassLoader类
            ClassLoader cl = new HookClassLoader("", oClassLoader);

            // 将新的ClassLoader写入mPackageInfo.mClassLoader
            ReflectUtils.writeField(oPackageInfo, "mClassLoader", cl);

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

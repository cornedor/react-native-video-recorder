package info.corne.reactnativevideorecorder;

import android.app.Activity;
import android.content.Context;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by corne on 04/07/16.
 */

public class VideoRecorderPackage implements ReactPackage {

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext context) {
        return Arrays.<ViewManager>asList(new VideoRecorderManager(context.getCurrentActivity()));
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext context) {
        List<NativeModule> modules = new ArrayList<>();
        return modules;
    }

}

package info.corne.reactnativevideorecorder;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by corne on 27/06/16.
 */

public class VideoRecorderManager extends SimpleViewManager<VideoRecorderBase> implements LifecycleEventListener {
    private static final int COMMAND_RECORD = 559;
    private static final int COMMAND_STOP = 365;
    private Activity mActivity;
    private VideoRecorderBase mView;

    public VideoRecorderManager(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onHostResume() {
        mView.onResume();
    }

    @Override
    public void onHostPause() {
        mView.onPause();
    }

    @Override
    public void onHostDestroy() {

    }

    @ReactProp(name = "type")
    public void setType(VideoRecorderBase view, @Nullable String type) {
        view.setType(type);
    }

    @ReactProp(name = "videoEncodingBitrate", defaultInt = 7000000)
    public void setVideoEncodingBitrate(VideoRecorderBase view, int bitrate) {
        view.setVideoEncodingBitrate(bitrate);
    }

    @ReactProp(name = "videoEncodingFrameRate", defaultInt = 30)
    public void setVideoEncodingFrameRate(VideoRecorderBase view, int frameRate) {
        view.setVideoEncodingFrameRate(frameRate);
    }

    @Override
    public String getName() {
        return "VideoRecorder";
    }

    @Override
    protected VideoRecorderBase createViewInstance(ThemedReactContext reactContext) {
        Log.d("MWIT", "Started cam thing");
        reactContext.addLifecycleEventListener(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mView = new Camera2RecorderView(reactContext, mActivity);
        } else {
            mView = new CameraRecorderView(reactContext);
        }

        return mView;
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "recordingStart", MapBuilder.of("registrationName", "onRecordingStarted"),
                "recordingFinish", MapBuilder.of("registrationName", "onRecordingFinished"),
                "cameraAccessException", MapBuilder.of("registrationName", "onCameraAccessException"),
                "cameraFailed", MapBuilder.of("registrationName", "cameraFailed")
        );
    }

    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "record", COMMAND_RECORD,
                "stop", COMMAND_STOP
        );
    }

    @Override
    public void receiveCommand(
            VideoRecorderBase view,
            int commandType,
            @Nullable ReadableArray args
    ) {
        Assertions.assertNotNull(view);

        switch (commandType) {
            case COMMAND_RECORD:
                view.record();
                break;
            case COMMAND_STOP:
                view.stop();
        }
    }
}

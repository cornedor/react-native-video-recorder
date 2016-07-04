package info.corne.reactnativevideorecorder;

import android.content.Context;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import info.corne.reactnativevideorecorder.AutoFitTextureView;

/**
 * Created by corne on 27/06/16.
 */

abstract class VideoRecorderBase extends AutoFitTextureView {

    public VideoRecorderBase(Context context) {
        super(context);
    }

    abstract void record();
    abstract void stop();
    abstract void onResume();
    abstract void onPause();

    abstract void setType(String type);
    abstract void setVideoEncodingBitrate(int bitrate);
    abstract void setVideoEncodingFrameRate(int frameRate);

    abstract boolean isRecording();

    public void onRecordingStarted() {
        WritableMap event = Arguments.createMap();
        event.putBoolean("ok", true);
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "recordingStart",
                event
        );
    }

    public void onRecordingStopped(String path) {
        WritableMap event = Arguments.createMap();
        event.putString("file", path);
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "recordingFinish",
                event
        );
    }

    public void onCameraAccessException() {
        WritableMap event = Arguments.createMap();
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "cameraAccessException",
                event
        );
    }

    public void onCameraFailed() {
        WritableMap event = Arguments.createMap();
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "cameraFailed",
                event
        );
    }
}

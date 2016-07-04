package info.corne.reactnativevideorecorder;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceView;
import android.view.TextureView;

import java.io.File;
import java.io.IOException;

/**
 * Created by corne on 27/06/16.
 */

public class CameraRecorderView extends VideoRecorderBase implements TextureView.SurfaceTextureListener {
    private Camera mCamera;
    private String mStorageDirectory;
    private String mFilename;

    public CameraRecorderView(Context context) {
        super(context);
        this.setSurfaceTextureListener(this);
    }

    @Override
    void record() {

    }

    @Override
    void stop() {

    }

    @Override
    void onResume() {

    }

    @Override
    void onPause() {

    }

    @Override
    void setType(String type) {

    }

    @Override
    void setVideoEncodingBitrate(int bitrate) {

    }

    @Override
    void setVideoEncodingFrameRate(int frameRate) {

    }

    @Override
    boolean isRecording() {
        return false;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open();
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPreviewSize(width, height);

            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}

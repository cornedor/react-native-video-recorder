# React Native Video Recorder

## This is a very experimental module, you probably don't want to use it. Take a look at https://github.com/lwansbrough/react-native-camera

This is a video recorder component using the Camera 2 API. It currently does not
support the older API so only devices with Android Lollipop or higher are supported.

```
import VideoRecorder from 'react-native-video-recorder';

<VideoRecorder
  ref="recorder"
  onRecordingStarted={() => console.log('Started')}
  onRecordingFinished={(e) => console.log(e.nativeEvent.file)}
  onCameraAccessException={() => alert('No permission for camera')}
  onCameraFailed={() => alert('Camera failed')}
  type="front"
  videoEncodingBitrate={7000000}
  videoEncodingFrameRate={30}
/>

this.refs.recorder.start();
this.refs.recorder.stop();
```

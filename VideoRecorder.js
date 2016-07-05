import React, { Component, PropTypes } from 'react';
import ReactNative, {
  requireNativeComponent,
  UIManager,
  StyleSheet,
} from 'react-native';

const NVideoRecorder = requireNativeComponent('VideoRecorder', VideoRecorder, {});

const styles = StyleSheet.create({
  camera: {
    flex: 1,
  }
});

export default class VideoRecorder extends Component {
  stop() {
    UIManager.dispatchViewManagerCommand(
      this.getNodeHandle(),
      UIManager.VideoRecorder.Commands.stop,
      null
    );
  }

  record() {
    UIManager.dispatchViewManagerCommand(
      this.getNodeHandle(),
      UIManager.VideoRecorder.Commands.record,
      null
    );
  }

  getNodeHandle() {
    return ReactNative.findNodeHandle(this.refs.recorder);
  }

  render() {
    return (
      <NVideoRecorder
        {...this.props}
        ref="recorder"
        style={[styles.camera, this.props.style]}
      />
    );
  }
}

VideoRecorder.propTypes = {
  onRecordingStarted: PropTypes.func,
  onRecordingFinished: PropTypes.func,
  onCameraAccessException: PropTypes.func,
  onCameraFailed: PropTypes.func,
  type: PropTypes.oneOf(['front', 'back']),
  videoEncodingBitrate: PropTypes.number,
  videoEncodingFrameRate: PropTypes.number
};

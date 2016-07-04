import React, { Component, PropTypes } from 'react';
import ReactNative, {
  requireNativeComponent,
  UIManager,
  StyleSheet,
} from 'react-native';

const NVideoRecorder = requireNativeComponent('NVideoRecorder', VideoRecorder, {});

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

  start() {
    UIManager.dispatchViewManagerCommand(
      this.getNodeHandle(),
      UIManager.VideoRecorder.Commands.start,
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
  type: PropTypes.oneOn(['front', 'back']),
  videoEncodingBitrate: PropTypes.number,
  videoEncodingFrameRate: PropTypes.number
};

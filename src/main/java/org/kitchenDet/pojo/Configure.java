package org.kitchenDet.pojo;

public class Configure {
    private String image_resolution;
    private int frameRate;
    private int interFrame;
    private String streamType;
    private String cameraID;

    public String getImage_resolution() {
        return image_resolution;
    }

    public void setImage_resolution(String image_resolution) {
        this.image_resolution = image_resolution;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public int getInterFrame() {
        return interFrame;
    }

    public void setInterFrame(int interFrame) {
        this.interFrame = interFrame;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public String getCameraID() {
        return cameraID;
    }

    public void setCameraID(String cameraID) {
        this.cameraID = cameraID;
    }
}

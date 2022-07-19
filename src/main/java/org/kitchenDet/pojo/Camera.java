package org.kitchenDet.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Camera {
    private String cameraName;
    private String remark;
    private String belongID;
    private String cameraID;
    private int status;
    private String IP;
    private String brand;
    private Timestamp time_start;
    private Date time_reload;

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBelongID() {
        return belongID;
    }

    public void setBelongID(String belongID) {
        this.belongID = belongID;
    }

    public String getCameraID() {
        return cameraID;
    }

    public void setCameraID(String cameraID) {
        this.cameraID = cameraID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Timestamp getTime_start() {
        return time_start;
    }

    public void setTime_start(Timestamp time_start) {
        this.time_start = time_start;
    }

    public Date getTime_reload() {
        return time_reload;
    }

    public void setTime_reload(Date time_reload) {
        this.time_reload = time_reload;
    }
}

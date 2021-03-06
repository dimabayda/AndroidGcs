package com.diploma.dima.androidgcs.mavconnection.gcs;

import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_attitude;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_global_position_int;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_heartbeat;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_mission_current;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_sys_status;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.enums.MAV_MODE;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.enums.MAV_MODE_FLAG;

public class VehicleParameters {
    private int currentMissionSeq;
    private float alt, relativeAlt, lat, lng;
    private float roll, pitch, yaw; //Крен, Тангаж, Рысканье
    private float batteryVoltage, batteryCurrent;
    private int batteryRemaining;
    private String mode;

    void setMode(short baseMode){
        switch (baseMode){
            case MAV_MODE.MAV_MODE_AUTO_ARMED:
                mode = "AUTO ARMED";
                break;

            case MAV_MODE.MAV_MODE_AUTO_DISARMED:
                mode = "AUTO DISARMED";
                break;

            case MAV_MODE.MAV_MODE_GUIDED_ARMED:
                mode = "GUIDED ARMED";
                break;

            case MAV_MODE.MAV_MODE_GUIDED_DISARMED:
                mode = "GUIDED DISARMED";
                break;

            case MAV_MODE.MAV_MODE_MANUAL_ARMED:
                mode = "MANUAL ARMED";
                break;

            case MAV_MODE.MAV_MODE_MANUAL_DISARMED:
                mode = "MANUAL DISARMED";
                break;

            case MAV_MODE.MAV_MODE_PREFLIGHT:
                mode = "PREFLIGHT";
                break;

            case MAV_MODE.MAV_MODE_STABILIZE_ARMED:
                mode = "STABILIZE ARMED";
                break;

            case MAV_MODE.MAV_MODE_STABILIZE_DISARMED:
                mode = "STABILIZE DISARMED";
                break;

            case MAV_MODE.MAV_MODE_TEST_ARMED:
                mode = "TEST ARMED";
                break;

            case MAV_MODE.MAV_MODE_TEST_DISARMED:
                mode = "TEST DISARMED";
                break;

            default:
                mode = String.format("MODE(%d)", baseMode);
                break;
        }
    }

    void setMissionCurrent(msg_mission_current missionCurrent) {
        currentMissionSeq = missionCurrent.seq;
    }

    void setGlobalPosition(msg_global_position_int globalPosition) {
        lat = globalPosition.lat / 10000000;
        lng = globalPosition.lon / 10000000;
        alt = globalPosition.alt / 1000;
        relativeAlt = globalPosition.relative_alt / 1000;
    }

    void setAttitude(msg_attitude attitude) {
        roll = attitude.roll;
        pitch = attitude.pitch;
        yaw = attitude.yaw;
    }

    void setSysStatus(msg_sys_status sysStatus) {
        batteryVoltage = sysStatus.voltage_battery / 1000;
        batteryCurrent = sysStatus.current_battery / 100;
        batteryRemaining = sysStatus.battery_remaining;
    }

    public int getCurrentMissionSeq() {
        return currentMissionSeq;
    }

    public float getAlt() {
        return alt;
    }

    public float getRelativeAlt() {
        return relativeAlt;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public float getRoll() {
        return roll;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getBatteryVoltage() {
        return batteryVoltage;
    }

    public float getBatteryCurrent() {
        return batteryCurrent;
    }

    public int getBatteryRemaining() {
        return batteryRemaining;
    }

    public String getMode() {
        return mode;
    }
}

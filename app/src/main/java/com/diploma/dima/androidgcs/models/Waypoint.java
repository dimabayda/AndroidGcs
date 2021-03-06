package com.diploma.dima.androidgcs.models;

import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_mission_item;
import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.enums.MAV_CMD;
import com.google.android.gms.maps.model.LatLng;
import com.orm.SugarRecord;

public class Waypoint extends SugarRecord {
    float x;
    float y;
    float height;
    WayPointType wayPointType;

    //Param 1
    float takeOffAngle;

    MapWay mapWay;

    public Waypoint() {
        x = 0;
        y = 0;
        height = 0;
        wayPointType = WayPointType.WayPoint;
        takeOffAngle = 30;
    }

    public Waypoint(float x, float y, float height, MapWay mapWay, WayPointType wayPointType) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.wayPointType = wayPointType;
        this.mapWay = mapWay;
        takeOffAngle = 30;
    }

    public Waypoint(msg_mission_item missionItem, MapWay mapWay) {
        this.x = missionItem.x;
        this.y = missionItem.y;
        this.height = missionItem.z;
        this.mapWay = mapWay;

        switch (missionItem.command) {
            case MAV_CMD.MAV_CMD_NAV_TAKEOFF:
                wayPointType = WayPointType.TakeOff;
                break;
            case MAV_CMD.MAV_CMD_NAV_LAND:
                wayPointType = WayPointType.Land;
                break;
            case MAV_CMD.MAV_CMD_NAV_WAYPOINT:
                wayPointType = WayPointType.WayPoint;
                break;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTakeOffAngle(float takeOffAngle) {
        this.takeOffAngle = takeOffAngle;
    }

    public float getTakeOffAngle() {
        return takeOffAngle;
    }

    public LatLng getLatLng() {
        return new LatLng(x, y);
    }

    public void setWayPointType(WayPointType type) {
        wayPointType = type;
    }

    public WayPointType getWayPointType() {
        return wayPointType;
    }

    public msg_mission_item getMavLinkItem() {
        msg_mission_item item = new msg_mission_item();
        item.x = x;
        item.y = y;
        item.z = height;

        switch (wayPointType) {
            case TakeOff:
                item.command = MAV_CMD.MAV_CMD_NAV_TAKEOFF;
                item.param1 = takeOffAngle;
                break;
            case Land:
                item.command = MAV_CMD.MAV_CMD_NAV_LAND;
                break;
            case WayPoint:
                item.command = MAV_CMD.MAV_CMD_NAV_WAYPOINT;
                break;
        }

        return item;
    }

    public Waypoint clone() {
        return new Waypoint(x, y, height, mapWay, wayPointType);
    }
}

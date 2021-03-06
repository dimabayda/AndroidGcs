package com.diploma.dima.androidgcs.utils;

import com.diploma.dima.androidgcs.mavconnection.gcs.MAVLink.common.msg_mission_item;
import com.diploma.dima.androidgcs.models.MapWay;
import com.diploma.dima.androidgcs.models.Waypoint;

import java.util.ArrayList;
import java.util.List;

public class WaypointConverter {

    public static List<msg_mission_item> convert(List<Waypoint> waypoints) {
        ArrayList<msg_mission_item> newArray = new ArrayList<>();

        for (Waypoint waypoint : waypoints) {
            newArray.add(waypoint.getMavLinkItem());
        }

        return newArray;
    }

    public static List<Waypoint> convertBack(List<msg_mission_item> waypoints, MapWay mapWay) {
        ArrayList<Waypoint> newArray = new ArrayList<>();

        for (msg_mission_item waypoint : waypoints) {
            newArray.add(new Waypoint(waypoint, mapWay));
        }

        return newArray;
    }
}
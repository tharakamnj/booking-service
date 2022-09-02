package com.booking.constant;

import java.util.HashMap;
import java.util.Map;

public enum RoomType {
    DELUXE_ROOM(0),
    DOUBLE_ROOM(1),
    SINGLE_ROOM(2);

    private int value;
    private static Map map = new HashMap<>();

    private RoomType(int value){
        this.value=value;
    }
    static {
        for (RoomType roomType:RoomType.values()){
            map.put(roomType.value,roomType);
        }
    }

    public static RoomType valueOf(int type){
        return (RoomType) map.get(type);
    }

    public int getValue(){
        return value;
    }
}

package com.gfl.common.utils;


import com.gfl.common.entity.PositionInfo;

/**
 * 采用矩形算法的工具类
 * @author zjr
 *
 */
public class LatitudeLontitudeUtil {  
  
  
    /** 地球半径 */  
    private static final double EARTH_RADIUS = 6371000;  
    /** 范围距离 */  
    private double distance;
    /** 左上角 */  
    private PositionInfo left_top = null;
    /** 右上角 */  
    private PositionInfo right_top = null;  
    /** 左下角 */  
    private PositionInfo left_bottom = null;  
    /** 右下角 */  
    private PositionInfo right_bottom = null;  
  
    private LatitudeLontitudeUtil(double distance) {  
        this.distance = distance;  
    }  
    private void getRectangle4Point(double lat, double lng) { //经度，纬度 
  
        double dlng = 2 * (Math.asin(Math.sin(distance / (2 * EARTH_RADIUS))  
                / Math.cos(Math.toRadians(lat))));  
        dlng = Math.toDegrees(dlng);  
  
      
        double dlat = distance / EARTH_RADIUS;  
        dlat = Math.toDegrees(dlat); //  弧度转换成角度  
  
       
        left_top = new PositionInfo(lat + dlat, lng - dlng);
        right_top = new PositionInfo(lat + dlat, lng + dlng);  
        left_bottom = new PositionInfo(lat - dlat, lng - dlng);  
        right_bottom = new PositionInfo(lat - dlat, lng + dlng);  
  
    }  
  
    public static double hav(double theta) {  
        double s = Math.sin(theta / 2);  
        return s * s;  
    }  
  
    public static double getDistance(double lat0, double lng0, double lat1,  
            double lng1) {  
        
    
        lat0 = Math.toRadians(lat0);  
        lat1 = Math.toRadians(lat1);  
        lng0 = Math.toRadians(lng0);  
        lng1 = Math.toRadians(lng1);  
  
        double dlng = Math.abs(lng0 - lng1);  
        double dlat = Math.abs(lat0 - lat1);  
        double h = hav(dlat) + Math.cos(lat0) * Math.cos(lat1) * hav(dlng);  
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));  
  
        return distance;  
    }  
  
    public static PositionInfo[] getRectangle4Point(double lat, double lng,  
            double distance) {  
    	 LatitudeLontitudeUtil llu = new LatitudeLontitudeUtil(distance);  
         llu.getRectangle4Point(lat, lng);  
         PositionInfo[] locations = new PositionInfo[4];  
         locations[0] = llu.left_top;  
         locations[1] = llu.right_top;  
         locations[2] = llu.left_bottom;  
         locations[3] = llu.right_bottom;  
         return locations;  
    }  
  
   
} 
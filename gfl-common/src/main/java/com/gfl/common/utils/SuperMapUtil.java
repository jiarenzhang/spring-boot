package com.gfl.common.utils;

import java.awt.geom.Point2D;
import java.util.List;

public class SuperMapUtil {
    /**
     * 判断点是否在多边形内（百度提供）
     * @param point 检测点
     * @param pts   多边形的顶点
     * @return      点在多边形内返回true,否则返回false
     */
    public static boolean isPtInPoly(Point2D.Double point, List<Point2D.Double> pts) {
        if (pts == null || pts.size() == 0) {
            return false;
        }
        int N = pts.size();
        //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        boolean boundOrVertex = true;
        int intersectCount = 0;
        //浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        Point2D.Double p1, p2;
        //当前点
        Point2D.Double p = point;
        p1 = pts.get(0);
        for (int i = 1; i <= N; ++i) {
            if (p.equals(p1)) {
                return boundOrVertex;
            }
            p2 = pts.get(i % N);
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                continue;
            }
            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                if (p.y <= Math.max(p1.y, p2.y)) {
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) {
                        return boundOrVertex;
                    }
                    if (p1.y == p2.y) {
                        if (p1.y == p.y) {
                            return boundOrVertex;
                        } else {
                            ++intersectCount;
                        }
                    } else {
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
                        if (Math.abs(p.y - xinters) < precision) {
                            return boundOrVertex;
                        }
                        if (p.y < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {
                if (p.x == p2.x && p.y <= p2.y) {
                    Point2D.Double p3 = pts.get((i + 1) % N);
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }
        if (intersectCount % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 断点是否在多边形内（高德地图提供）
     * @param point 检测点
     * @param scope 点在多边形内返回true,否则返回false
     * @return
     */
    public static boolean contains(Point2D.Double point, List<Point2D.Double> scope) {
        if (scope == null || scope.size() == 0) {
            return false;
        }
        boolean var2 = false;
        double var3 = 1.0E-9D;
        int var5 = 0;
        double var10 = 180.0D;
        double var6 = point.x;
        double var8 = point.y;
        double var12 = point.y;
        if (scope.size() < 3) {
            return false;
        } else {
            if (!scope.get(0).equals(scope.get(scope.size() - 1))) {
                scope.add(scope.get(0));
            }

            for (int var14 = 0; var14 < scope.size() - 1; ++var14) {
                double var15 = scope.get(var14).x;
                double var17 = scope.get(var14).y;
                double var19 = scope.get(var14 + 1).x;
                double var21 = scope.get(var14 + 1).y;
                if (b(var6, var8, var15, var17, var19, var21)) {
                    return true;
                }

                if (Math.abs(var21 - var17) >= var3) {
                    if (b(var15, var17, var6, var8, var10, var12)) {
                        if (var17 > var21) {
                            ++var5;
                        }
                    } else if (b(var19, var21, var6, var8, var10, var12)) {
                        if (var21 > var17) {
                            ++var5;
                        }
                    } else if (a(var15, var17, var19, var21, var6, var8, var10, var12)) {
                        ++var5;
                    }
                }
            }
            if (var5 % 2 != 0) {
                var2 = true;
            }
            return var2;
        }
    }

    public static double a(double var0, double var2, double var4, double var6, double var8, double var10) {
        return (var4 - var0) * (var10 - var2) - (var8 - var0) * (var6 - var2);
    }


    public static boolean b(double var0, double var2, double var4, double var6, double var8, double var10) {
        boolean var12 = false;
        double var13 = 1.0E-9D;
        if (Math.abs(a(var0, var2, var4, var6, var8, var10)) < var13 && (var0 - var4) * (var0 - var8) <= 0.0D && (var2 - var6) * (var2 - var10) <= 0.0D) {
            var12 = true;
        }

        return var12;
    }

    public static boolean a(double var0, double var2, double var4, double var6, double var8, double var10, double var12, double var14) {
        boolean var16 = false;
        double var17 = (var4 - var0) * (var14 - var10) - (var6 - var2) * (var12 - var8);
        if (var17 != 0.0D) {
            double var19 = ((var2 - var10) * (var12 - var8) - (var0 - var8) * (var14 - var10)) / var17;
            double var21 = ((var2 - var10) * (var4 - var0) - (var0 - var8) * (var6 - var2)) / var17;
            if (var19 >= 0.0D && var19 <= 1.0D && var21 >= 0.0D && var21 <= 1.0D) {
                var16 = true;
            }
        }
        return var16;
    }

}

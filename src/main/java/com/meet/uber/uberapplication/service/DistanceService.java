package com.meet.uber.uberapplication.service;

import org.locationtech.jts.geom.Point;


public interface DistanceService {
    double calculateDistance(Point src, Point dest);
}

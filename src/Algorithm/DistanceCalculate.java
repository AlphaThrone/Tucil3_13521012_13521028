package Algorithm;

import org.jxmapviewer.viewer.GeoPosition;

public class DistanceCalculate{

    private static final double DISTANCE_CONST = 111.18957696;

	public static double distance(GeoPosition pos1, GeoPosition pos2) {
		if ((pos1.getLatitude() == pos2.getLatitude()) && (pos1.getLongitude() == pos2.getLongitude())) {
			return 0;
		}
		else {
			double theta = pos1.getLongitude() - pos2.getLongitude();
			double dist = Math.sin(Math.toRadians(pos1.getLatitude())) * Math.sin(Math.toRadians(pos2.getLatitude())) + Math.cos(Math.toRadians(pos1.getLatitude())) 
                        * Math.cos(Math.toRadians(pos2.getLatitude())) 
                        * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * DISTANCE_CONST*1000;/*satuan meter*/
			return (dist);
		}
	}
}

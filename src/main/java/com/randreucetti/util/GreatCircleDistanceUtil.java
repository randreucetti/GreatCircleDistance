package com.randreucetti.util;

/**
 * A utility class used to measuere distance based on longitude and latitude
 * 
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Great-circle_distance">Great-circle_distance</a>
 * 
 * @author ross
 *
 */
public class GreatCircleDistanceUtil {

	public static final int RADIAN_TO_KM = 6371;

	/**
	 * 
	 * @param sourceLongitude
	 * @param sourceLatitude
	 * @param destLongitude
	 * @param destLatitude
	 * @return distance in km between points
	 */
	public static double getDistanceInKm(double sourceLongitude, double sourceLatitude, double destLongitude,
			double destLatitude) {
		return getDistanceInRadians(sourceLongitude, sourceLatitude, destLongitude, destLatitude) * RADIAN_TO_KM;
	}

	/**
	 * 
	 * @param sourceLongitude
	 * @param sourceLatitude
	 * @param destLongitude
	 * @param destLatitude
	 * @return distance in radians between points
	 */
	public static double getDistanceInRadians(double sourceLongitude, double sourceLatitude, double destLongitude,
			double destLatitude) {
		// Need to convert from degrees to radians first
		double long1 = Math.toRadians(sourceLongitude);
		double lat1 = Math.toRadians(sourceLatitude);
		double long2 = Math.toRadians(destLongitude);
		double lat2 = Math.toRadians(destLatitude);

		// Now we can get the distance in radians
		// https://en.wikipedia.org/wiki/Great-circle_distance#Formulas
		double distanceRadians = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2)
				* Math.cos(Math.abs(long1 - long2)));

		return distanceRadians;
	}
}

package edu.gdgspb.androidacademy2018.noticeme;

public class AreaIncludesVerifier {
    private Double longitudeCurrent, latitudeCurrent;
    private Double radius;

    public AreaIncludesVerifier(Double longitude, Double latitude, Double radius) {
        this.longitudeCurrent = longitude;
        this.latitudeCurrent = latitude;
        this.radius = radius;
    }

    /**
     * Возвращает расстояние между двума координатами
     */
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);
        System.out.println("Distance meters: " + dist);
        return dist;
    }

    public boolean isInArea(Double longitude, Double latitude) {

        return distFrom(longitude, latitude, longitudeCurrent, latitudeCurrent) <= radius;
    }

}

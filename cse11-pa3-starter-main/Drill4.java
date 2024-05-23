class Drill4 {
    // returns a String of the phase of water the given temperature (in celsius) would result in
    String phaseOfWater(int temp) {
        if (temp >= 100) { return "vapor";}
        if (temp <= 0) { return "solid";}
        return "liquid";
    }

    // returns the largest absolute difference between any two inputs
    int maxDifference(int x, int y, int z){
        int max = Math.max(Math.max(x, y), z);
        int min = Math.min(Math.min(x, y), z);
        return max - min;
    }

    // returns the area of the ring between two circles, assuming both inputs are positive and that the first number is smaller than the second
    double ringArea(double radiusInner, double radiusOuter){
        double areaInner = radiusInner * radiusInner * Math.PI;
        double areaOuter = radiusOuter * radiusOuter * Math.PI;
        return areaOuter - areaInner;
    }

    String phase1 = phaseOfWater(150);      // "vapor"
    String phase2 = phaseOfWater(80);      // "liquid"
    String phase3 = phaseOfWater(-5);      // "solid"

    int max1 = maxDifference(1, 2, 3);      // 2
    int max2 = maxDifference(10, -10, 0);       // 20
    int max3 = maxDifference(-10, -13, -11);        // 3
    int max4 = maxDifference(0, 0, 0);      // 0

    double ring1 = ringArea(1, 2);       // 9.42 (rounded)
    double ring2 = ringArea(10, 15);       // 392.70 (rounded)
    double ring3 = ringArea(50, 60);       // 3455.75 (rounded)
}
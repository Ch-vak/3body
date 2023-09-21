package main;

public class Motion {

    int G = 10;
    // Masses of the Planets
    int m1 = 10;
    int m2 = 10;
    int m3 = 10;

    // Starting coordinates

    public double[] getP1_start() {
        return p1_start;
    }

    public double[] getV1_start() {
        return v1_start;
    }

    public double[] getP2_start() {
        return p2_start;
    }

    public double[] getV2_start() {
        return v2_start;
    }

    public double[] getP3_start() {
        return p3_start;
    }

    public double[] getV3_start() {
        return v3_start;
    }

    double [] p1_start = {10000,10000};
    double [] v1_start = {0,0};

    double [] p2_start = {2000,1000};
    double [] v2_start = {0,0};
    double [] p3_start = {5000,1000};
    double [] v3_start = {0,0};

    // Positions

    public double []  accelerations(double p1 , double p2 , double p3){
        double p12 = Math.pow((p1_start[0] + p2_start[0]),2) + Math.pow((p1_start[1] + p2_start[1]),2);
        double p13 = Math.pow((p1_start[0] + p3_start[0]),2) + Math.pow((p1_start[1] + p3_start[1]),2);
        double p23 = Math.pow((p3_start[0] + p2_start[0]),2) + Math.pow((p3_start[1] + p2_start[1]),2);

        double a1 = -G * m2 *(p1-p2) / Math.pow(p12,1.5) - G * m3 * (p1-p3) / Math.pow(p13,1.5);

        double a2 = -G * m1 *(p2-p1) / Math.pow(p12,1.5) - G * m3 * (p2-p3) / Math.pow(p23,1.5);

        double a3 = -G * m1 *(p3-p1) / Math.pow(p13,1.5) - G * m2 * (p3-p2) / Math.pow(p23,1.5);

        return new double[]{a1,a2,a3};
    }


    public void update(){

        int dt = 1000;
        double [] xdvs = accelerations(p1_start[0],p2_start[0], p3_start[0]);
        double [] ydvs = accelerations(p1_start[1],p2_start[1], p3_start[1]);

        double [] xtempv = {v1_start[0],v2_start[0],v3_start[0]};
        double [] ytempv = {v1_start[1],v2_start[1],v3_start[1]};


        v1_start[0]= v1_start[0] + xdvs[0] * dt;
        v2_start[0]= v2_start[0] + xdvs[1] * dt;
        v3_start[0]= v3_start[0] + xdvs[2] * dt;

        v1_start[1]= v1_start[1] + ydvs[0] * dt;
        v2_start[1]= v2_start[1] + ydvs[1] * dt;
        v3_start[1]= v3_start[1] + ydvs[2] * dt;

        p1_start[0] += xtempv[0] * dt;
        p2_start[0] += xtempv[1] * dt;
        p3_start[0] += xtempv[2] * dt;


        p1_start[1] += ytempv[0] * dt;
        p2_start[1] += ytempv[1] * dt;
        p3_start[1] += ytempv[2] * dt;

    }

    public double [ ] getBarycenterCoordinates(){
        double x = (m1 * p1_start[0] + m2 * p2_start[0] + m3 * p3_start[0]) / (m1 + m2 + m3);
        double y = (m1 * p1_start[1] + m2 * p2_start[1] + m3 * p3_start[1]) / (m1 + m2 + m3);

        return new double[]{x,y};
    }
}

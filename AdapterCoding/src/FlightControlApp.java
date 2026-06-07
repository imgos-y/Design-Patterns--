interface SpeedSensor { // target
    void recordVelocity(double kmh);
}

class ModernDigitalSensor implements SpeedSensor { // currently using method
    @Override
    public void recordVelocity(double kmh) {
        System.out.println("Modern Sensor: Digital reading confirmed at " + kmh + " km/h.");
    }
}

class ImperialSensor {  // adaptee
    public void logSpeedKnots(int knots, String status) {
        System.out.println("Legacy Hardware: Speed logged at " + knots + " knots.");
        System.out.println("Status: " + status);
    }
}

class ImperialSensorAdapter extends ImperialSensor implements SpeedSensor {  // adapter


    @Override
    public void recordVelocity(double kmh) {
        int knots = (int) (kmh* 0.53);
        logSpeedKnots(knots , "N/A");

    }
}



class FlightControlSystem {
    private SpeedSensor sensor;

    public FlightControlSystem() {
        this.sensor = new ModernDigitalSensor();

    }

    public void monitorFlight(double kmh) {
        System.out.println("Flight System: Analyzing data...");
        sensor.recordVelocity(kmh);
    }

    public void replaceSensor(SpeedSensor newSensor) {
        this.sensor = newSensor;
    }
}

public class FlightControlApp { // client
    public static void main(String[] args) {
        FlightControlSystem cockpit = new FlightControlSystem();
        cockpit.monitorFlight(720.0);

        cockpit.replaceSensor(new ImperialSensorAdapter());
        cockpit.monitorFlight(720.0);

    }
}

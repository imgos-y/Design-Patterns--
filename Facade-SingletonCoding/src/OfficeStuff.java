import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OfficeStuff {
    public static void main(String[] args) {


        OfficeAssistantSystem oa =  OfficeAssistantSystem.getInstance();
        oa.startDeepWork("Eclipse");
        User dev = new User("Sisyphos");


        /*Workstation pc = new Workstation();
        ClimateControl ac = new ClimateControl();
        CoffeeMachine coffee = new CoffeeMachine();
        SmartBlinds blinds = new SmartBlinds();
        StatusIndicator status = new StatusIndicator();*/
       /* pc.powerOn();
        pc.launchIDE("IntelliJ IDEA");
        ac.setTemp(23);
        coffee.brew("Double Espresso");
        blinds.open();
        status.setDND(true);*/

        System.out.println("Productivity mode engaged for " + dev.getName() + "!");
    }
}

// --- Subsystem Classes ---

class Workstation {
    public void powerOn() {
        System.out.println("PC is booting up...");
    }

    public void launchIDE(String ide) {
        System.out.println("Launching " + ide + "...");
    }
}

class ClimateControl {
    public void setTemp(int temp) {
        System.out.println("AC set to " + temp + "Â°C.");
    }
}

class CoffeeMachine {
    public void brew(String type) {
        System.out.println("Brewing a hot " + type + "...");
    }
}

class SmartBlinds {
    public void open() {
        System.out.println("Blinds are opening...");
    }
}

class StatusIndicator {
    public void setDND(boolean active) {
        System.out.println("DND Light is " + (active ? "ON" : "OFF"));
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class OfficeAssistantSystem {

    private static volatile OfficeAssistantSystem instance1 = null;
    private static volatile OfficeAssistantSystem instance2 = null;
    private static final Lock lock = new ReentrantLock();


    private Workstation workstation = new Workstation();
    private ClimateControl climateControl = new ClimateControl();
    private SmartBlinds smartBlinds = new SmartBlinds();
    private CoffeeMachine coffeeMachine = new CoffeeMachine();
    private StatusIndicator statusIndicator = new StatusIndicator();


    private OfficeAssistantSystem() {

    }

    public void startDeepWork(String ideName) {
        workstation.launchIDE(ideName);
        workstation.powerOn();
        climateControl.setTemp(23);
        smartBlinds.open();
        coffeeMachine.brew("double esspresso");
        statusIndicator.setDND(true);


    }

    public static OfficeAssistantSystem getInstance() {
        if (instance1 == null) {
            return getInstance1();
        }
        if (instance2 == null) {
            return getInstance2();
        }

        Random rd = new Random();
        if (rd.nextInt(2) ==0){ ;
            return getInstance1();
        }else {
            return getInstance2();
        }
    }


    private static OfficeAssistantSystem getInstance2() {
        if (instance2 == null) {
            lock.lock();
            try {
                if (instance2 == null) {
                    instance2 = new OfficeAssistantSystem();

                }
            } finally {
                lock.unlock();
            }

        }
        return instance2;

    }
    private static OfficeAssistantSystem getInstance1() {
        if (instance1 == null) {
            lock.lock();
            try {
                if (instance1 == null) {
                    instance1 = new OfficeAssistantSystem();
                }
            }finally {
                lock.unlock();
            }
        }
        return instance1;
    }

}


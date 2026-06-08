import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BountyDispatcher bountyDispatcher = new BountyDispatcher();
        BountyHunter b1 = new Netrunner("netrunner " , bountyDispatcher);
        BountyHunter b2 = new StreetSamurai( " street samurai " , bountyDispatcher);

        bountyDispatcher.attach(b1);
        bountyDispatcher.attach(b2);

        bountyDispatcher.setTask("new task: find x person");

        bountyDispatcher.detach(b1);

        bountyDispatcher.setTask("new task: find y person");
        bountyDispatcher.updateFromSatellite("satelite sync complete");

    }
}

interface BountyHunter { // observer

    void update();
}

interface BountyHub { // subject
    void attach(BountyHunter o);
    void detach(BountyHunter o);
    void notifyObservers();


}

class BountyDispatcher implements BountyHub {

    private List<BountyHunter> observers = new ArrayList<>();
    private String task;

    public void setTask ( String task) {
        this.task = task;
        notifyObservers(); // important detail.

    }

    public void updateFromSatellite(String data) {
        this.task = data; // state update

        System.out.println("Satellite update received: " + data);
    }

    public String getTask() {
        return task;
    }

    @Override
    public void attach(BountyHunter o) {
        observers.add(o);


    }

    @Override
    public void detach(BountyHunter o) {
        observers.remove(o);

    }

    @Override
    public void notifyObservers() {
        for (BountyHunter o : observers) {
            o.update();
        }

    }
}

class StreetSamurai implements BountyHunter {

    private String name;
    private BountyDispatcher bountyDispatcher;

    public StreetSamurai (String name, BountyDispatcher bountyDispatcher) {
        this.name = name;
        this.bountyDispatcher = bountyDispatcher;
    }


    @Override
    public void update() {
        System.out.println( name + " notified. The task: " + bountyDispatcher.getTask());
    }
}

class Netrunner implements BountyHunter {

    private String name;
    private BountyDispatcher bountyDispatcher;

    public Netrunner (String name, BountyDispatcher bountyDispatcher) {
        this.name = name;
        this.bountyDispatcher = bountyDispatcher;
    }



    @Override
    public void update() {
        System.out.println( name + " notified. The task: " + bountyDispatcher.getTask());

    }
}


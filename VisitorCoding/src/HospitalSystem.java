import java.util.ArrayList;

public class HospitalSystem {
    public static void main(String[] args) {

        HospitalManagementSystem h = new HospitalManagementSystem();

        h.addPatient(new NewbornInfants());
        h.addPatient(new ElderlyResidents());
        h.addPatient(new PostSurgeryAdults());

        h.accept(new Billing());
        h.accept(new ResearchDivision());

    }
}


interface Patient{ // element
    public void accept(HospitalVisitor visitor);


}
class NewbornInfants implements Patient{

    @Override
    public void accept(HospitalVisitor visitor) {
        visitor.visit(this);

    }
}
class PostSurgeryAdults implements Patient{

    @Override
    public void accept(HospitalVisitor visitor) {
        visitor.visit(this);

    }
}
class  ElderlyResidents  implements Patient{
    @Override
    public void accept(HospitalVisitor visitor) {
        visitor.visit(this);
    }
}

interface HospitalVisitor { // visitor

    public void visit(NewbornInfants newbornInfants);
    public void visit(PostSurgeryAdults postSurgeryAdults);
    public void visit(ElderlyResidents elderlyResidents);


}
class Billing implements HospitalVisitor{
    @Override
    public void visit(NewbornInfants newbornInfants) {
        System.out.println("Billing: Newborn incubator cost calculated.");

    }

    @Override
    public void visit(PostSurgeryAdults postSurgeryAdults) {
        System.out.println("Billing: Post surgery adults calculated.");

    }

    @Override
    public void visit(ElderlyResidents elderlyResidents) {
        System.out.println("Billing: Elderly resident cost calculated.");

    }
}
class ResearchDivision implements HospitalVisitor{

    @Override
    public void visit(NewbornInfants newbornInfants) {
        System.out.println("Research: Newborn sleep pattern data collected.");

    }

    @Override
    public void visit(PostSurgeryAdults postSurgeryAdults) {
        System.out.println("Research: Post sleep pattern data collected.");

    }

    @Override
    public void visit(ElderlyResidents elderlyResidents) {
        System.out.println("Research: Elder sleep pattern data collected.");

    }
}

class HospitalManagementSystem { // object structure
    private ArrayList<Patient> patients = new ArrayList<>();

    public void addPatient ( Patient patient){
        patients.add(patient);
    }
    public void accept(HospitalVisitor visitor) {
        for (Patient p : patients) {
            p.accept(visitor);
        }
    }




}
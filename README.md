# Design-Patterns--
This repo about design patterns implementation for java.

-Façade Pattern: 
* Goal: To simplify a complex subsystem.

How it works: The OfficeAssistantSystem class acts as a single interface (a "front") for many small office devices like the PC, Coffee Machine, AC, and Smart Blinds. Instead of talking to every device one by one, the user just calls startDeepWork().
Multiton (Limited Singleton) Pattern:
Goal: To control the number of instances (objects) created.
How it works: This system allows at most two instances of the office assistant.
Logic: * It uses Lazy Instantiation (the object is created only when needed).
It is Thread-Safe using "Double-Checked Locking" and the volatile keyword to prevent errors in multi-threaded environments.
Smart Selection: The first call creates Instance 1. The second call creates Instance 2. After both are created, the system chooses one of them randomly for every new request.

- Template Method Pattern:

Goal: To define a fixed sequence of steps for a process, while allowing subclasses to customize some of those steps.
How it works: The BankingSystem abstract class defines the transaction pipeline inside the processTransaction() method. This method is final, so no subclass can change the order of steps. Steps that are the same for all transactions (like verifyIdentity() and checkBalance()) are implemented directly in the base class. Steps that differ between transactions (like executeTransfer()) are declared as abstract, so each subclass provides its own version. Hook Method: amlHook() is an empty method in the base class. CashWithdrawal ignores it, but InternationalTransfer overrides it to add an AML fraud screening step between identity verification and balance check.

- Adapter Pattern:
  
Goal: To allow two incompatible interfaces to work together without modifying existing code.
How it works: The FlightControlSystem is built to work only with the SpeedSensor interface, which expects speed as a decimal in km/h via recordVelocity(double kmh). The legacy ImperialSensor cannot be used directly because it requires speed as an integer in knots and an atmospheric pressure string via logSpeedKnots(int knots, String status).
The ImperialSensorAdapter bridges this gap. It extends ImperialSensor (Class Adapter approach) and implements SpeedSensor, so it can be used anywhere a SpeedSensor is expected. Inside recordVelocity(), it converts km/h to knots and calls the legacy method — the client never knows the difference. Key design decision: FlightControlSystem holds a SpeedSensor field (interface type, not concrete), so any sensor — modern or adapted legacy — can be plugged in at runtime via replaceSensor().

- Observer Pattern
  
Goal: To define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
How it works: The BountyDispatcher acts as the central hub (ConcreteSubject) that maintains a list of connected BountyHunter observers. When a new bounty is entered via setTask(), the Dispatcher automatically notifies every hunter on its list. The Netrunner and StreetSamurai classes are ConcreteObservers — they both receive the same notification but can react differently.
Hunters can connect and disconnect at any time via attach() and detach(). To simulate an injured hunter, the Netrunner is detached mid-simulation and receives no further notifications. The updateFromSatellite() method demonstrates that not every state change needs to trigger a notification — internal updates can happen silently without alerting any observers.

- Visitor Pattern

Goal: To perform different operations on a group of related objects without modifying their classes.
How it works: The HospitalManagementSystem acts as the central ObjectStructure that maintains a list of all active patient records — NewbornInfants, PostSurgeryAdults, and ElderlyResidents. Each patient class implements the Patient interface and only knows how to accept() a visitor by calling visitor.visit(this).
The Billing and ResearchDivision classes are ConcreteVisitors. When passed through the system, each applies its own logic per patient type — the Billing department calculates equipment costs while the Research Division collects anonymous recovery statistics. Neither operation touches the patient records themselves.
Key design decision: Adding a new administrative or medical service requires only a new Visitor class — the core patient records and the HospitalManagementSystem never need to be modified.
Trade-off: Introducing a new patient type like CriticalCarePatient requires adding a new visit() method to the HospitalVisitor interface and updating every existing ConcreteVisitor — this is the known liability of the Visitor pattern.

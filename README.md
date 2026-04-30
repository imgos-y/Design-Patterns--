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

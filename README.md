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

# Java socket playground
Trying to learn the client-server approach in `Java` for a next university exam.

An `Echo Reply` service is implemented using different approaches:

-   **Iterative** - TCP iterative server
-   **Multiprocess** - TCP multiprocess server
-   **Mutex** - TCP multiprocess server with a `shared resource`
-   **UDP** - UDP connection



## How to use

Compile with `javac *.java` and launch server and client(s) on different terminal windows.
Note that you have to pass the **server port** to the client using `java Client XXXXX`.
So, first launch the **server**, copy the **port** and run the **client**.

In the case of **Multiprocess** and **Mutex** you have to launch `Main` instead of `Server`.

In case of **UDP** you don't need to specify the port. 
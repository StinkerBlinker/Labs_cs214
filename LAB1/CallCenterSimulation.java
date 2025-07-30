import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Caller {
    private String name;
    private String phoneNumber;
    private String message;

    public Caller(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.message = "";
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Caller: " + name + " (" + phoneNumber + ")" +
                (message.isEmpty() ? "" : " Message: " + message);
    }
}

class CallCenter {
    private Queue<Caller> callQueue;
    private int consultantCount;

    public CallCenter(int consultantCount) {
        this.callQueue = new LinkedList<>();
        this.consultantCount = consultantCount;
    }

    public void addCaller(String name, String phoneNumber) {
        Caller caller = new Caller(name, phoneNumber);
        callQueue.add(caller);
        System.out.println("Added " + caller + " to queue. Current queue size: " + callQueue.size());
    }

    public void processNextCall() {
        if (callQueue.isEmpty()) {
            System.out.println("No callers in queue.");
            return;
        }
        Caller caller = callQueue.poll();
        System.out.println("Processing call for " + caller);
        System.out.println("Remaining callers in queue: " + callQueue.size());
    }

    public void hangUp(String name, String message) {
        for (Caller caller : callQueue) {
            if (caller.getName().equalsIgnoreCase(name)) {
                caller.setMessage(message);
                callQueue.remove(caller);
                System.out.println(name + " hung up with message: " + message);
                System.out.println("Current queue size: " + callQueue.size());
                return;
            }
        }
        System.out.println("Caller " + name + " not found in queue.");
    }

    public void displayQueueStatus() {
        if (callQueue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Current queue (" + callQueue.size() + " callers):");
        for (Caller caller : callQueue) {
            System.out.println(caller);
        }
    }
}

public class CallCenterSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CallCenter callCenter = new CallCenter(3); // Assuming 3 consultants

        while (true) {
            System.out.println("\nCall Center Simulation Menu:");
            System.out.println("1. Add new caller");
            System.out.println("2. Process next call");
            System.out.println("3. Caller hang up");
            System.out.println("4. Display queue status");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter caller name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    callCenter.addCaller(name, phone);
                    break;
                case 2:
                    callCenter.processNextCall();
                    break;
                case 3:
                    System.out.print("Enter caller name: ");
                    String hangUpName = scanner.nextLine();
                    System.out.print("Enter message: ");
                    String message = scanner.nextLine();
                    callCenter.hangUp(hangUpName, message);
                    break;
                case 4:
                    callCenter.displayQueueStatus();
                    break;
                case 5:
                    System.out.println("Exiting simulation...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
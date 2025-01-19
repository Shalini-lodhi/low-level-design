//package PrinterSettings;

import java.util.*;

// Printer Class
class Printer {
    private String name;
    private int port;
    private String ipAddress;
    private String outputFormat;
    private boolean isDefault;

    public Printer(String name, int port, String ipAddress, String outputFormat, boolean isDefault) {
        this.name = name;
        this.port = port;
        this.ipAddress = ipAddress;
        this.outputFormat = outputFormat;
        this.isDefault = isDefault;
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getPort() { return port; }
    public String getIpAddress() { return ipAddress; }
    public String getOutputFormat() { return outputFormat; }
    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean isDefault) { this.isDefault = isDefault; }
}

// PrinterManager Class
class PrinterManager {
    private static final int MAX_PRINTERS = 5;
    private List<Printer> printers = new ArrayList<>();

    public List<Printer> getPrinters() {
        return printers;
    }

    public String addPrinter(String name, int port, String ipAddress, String outputFormat) {
        if (printers.size() >= MAX_PRINTERS) {
            return "Cannot add more than " + MAX_PRINTERS + " printers.";
        }
        for (Printer printer : printers) {
            if (printer.getName().equals(name)) {
                return printer.getName() + ": Printer with the same name already exists.";
            }
        }
        Printer newPrinter = new Printer(name, port, ipAddress, outputFormat, true);
        for (Printer printer : printers) {
            printer.setDefault(false); // Remove default status from other printers
        }
        printers.add(newPrinter);
        return newPrinter.getName() + ": added successfully and set as default printer.";
    }

    public String deletePrinter(String name) {
        for (Printer printer : printers) {
            if (printer.getName().equals(name)) {
                if (printer.isDefault()) {
                    return printer.getName() + ": Cannot delete the default printer.";
                }
                printers.remove(printer);
                return printer.getName() + ": Printer deleted successfully.";
            }
        }
        return "Printer not found.";
    }

    public String setDefaultPrinter(String name) {
        for (Printer printer : printers) {
            if (printer.getName().equals(name)) {
                for (Printer p : printers) {
                    p.setDefault(false);
                }
                printer.setDefault(true);
                return printer.getName() + ": Printer set as default.";
            }
        }
        return "Printer not found.";
    }
}

// Main Class
public class PrinterSettingsApp {
    public static void main(String[] args) {
        PrinterManager manager = new PrinterManager();

        System.out.println(manager.addPrinter("Printer1", 9100, "192.168.1.100", "PDF"));
        System.out.println(manager.addPrinter("Printer2", 9101, "192.168.1.101", "Text"));
        System.out.println(manager.addPrinter("Printer3", 9101, "192.168.1.102", "PDF"));
        System.out.println(manager.addPrinter("Printer4", 9101, "192.168.1.104", "PDF"));
        System.out.println(manager.addPrinter("Printer5", 9101, "192.168.1.102", "PDF"));
        System.out.println(manager.addPrinter("Printer6", 9101, "192.168.1.104", "PDF"));
        System.out.println(manager.setDefaultPrinter("Printer2"));
        System.out.println(manager.setDefaultPrinter("Printer3"));
        System.out.println(manager.deletePrinter("Printer1"));
        System.out.println(manager.deletePrinter("Printer2")); // Attempting to delete default printer

        for (Printer printer : manager.getPrinters()) {
            System.out.println("Printer: " + printer.getName() + ", Default: " + printer.isDefault());
        }
    }
}

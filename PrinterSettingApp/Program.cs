using System;
using System.Management;
using System.Runtime.InteropServices;

class Program
{
    [DllImport("winspool.drv", SetLastError = true, CharSet = CharSet.Auto)]
    public static extern bool SetDefaultPrinter(string printerName);

    static void Main()
    {
        while (true)
        {
            Console.Clear();
            Console.WriteLine("===== Printer Settings Manager =====");
            Console.WriteLine("1. View Printers");
            Console.WriteLine("2. Add Printer (Mock)");
            Console.WriteLine("3. Delete Printer");
            Console.WriteLine("4. Set Default Printer");
            Console.WriteLine("5. Exit");
            Console.Write("Choose an option: ");

            switch (Console.ReadLine())
            {
                case "1":
                    ViewPrinters();
                    break;
                case "2":
                    AddPrinter(); // Mock function
                    break;
                case "3":
                    DeletePrinter();
                    break;
                case "4":
                    SetDefaultPrinter();
                    break;
                case "5":
                    return;
                default:
                    Console.WriteLine("Invalid option! Press Enter to try again...");
                    Console.ReadLine();
                    break;
            }
        }
    }

    static void ViewPrinters()
    {
        Console.WriteLine("\nAvailable Printers:");

        ManagementObjectSearcher searcher = new ManagementObjectSearcher("SELECT * FROM Win32_Printer");
        foreach (ManagementObject printer in searcher.Get())
        {
            string name = printer["Name"]?.ToString();
            bool isDefault = printer["Default"] != null && (bool)printer["Default"];

            Console.WriteLine($"- {name} (Default: {isDefault})");
        }

        Console.WriteLine("\nPress Enter to continue...");
        Console.ReadLine();
    }

    static void AddPrinter()
    {
        Console.Write("\nEnter the new printer name: ");
        string printerName = Console.ReadLine();
        Console.WriteLine($"[Mock] Adding printer '{printerName}'...");
        
        // In a real scenario, use Windows API or WMI to add a printer.
        
        Console.WriteLine($"[Mock] Setting '{printerName}' as default...");
        SetDefaultPrinter(printerName);

        Console.WriteLine("\nPress Enter to continue...");
        Console.ReadLine();
    }

    static void DeletePrinter()
    {
        Console.Write("\nEnter the printer name to delete: ");
        string printerName = Console.ReadLine();

        ManagementObjectSearcher searcher = new ManagementObjectSearcher("SELECT * FROM Win32_Printer");
        foreach (ManagementObject printer in searcher.Get())
        {
            if (printer["Name"].ToString().Equals(printerName, StringComparison.OrdinalIgnoreCase))
            {
                if ((bool)printer["Default"])
                {
                    Console.WriteLine("Cannot delete the default printer!");
                }
                else
                {
                    Console.WriteLine($"[Mock] Deleting printer '{printerName}'...");
                    // Actual deletion requires Windows API (not implemented here).
                }
                break;
            }
        }

        Console.WriteLine("\nPress Enter to continue...");
        Console.ReadLine();
    }

    static void SetDefaultPrinter()
    {
        Console.Write("\nEnter the printer name to set as default: ");
        string printerName = Console.ReadLine();

        if (SetDefaultPrinter(printerName))
        {
            Console.WriteLine($"Successfully set '{printerName}' as the default printer.");
        }
        else
        {
            Console.WriteLine("Failed to set the default printer. Make sure you have the right permissions.");
        }

        Console.WriteLine("\nPress Enter to continue...");
        Console.ReadLine();
    }
}

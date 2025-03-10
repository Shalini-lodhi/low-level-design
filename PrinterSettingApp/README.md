## **Explanation of PrinterSettingApp in .NET Framework (WPF)**

The **PrinterSettingApp** is a **console-based application** that interacts with Windows printers. Since you originally faced issues using `System.Printing` in **.NET Core**, the best way to implement it in a **WPF .NET Framework project** is to use **System.Printing**, which is fully supported in .NET Framework (not in .NET Core or .NET 5+).  

---

## **💡 Why Use .NET Framework for This?**
1. **`System.Printing` is only available in .NET Framework**  
   - It provides better **print queue management** and works with **WPF** applications.
2. **Supports WMI (`System.Management`)** for deeper system integration.
3. **Compatible with `winspool.drv`** (Windows printing DLL).

---

## **📌 Breakdown of the Code and Libraries Used**

### **1️⃣ Using System.Printing for Printer Management**
```csharp
using System;
using System.Printing;
using System.Runtime.InteropServices;
```
- **`System.Printing`** → Provides access to print servers, print queues, and printer properties.
- **`System.Runtime.InteropServices`** → Required for calling `winspool.drv` (Windows printer API).

### **2️⃣ Setting the Default Printer (Windows API - `winspool.drv`)**
```csharp
[DllImport("winspool.drv", SetLastError = true, CharSet = CharSet.Auto)]
public static extern bool SetDefaultPrinter(string printerName);
```
- Uses **Windows DLL (`winspool.drv`)** to set the **default printer**.
- This is necessary because `.NET Framework` does **not** provide built-in methods to change the default printer.

### **3️⃣ Viewing Installed Printers**
```csharp
static void ViewPrinters()
{
    Console.WriteLine("\nAvailable Printers:");
    using (LocalPrintServer printServer = new LocalPrintServer())
    {
        PrintQueueCollection printers = printServer.GetPrintQueues();
        foreach (PrintQueue printer in printers)
        {
            Console.WriteLine($"- {printer.FullName} (Default: {printer.IsDefault})");
        }
    }
    Console.WriteLine("\nPress Enter to continue...");
    Console.ReadLine();
}
```
- **`LocalPrintServer`**: Represents the local print server (manages installed printers).
- **`GetPrintQueues()`**: Fetches a list of all **installed printers**.
- **`PrintQueue.FullName`**: Gets the printer name.
- **`PrintQueue.IsDefault`**: Checks if the printer is set as default.

### **4️⃣ Adding a Printer (Mock Implementation)**
```csharp
static void AddPrinter()
{
    Console.Write("\nEnter the new printer name: ");
    string printerName = Console.ReadLine();
    Console.WriteLine($"[Mock] Adding printer '{printerName}'...");
    
    Console.WriteLine($"[Mock] Setting '{printerName}' as default...");
    SetDefaultPrinter(printerName);

    Console.WriteLine("\nPress Enter to continue...");
    Console.ReadLine();
}
```
- In a **real implementation**, adding a printer requires either:
  - **Windows API (`Win32_Printer`)**
  - **PowerShell scripting**
  - **Manually adding via Windows UI**

### **5️⃣ Deleting a Printer**
```csharp
static void DeletePrinter()
{
    Console.Write("\nEnter the printer name to delete: ");
    string printerName = Console.ReadLine();

    using (LocalPrintServer printServer = new LocalPrintServer())
    {
        PrintQueueCollection printers = printServer.GetPrintQueues();
        PrintQueue printerToDelete = printers.FirstOrDefault(p => p.FullName.Equals(printerName, StringComparison.OrdinalIgnoreCase));

        if (printerToDelete == null)
        {
            Console.WriteLine("Printer not found.");
        }
        else if (printerToDelete.IsDefault)
        {
            Console.WriteLine("Cannot delete the default printer!");
        }
        else
        {
            Console.WriteLine($"[Mock] Deleting printer '{printerName}'...");
            // Actual deletion requires Windows API (not implemented here).
        }
    }

    Console.WriteLine("\nPress Enter to continue...");
    Console.ReadLine();
}
```
- **Fetches the list of installed printers**.
- **Checks if the printer exists**.
- **Prevents deletion of the default printer**.
- **Deletion is a mock operation** (actual deletion requires Windows API).

### **6️⃣ Setting a Default Printer**
```csharp
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
```
- Uses **Windows API** to change the default printer.

---

## **📌 Why These Libraries Were Used?**
| **Library**                 | **Why It Was Used?** |
|-----------------------------|---------------------|
| `System.Printing`           | To list printers and manage print queues. |
| `System.Runtime.InteropServices` | To interact with Windows printing API (`winspool.drv`). |
| `System.Management` (alternative) | Used in .NET Core for WMI queries (`Win32_Printer`). |
| `winspool.drv` (Windows DLL) | To set the default printer, since .NET does not have built-in support. |

---

## **💡 Summary**
- **.NET Framework (WPF)** supports **System.Printing**, which makes it a better choice for printer management.  
- **WMI (`System.Management`)** is needed for **real printer addition/deletion**.  
- **Windows API (`winspool.drv`)** is used to **set default printers**.  
- The **current implementation is functional** but **adding/deleting printers requires admin permissions**.  

---

## **🚀 Next Steps**
- Would you like a **WPF UI version** of this?  
- Need **real printer addition/removal** using **Windows API or PowerShell**?  

Let me know how you'd like to proceed! 😊
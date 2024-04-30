package org.pluralsight;

import org.pluralsight.templates.LogEntry;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Logger
{
    private final String LOG_DIRECTORY_PATH = "files";
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("kk:mm:ss");
    boolean firstLine = true;

    private String fileName;
    private String filePath;

    public Logger(String fileName)
    {
        File directory = new File(LOG_DIRECTORY_PATH);
        if(!directory.exists())
        {
            directory.mkdir();
        }

        this.fileName = fileName;
        this.filePath = LOG_DIRECTORY_PATH + "/" + fileName;
        if(!this.filePath.toLowerCase().endsWith(".csv"))
        {
            this.filePath += ".csv";
        }
    }


    public void logMessage(String description, String vendor, double amount)
    {

        File logFile = new File(filePath);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        try(FileWriter fileWriter = new FileWriter(logFile, true);
            PrintWriter writer = new PrintWriter(fileWriter)
        )
        {
            if (logFile.length() == 0) {

                writer.write("date|time|description|vendor|amount\n");
            }

            writer.write(String.format("%s | %s | %s | %s | %.2f\n", date.format(DATE_FORMAT), time.format(TIME_FORMAT),
                    description,vendor,amount));
        }
        catch (IOException ex)
        {
            System.out.println("Failed to enter data.");
        }

    }

    public ArrayList<LogEntry> readEntries()
    {
        ArrayList<LogEntry> logEntries = new ArrayList<>();

        File logFile = new File("files/transactions.csv");

        try(FileReader fileReader = new FileReader(logFile);
            Scanner scanner = new Scanner(fileReader)
        )
        {
            firstLine = true;
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\\s\\|\\s");
                 if (firstLine)
                 {
                     firstLine = false;
                      continue;
                 }
                LocalDate date = LocalDate.parse(lineScanner.next(), DATE_FORMAT);
                LocalTime time = LocalTime.parse(lineScanner.next(), TIME_FORMAT);
                String description = lineScanner.next();
                String vendor = lineScanner.next();
                double amount = lineScanner.nextDouble();

                LogEntry logEntry = new LogEntry(date, time, description, vendor, amount);

                logEntries.add(logEntry);

            }

        }
        catch (IOException ex)
        {

        }
        return logEntries;
    }

}

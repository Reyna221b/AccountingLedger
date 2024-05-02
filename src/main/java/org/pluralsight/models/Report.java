package org.pluralsight.models;

import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Report
{
        private static final LocalDate currentDate = LocalDate.now();


        private static List<LogEntry> filterEntriesByDate(List<LogEntry> logEntryList, LocalDate start, LocalDate end)
        {
                return logEntryList.stream().filter(entry-> !entry.getDate().isBefore(start)
                        &&!entry.getDate().isAfter(end)).collect(Collectors.toList());
        }

        public static List<LogEntry> monthToDate(List<LogEntry> logEntries)
        {
                LocalDate firstOfMonth = currentDate.withDayOfMonth(1);
                return filterEntriesByDate(logEntries, firstOfMonth, currentDate);
        }

        public static List<LogEntry> previousMonth(List<LogEntry> logEntries)
        {
                LocalDate startPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
                LocalDate endPreviousMonth = currentDate.withDayOfMonth(1).minusDays(1);
                return filterEntriesByDate(logEntries, startPreviousMonth, endPreviousMonth);
        }

        public static List<LogEntry> yearToDate(List<LogEntry>logEntries)
        {
                LocalDate startOfYear = LocalDate.of(currentDate.getYear(), 1, 1);
                return filterEntriesByDate(logEntries, startOfYear, currentDate);
        }

        public static List<LogEntry> previousYear(List<LogEntry>logEntries)
        {
                LocalDate startPreviousYear = LocalDate.of(currentDate.getYear()-1, 1,1);
                LocalDate endPreviousYear = LocalDate.of(currentDate.getYear()-1, 12, 31);
                return filterEntriesByDate(logEntries, startPreviousYear, endPreviousYear);
        }

        public static List<LogEntry> searchByVendor(List<LogEntry> logEntries, String vendor)
        {
                return logEntries.stream().filter(entry-> entry.getVendor().equalsIgnoreCase(vendor))
                        .collect(Collectors.toList());
        }


        public static void reportResults(List<LogEntry>logEntries)
        {
                Collections.reverse(logEntries);
                for(LogEntry entry : logEntries){

                        System.out.println(entry);
                }
                if(logEntries.isEmpty())
                {
                        System.out.println("No records found.");
                }
        }

}

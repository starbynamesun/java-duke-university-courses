
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log.txt");
        //la.readFile("data/short-test_log");
        la.printAll();
        System.out.println("Higher than num: ");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        //la.readFile("data/weblog-short_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }
    
    
    public void testVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        ArrayList<String> IPs = la.uniqueIPVisitsOnDay("Sep 24");
        for(String ip : IPs) {
            System.out.println(ip);
        }
        System.out.println("Has " + IPs.size() + " IPs");
    }

    public void testUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        //la.readFile("data/short-test_log");
        int count = la.countUniqueIPsInRange(200, 299); //4
        //int count = la.countUniqueIPsInRange(300, 399); //2
        System.out.println(count);
    }
    
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("Most number visits per IP:" + la.mostNumberVisitsByIP(counts));
        System.out.println("List of IPs: " + la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log.txt");
        HashMap<String, ArrayList<String>> daysIPs = la.iPsForDays();
        System.out.println(daysIPs);
        System.out.println("The day most visited: " + la.dayWithMostIPVisits(daysIPs));
        
        System.out.println("There are Ips:" + la.iPsWithMostVisitsOnDay(daysIPs, "Sep 29"));
    }
}

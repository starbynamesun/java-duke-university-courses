
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
        
        FileResource fr = new FileResource(filename);
         
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
        
    public void printAll() {
        for (LogEntry le : records) {
             System.out.println(le);
        }
    }
     
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for (LogEntry le: records) {
        
            String ipAddr = le.getIpAddress();
            
            if (!uniqueIPs.contains(ipAddr)) {
            // add ipAddr to uniqueIps
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size(); 
    }
    
    
    public void printAllHigherThanNum(int num) {
    
        for (LogEntry le : records) {
            if(le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }
    
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        //String someday in the format “MMM DD” (examples are “Dec 05” and “Apr 22”). 
    
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for (LogEntry le: records) {
            
            String date = le.getAccessTime().toString();
            String ipAddr = le.getIpAddress();
            
            if (date.indexOf(someday) != -1 && !uniqueIPs.contains(ipAddr)) {
                // add ipAddr to uniqueIps
                uniqueIPs.add(ipAddr);
            }
        }
        
        return uniqueIPs;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        
        int count = 0;
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records) {
            int code = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if((code >= low && code <= high) && 
            !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap <String, Integer> counts = new HashMap<String, Integer>();
        
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            
            if (!counts.containsKey(ip)) {
                counts.put(ip,1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        
        return counts;
    }
    
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
   
        int maxNumber = 0;
        
        for (String ip : counts.keySet()) {
            if(counts.get(ip) > maxNumber) {
                maxNumber = counts.get(ip);
            }
        }
        //For example, the call mostNumberVisitsByIP on a HashMap formed using
        //the file weblog3-short_log returns 3.
        return maxNumber;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
    
        ArrayList<String> maxNumIPs = new ArrayList<String>();
        
        int maxValue = mostNumberVisitsByIP(counts);
        for(String key : counts.keySet()) {
            if(counts.get(key) == maxValue){
                if(!maxNumIPs.contains(key)) {
                    maxNumIPs.add(key);
                }
            }
        }
    
        return maxNumIPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        
        HashMap <String, ArrayList<String>> daysIPs = new HashMap<String, ArrayList<String>>();
        
        for (LogEntry le: records) {
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddr = le.getIpAddress();
            ArrayList<String> ip;
            
            if(!daysIPs.containsKey(date)) {
                ip = new ArrayList<String>();
                ip.add(ipAddr);
                daysIPs.put(date, ip);
            } else {
                ip = daysIPs.get(date);
                ip.add(ipAddr);
                daysIPs.put(date, ip);
            }
        }
        return daysIPs;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIPs) {
    
        String day = "";
        int size = 0;
        
        for (String key : daysIPs.keySet()) {
            
            if(daysIPs.get(key).size() > size) {
                size = daysIPs.get(key).size();
                day = key;
            }
        }
    
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIPs, String day) {
    
        if(daysIPs.containsKey(day)) {
            HashMap<String, Integer> counts = new HashMap<>();
            ArrayList<String> ipsOnDay = daysIPs.get(day);
            
            for (String ip : ipsOnDay) {
                counts.put(ip, counts.getOrDefault(ip, 0) + 1);
            }
            return iPsMostVisits(counts);
            
        } else {
            return new ArrayList<>(); // Return an empty list if the day is not found
        }
    }
}




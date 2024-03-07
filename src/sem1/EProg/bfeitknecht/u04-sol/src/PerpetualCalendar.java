import java.util.Scanner;

/* 
 * Author: Maximiliana Muster
 * for Einfuehrung in die Programmierung
 * 
 * This program computes the weekday for any date after 1.1.1900.
 */
public class PerpetualCalendar {
    
    public static void main(String[] args) {
        // input the date
        Scanner scanner = new Scanner(System.in);
        System.out.print("Day? ");
        int day = scanner.nextInt();
        System.out.print("Month? ");
        int month = scanner.nextInt();
        System.out.print("Year (after 1900)? ");
        int year = scanner.nextInt();
        
        // check for valid input and print error
        if(!isValidDate(day, month, year)) {
            System.out.println("Invalid date!");
        }
        else {
            // compute the weekday
            int weekday = countDays(day, month, year) % 7;
            
            String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday",
                    "Friday", "Saturday", "Sunday"};
            
            // output the weekday
            System.out.println("The " + day + "." + month + "." + year
                    + " is a " + weekdays[weekday]);
        }
    }
    
    /**
     * Returns whether the given values represent a valid date
     */
    static boolean isValidDate(int day, int month, int year) {
        return year >= 1900 && month >= 1 && month <= 12
                && day >= 1 && day <= countDaysInMonth(month, year);
    }
    
    /**
     * Returns the number of days between January 1, 1900 and the given date
     */
    static int countDays(int day, int month, int year) {
        int result = 0;
        
        // sum up all days from 1.1.1900 to 1.1.[year]
        for(int y = 1900; y < year; y++)
            result += countDaysInYear(y);
        
        // sum up all days from 1.1.[year] to 1.[month].[year]
        for(int m = 1; m < month; m++)
            result += countDaysInMonth(m, year);
        
        // add days in [month]
        result += day - 1;
        return result;
    }
    
    /**
     * Returns the number of days in the given month of the given year
     */
    static int countDaysInMonth(int month, int year) {
        if(month == 2 && !isLeapYear(year))
            return 28;
        else if(month == 2)
            return 29;
        else if(month < 8 && month % 2 == 0 || month >= 8 && month % 2 == 1)
            return 30;
        else
            return 31;
    }
    
    /**
     * Returns the number of days in the given year
     */
    static int countDaysInYear(int year) {
        if(isLeapYear(year))
            return 366;
        else
            return 365;
    }
    
    /**
     * Returns whether the given year was a leap year
     */
    static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}

package com.lea.Utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class ConsoleReader {

    private Scanner scanner;
    private DateUtil dateUtil;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
        dateUtil = new DateUtil();
    }

    public String readString(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
        } while (input == null || input.trim().equals(""));
        return input.trim();
    }

    public String readStringWithPrefix(String message, String prefix) {
        String input;
        do {
            input = readString(message);
        } while (!input.startsWith(prefix));
        return input;
    }

    public Date readValidDate(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
        } while(!isDateString(input));
        try {
            return dateUtil.getDateFromString(input);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isDateString(String string) {
        try {
            dateUtil.getDateFromString(string);
            if (!dateUtil.validateDateStringNotInFuture(string)) {
                System.out.println("Date cannot be in the future.");
                return false;
            }
            if (!dateUtil.validateDateStringNotBefore2000(string)) {
                System.out.println("Date cannot be before 2000");
                return false;
            }
            return true;
        } catch (ParseException e) {
            System.out.println(String.format("Invalid date format: %s", string));
            return false;
        }
    }

    public int readInt(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
        } while (!isInteger(input));
        return Integer.parseInt(input);
    }

    public int readNonNegativeInt(String message) {
        int input;
        do {
            input = readInt(message);
        } while (input < 0);
        return input;
    }

    private boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

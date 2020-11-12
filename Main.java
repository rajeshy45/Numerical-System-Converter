package converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int sourceRadix;
            try{
                sourceRadix = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("error");
                break;
            }
            if (sourceRadix < 1 || sourceRadix > 36) {
                System.out.println("error");
                break;
            }
            String sourceNumber = scanner.next();
            int targetRadix;
            try {
                targetRadix = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("error");
                break;
            }
            if (targetRadix < 1 || targetRadix > 36) {
                System.out.println("error");
                break;
            }
            String targetNumber;

            String integerPart;
            String fractionalPart = "";

            if (sourceNumber.contains(".")) {
                String[] parts = sourceNumber.split("\\.");
                integerPart = parts[0];
                fractionalPart = parts[1];
            } else {
                integerPart = sourceNumber;
            }

            double integerDecimal = 0.0;
            double fractionalDecimal = 0.0;
            double decimal = 0.0;
            String targetInteger;
            StringBuilder targetFractional = new StringBuilder();

            if (sourceRadix == 1) {
                int len = integerPart.length();
                for (int i = 0; i < len; i++) {
                    decimal = decimal + Character.getNumericValue(integerPart.charAt(i));
                }
            } else {
                integerDecimal = Integer.parseInt(integerPart, sourceRadix);
                int j = 1;
                for (int i = 0; i < fractionalPart.length(); i++) {
                    fractionalDecimal += Character.getNumericValue(fractionalPart.charAt(i)) / Math.pow(sourceRadix, j);
                    j += 1;
                }
                decimal = integerDecimal + fractionalDecimal;
            }

            if (targetRadix == 1) {
                long number = 0;
                long add = 1;
                for (int i = 0; i < integerDecimal; i++) {
                    number += add;
                    add *= 10;
                }
                targetNumber = Long.toString(number);
            } else {
                int integer = (int) decimal;
                double fractional = decimal - (int) decimal;
                targetInteger = Integer.toString(integer, targetRadix);
                int number;
                for (int i = 0; i < 5; i++) {
                    number = (int) (fractional * targetRadix);
                    fractional = fractional * targetRadix - number;
                    if (number > 9) {
                        targetFractional.append(Character.forDigit(number, 36));
                    } else {
                        targetFractional.append(number);
                    }
                }
                targetNumber = targetInteger + "." + targetFractional;
            }
            System.out.println(targetNumber);
            break;
        }
    }
}

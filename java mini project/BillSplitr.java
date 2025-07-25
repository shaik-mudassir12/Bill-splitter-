import java.io.FileWriter;
import java.io.IOException;
import java.lang.Exception;
import java.util.Scanner;

public class BillSplitr {
    void BillSplitr() {
        System.out.print("<====BILL DETAILS====>\n\n");
    }

    public static void main(String[] args) {
        BillSplitr b = new BillSplitr();
        b.BillSplitr();

        double bAmt = 0.0;
        double tipAmt = 0.0;

        System.out.print("Enter Bill Amount Rs.");
        Scanner sc = new Scanner(System.in);
        try {
            bAmt = sc.nextDouble();

            System.out.print("Do you want to add a tip? (yes/no): ");
            String tipChoice = sc.next().toLowerCase();

            if (tipChoice.equals("yes")) {
                System.out.print("Enter Tip Amount Rs.");
                tipAmt = sc.nextDouble();
            }

            System.out.print("Split Bill into ");
            int plp = sc.nextInt();

            double totAmt = (bAmt + tipAmt);    // with tip
            double totAmt2 = (bAmt);            // without tip
            double perPerson = totAmt / plp;    // split amount

            System.out.println("--------------------------");
            System.out.println("<====SPLIT SUMMARY====>\n");
            System.out.printf("ORIGINAL BILL : Rs.%.2f/-\n", bAmt);

            // StringBuilder to store the output for file
            StringBuilder summary = new StringBuilder();
            summary.append("<====SPLIT SUMMARY====>\n\n");
            summary.append(String.format("ORIGINAL BILL : Rs.%.2f/-\n", bAmt));

            if (tipAmt > 0.0) {
                System.out.printf("TIP AMOUNT : Rs.%.2f/-\n", tipAmt);
                System.out.printf("TOTAL AMOUNT : Rs.%.2f/-\n", totAmt);

                summary.append(String.format("TIP AMOUNT : Rs.%.2f/-\n", tipAmt));
                summary.append(String.format("TOTAL AMOUNT : Rs.%.2f/-\n", totAmt));
            } else {
                System.out.println("NO TIP ADDED");
                System.out.printf("TOTAL AMOUNT : Rs.%.2f/-\n", totAmt2);

                summary.append("NO TIP ADDED\n");
                summary.append(String.format("TOTAL AMOUNT : Rs.%.2f/-\n", totAmt2));
            }

            System.out.printf("SPLIT PER PERSON : Rs.%.2f/-\n", perPerson);
            summary.append(String.format("SPLIT PER PERSON : Rs.%.2f/-\n", perPerson));

            
            try (FileWriter writer = new FileWriter("bill.txt")) {
                writer.write(summary.toString());
                System.out.println("\nBILL PRINTED IN bill.txt");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("INVALID INPUT!");
        }
    }
}

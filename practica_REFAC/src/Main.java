import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        String p = "Proves";

        int opcio;
        do {
            //REFACT 1
            menu();
            //
            opcio = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 1:
                    //REFACT 2
                    compararNum();
                    //
                    break;
                case 2:
                    double a=2;
                    double b=3;
                    double c=1;
                    calcEquacioSegongrau(a, b, c);
                    break;
                case 3:
                    List<OrderLineItem> lineItems = null;
                    //REFACT
                    Order asd = new Order(lineItems, 5.5);
                    //
                    break;
                case 0:
                    break;
                default:
                    //REFACT 3
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 3");
                    //
            }
        } while (opcio != 0);
    }

    //REFACT 1.1
    public static void menu() {
        System.out.println("1. ");
        System.out.println("2. ");
        System.out.println("3. ");
        System.out.println("4. ");
        System.out.println("5. ");
        System.out.println("0. Acabar");
    }
    //

    //REFACT 2.1
    public static void compararNum () {
        int num1 = obtenirNum();
        int num2 = obtenirNum();
        
        if (num1 == num2) {
            System.out.println("Els dos números son iguals.");
        } else if (max(num1,num2)) {
            System.out.println("El número més gran és: " + num1);
        } else {
            System.out.println("El número més gran és: " + num2);
        }
    }

    //REFACT 2.2
    public static int obtenirNum() {
        int num;

        System.out.println("Introdueix un numero: ");
        try {
            num = scan.nextInt();
            scan.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("ERROR! Ha de ser un INT, se li ha assignat el valor 5.");
            num = 5;
        }

        return num;
    }

    public static boolean max(int a, int b) {
        if(a > b) {
            return true;
        } else if (a == b) {
            return false;
        } else {
            return false;
        }
    }

    public static void calcEquacioSegongrau(double a, double b, double c) {
        //REFACT 4
        double Discriminant = b * b - 4 * a * c;
        //

        if (Discriminant > 0) {
            double x1, x2;
            //REFACT 5
            x1 = calculOperacio2nGr('-', a, b, Discriminant);
            x2 = calculOperacio2nGr('+', a, b, Discriminant);
            //

            //REFACT 6
            System.out.println("X1 és igual a = " + x1 + ", X2 és igual a = " + x2);
            //

        } else if (Discriminant == 0) {
            double x;
            x = calculOperacio2nGrSimple(a, b);

            System.out.println("X és igual a = " + x);
        } else {
            //REFACT 7
            System.out.println("L'equació no té arrels reals.");
            //
        }
    }

    //REFACT 5.1
    public static double calculOperacio2nGr (char t, double a, double b, double D) {

        if (t == '+') {
            return (-b + Math.sqrt(D)) / (2 * a);
        } else if (t == '-') {
            return (-b - Math.sqrt(D)) / (2 * a);
        }
        return 0;
    }

    //REFACT 5.2
    public static double calculOperacio2nGrSimple(double a, double b) {
        return -b / (2 * a);
    }



    public static class Human {
        private String name;
        private String age;
        private String country;
        private String city;
        private String street;
        private String house;
        private String quarter;
        public String obtenirAdrecaCompleta() {
            StringBuilder result = new StringBuilder();
            return result
                    .append(country)
                    .append(", ")
                    .append(city)
                    .append(", ")
                    .append(street)
                    .append(", ")
                    .append(house)
                    .append(" ")
                    .append(quarter).toString();
        }
    }

    public static class Order {
        private List<OrderLineItem> lineItems;
        private double taxRate;

        public Order(List<OrderLineItem> lineItems, double taxRate) {
            this.lineItems = lineItems;
            this.taxRate = taxRate;
        }

        //No s'utilitza mai
        public double calculateTotalPrice() {
            double subtotal = 0.0;
            for (OrderLineItem item : lineItems) {
                subtotal += item.getPrice();
            }
            double tax = subtotal * taxRate;
            return subtotal + tax;
        }
    }

    public class OrderLineItem {
        private String productName;
        private int quantity;
        private double price;

        public OrderLineItem(String productName, int quantity, double price) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }
        public double getPrice() {
            return price * quantity;
        }
    }

    //La clase Customer no s'utilitza mai
    public class Customer {
        private String firstName;
        private String lastName;

        public Customer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }
    }
}
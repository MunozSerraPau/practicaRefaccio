import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        //No s'utilitza
        String p = "Proves";

        //REFACT 11
        String opcio;
        //Modifiquem el tipus de la variable opció per no tenir cap problema a l'hora d'introduir altres dades incorrectes.
        do {
            //REFACT 1 Tipus: Extracció de mètode
            menu();
            //Hem creat una funció pròpia per al menú on el mostrem un cop la cridem.
            opcio = scan.nextLine();
            switch (opcio) {
                case "1":
                    //REFACT 2 Tipus: Extracció de mètode
                    compararNum();
                    //Hem canviat el codi de lloc i hem creat un mètode amb ell.
                    break;
                case "2":
                    double a=2;
                    double b=3;
                    double c=1;
                    calcEquacioSegongrau(a, b, c);
                    break;
                case "3":
                    List<OrderLineItem> lineItems = null;
                    //REFACT 9 Tipus: Reanomenar
                    Order item = new Order(lineItems, 5.5);
                    //Modifiquem el nom de la variable per fer-la una mica més entenedora.
                    break;
                case "0":
                    break;
                default:
                    //REFACT 3
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 3");
                    //El menú només conte 3 punts (abans 0 i 5).
            }
        } while (opcio.equals("0"));
    }

    //REFACT 1.1 Tipus: Extracció de mètode
    //Aquest és el codi que es trobava a la funció Main.
    public static void menu() {
        System.out.println("1. Comparar dos números");
        System.out.println("2. Càlcul equació de segon grau");
        System.out.println("3. Calcular preu");
        //REFACT 8
        //Esborrem els prints innecessaris, ja que només tenim 3 punts.
        System.out.println("0. Acabar");
    }

    //REFACT 2.1 Tipus: Extracció de mètode
    //Hem fet una nova funció amb el codi que es trobava al switch.
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

    //REFACT 2.2 Tipus: Extracció de mètode, Substitució d’algoritme(Evitem que es repeteixi demanar el número de forma repetitiva.)
    //També hem fet una funció per demanar els valors i gestionar les excepcions.
    public static int obtenirNum() throws IllegalArgumentException{
        System.out.println("Introdueix un numero: ");
        int num = 0;
        try {
            num = scan.nextInt();
            scan.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("ERROR: Has introduït malament un dels valors, torna-ho a intentar.");
            System.exit(0);
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
        //REFACT 4 Tipus: Reanomenar
        double Discriminant = b * b - 4 * a * c;
        //Canviem d per → Discriminant per fer el codi més entenedor.

        if (Discriminant > 0) {
            double x1, x2;
            //REFACT 5 Tipus: Extracció de mètode
            x1 = calculOperacio2nGr('-', a, b, Discriminant);
            x2 = calculOperacio2nGr('+', a, b, Discriminant);
            //Fem una nova funció per fer els càlculs de x1 i x2

            //REFACT 6 Tipus: Reanomenar
            System.out.println("X1 és igual a = " + x1 + ", X2 és igual a = " + x2);
            //Modifiquem el print per fer-lo més entenedor.

        } else if (Discriminant == 0) {
            double x;
            x = calculOperacio2nGrSimple(a, b);

            System.out.println("X és igual a = " + x);
        } else {
            //REFACT 7 Tipus: Reanomenar
            System.out.println("L'equació no té arrels reals.");
            //Modifiquem el print per fer-lo més entenedor.
        }
    }

    //REFACT 5.1 Tipus: Extracció de mètode
    //Aquesta és la nova funció amb la qual fem els càlculs necessaris per obtenir el valor de x1 i x2.
    public static double calculOperacio2nGr (char t, double a, double b, double d) {

        if (t == '+') {
            return (-b + Math.sqrt(d)) / (2 * a);
        } else if (t == '-') {
            return (-b - Math.sqrt(d)) / (2 * a);
        }
        return 0;
    }

    //REFACT 5.2 Tipus: Extracció de mètode
    //Aquesta és la nova funció amb la qual fem els càlculs necessaris per obtenir el valor de x.
    public static double calculOperacio2nGrSimple(double a, double b) {
        return -b / (2 * a);
    }

    //La clase Human no s'utilitza mai
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
import java.util.Scanner; 
import java.util.InputMismatchException;

public class ComandesRestaurant {

    //Scanner Només Main Menu
    Scanner askOptionMenu = new Scanner(System.in);
    int mainOption;
    //Scanner Reutilitzable
    Scanner askValue = new Scanner(System.in);
    //Comandes
    String orderList = "";
    int idOrder = 1;
    double orderFinalMount;
    String header;
    String separation;
    String lastProducts = "";
    //ALTRES
    int table = 0;

    
    public static void main(String[] args) {
        ComandesRestaurant p = new ComandesRestaurant();
        p.principal();
    }
    
    public void principal() {
        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("|||        Welcome to the restaurant order manager         |||");
        System.out.println("|||    Powered by Edgar Rodrigo from IT Solutions Company  |||");
        System.out.println("--------------------------------------------------------------");

        boolean running = true;
        while (running) {
            try {
                mainMenu();
                if (mainOption == 1){
                    createOrder();
                }else if(mainOption == 2){
                    if (table == 0) {
                        System.out.println("\nYou can not modify values without an order, please create an order first.");
                    }else{
                        modifyOrder();
                    }
                }else if(mainOption == 3){
                    showLastOrder();
                }else if (mainOption == 4){
                    running = false;
                    System.out.println("\nGoodbye!");
                }else{
                    System.out.println("\nInvalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.print("\nInvalid input, please enter a valid value.\n");
                askOptionMenu.nextLine();
            } catch (Exception e) {
                System.out.print("\nInvalid input, please enter a valid value.\n");
                askOptionMenu.nextLine();
            }
        }
    }
    
    //MÉTODE QUE CONTÉ EL MENU
    public void mainMenu(){
        boolean valid = false;
        while (!valid) {
            System.out.println();
            System.out.println("1) Create new order");
            System.out.println("2) Modify lastest order");
            System.out.println("3) Show lastest order");
            System.out.println("4) Exit");
            System.out.println();
            System.out.print("Select an option (1-4): ");
            try {
                mainOption = askOptionMenu.nextInt();
                if (mainOption < 1 || mainOption > 4) {
                    System.out.print("\nInvalid input, please enter a valid number (1-4).\n");
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.print("\nInvalid input, please enter a valid number (1-4).\n");
                askOptionMenu.nextLine();
            } catch (Exception e) {
                System.out.print("\nInvalid input, please enter a valid number (1-4).\n");
                askOptionMenu.nextLine();
            }
        }
    }
    
    //MÉTODE PER VEURE L'ULTIMA COMANDA
    public void showLastOrder(){
        try {
            if (orderList.isEmpty()) {
                System.out.println("\nNo orders yet.");
            } else {
                System.out.println(orderList);
            }
        } catch (Exception e) {
            System.out.println("\nError showing order.");
        }
    }
    
    //MÉTODE CREAR COMANDA
    public void createOrder(){
        try {
            System.out.println();
            System.out.println("Order id: " + idOrder);

            boolean validTable = false;
            while (!validTable) {
                System.out.print("Table number (1-10): ");
                try {
                    table = askValue.nextInt();
                    if (table >= 1 && table <= 10) {
                        validTable = true;
                    } else {
                        System.out.print("\nInvalid input, please enter a valid table number (1-10).\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("\nInvalid input, please enter a valid table number (1-10).\n");
                    askValue.nextLine();
                } catch (Exception e) {
                    System.out.print("\nInvalid input, please enter a valid table number (1-10).\n");
                    askValue.nextLine();
                }
            }
            askValue.nextLine();

            if (table >= 1 && table <=10){
                orderFinalMount = 0;

                separation = "--------------------------------------------------\n";
                header = completeWord("==================================================\nProducte", 16) + completeWord("Quantitat", 12) + completeWord("Preu unit.", 12) + completeWord("Subtotal", 12) + "\n";

                lastProducts = addProductsLoop();

                double base = orderFinalMount;
                double iva = Math.round(base * 0.10 * 100.0) / 100.0;
                double total = Math.round((base + iva) * 100.0) / 100.0;

                String baseFormat = String.format("%.2f EUR", base);
                String ivaFormat  = String.format("%.2f EUR", iva);
                String totalPriceFormat  = String.format("%.2f EUR", total);

                String totals = separation + completeWord("Total sense IVA:", 16 + 12 + 12) + completeWord(baseFormat, 12) + "\n" + completeWord("IVA (10%):",        16 + 12 + 12) + completeWord(ivaFormat, 12) + "\n" + separation + completeWord("TOTAL A PAGAR:",    16 + 12 + 12) + completeWord(totalPriceFormat, 12) + "\n" + "==================================================\n";

                orderList = header + separation + lastProducts + totals;

                System.out.println("\nOrder #" + idOrder + " added correctly!");
                idOrder++;
                showLastOrder();
            }else{
                System.out.println("\nPlease enter valid table number.");
            }
        } catch (InputMismatchException e) {
            System.out.print("\nInvalid input, please enter a valid value.\n");
            askValue.nextLine();
        } catch (Exception e) {
            System.out.print("\nInvalid input, please enter a valid value.\n");
            askValue.nextLine();
        }
    }
    
    //MÉTODE PER MODIFICAR DADES DE LA ULTIMA COMANDA
    public void modifyOrder(){
        try {
            String extra = addProductsLoop();
            lastProducts += extra;

            double base = orderFinalMount;
            double iva = Math.round(base * 0.10 * 100.0) / 100.0;
            double total = Math.round((base + iva) * 100.0) / 100.0;

            String baseFormat = String.format("%.2f EUR", base);
            String ivaFormat  = String.format("%.2f EUR", iva);
            String totalPriceFormat  = String.format("%.2f EUR", total);

            String totals = separation + completeWord("Total sense IVA:", 16 + 12 + 12) + completeWord(baseFormat, 12) + "\n" + completeWord("IVA (10%):",        16 + 12 + 12) + completeWord(ivaFormat, 12) + "\n" + separation + completeWord("TOTAL A PAGAR:",    16 + 12 + 12) + completeWord(totalPriceFormat, 12) + "\n" + "==================================================\n";

            orderList = header + separation + lastProducts + totals;
            System.out.println("\nOrder modified correctly!\n");
            showLastOrder();
        } catch (InputMismatchException e) {
            System.out.print("\nInvalid input, please enter a valid number.\n");
            askValue.nextLine();
        } catch (Exception e) {
            System.out.print("\nInvalid input, please enter a valid value.\n");
            askValue.nextLine();
        }
    }

    public double readUnitPrice() {
        while (true) {
            try {
                String unitPrice = askValue.nextLine().trim();
                unitPrice = unitPrice.replace(',', '.');
                return Double.parseDouble(unitPrice);
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input, please enter a valid unit price.\n");
            } catch (Exception e) {
                System.out.print("\nInvalid input, please enter a valid unit price.\n");
            }
        }
    }

    public String completeWord(String p, int max) {
        String temporal = p;
        try {
            for (int i = p.length(); i < max; i++) {
                temporal += " ";
            }
        } catch (Exception e) {
            temporal = p;
        }
        return temporal;
    }

    public String addProductsLoop() {
        String listProduct = "";
        boolean isAdding = true;
        try {
            do{
                System.out.print("Product Name: ");
                String item = askValue.nextLine();

                int itemQuantity = 0;
                boolean validQty = false;
                while (!validQty) {
                    System.out.print("Quantity: ");
                    try {
                        itemQuantity = askValue.nextInt();
                        validQty = true;
                    } catch (InputMismatchException e) {
                        System.out.print("\nInvalid input, please enter a valid quantity.\n");
                        askValue.nextLine();
                    } catch (Exception e) {
                        System.out.print("\nInvalid input, please enter a valid quantity.\n");
                        askValue.nextLine();
                    }
                }
                askValue.nextLine();

                System.out.print("Unit Price: ");
                double unitPrice = readUnitPrice();
                double totalLine = unitPrice * itemQuantity;

                String unitFormatted = String.format("%.2f EUR", unitPrice);
                String lineFormatted = String.format("%.2f EUR", totalLine);

                String line = completeWord(item, 16) + completeWord(String.valueOf(itemQuantity), 12) + completeWord(unitFormatted, 12) + completeWord(lineFormatted, 12) + "\n";

                listProduct += line;
                orderFinalMount += totalLine;

                System.out.println();
                System.out.print("Continue adding (Y/N): ");
                String addMore = askValue.nextLine();
                if (addMore.equalsIgnoreCase("n")) {
                    isAdding = false;
                }else if (addMore.equalsIgnoreCase("y")) {
                    isAdding = true;
                }else{
                    System.out.print("\nInvalid input, please enter Y or N.\n");
                }
            }while(isAdding);
        } catch (Exception e) {
            System.out.print("\nInvalid input, please enter a valid value.\n");
            askValue.nextLine();
        }
        return listProduct;
    }
}

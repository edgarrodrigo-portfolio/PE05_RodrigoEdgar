import java.util.Scanner;

public class ComandesRestaurant {

    //Scanner Només Main Menu
    Scanner askOptionMenu = new Scanner(System.in);
    int mainOption;
    //Scanner Reutilitzable
    Scanner askValue = new Scanner(System.in);
    //Comandes
    String orderList = "";
    int idOrder = 1; //Es la clau primaria parlant de base de dades, és l'increment que fará per a que no coinsideixi.
    double productPrice;
    double orderFinalMount;
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
        
        mainMenu();
        if (mainOption == 1){
            System.out.println();
            System.out.println("Order id: " + idOrder);
            System.out.print("Table number (1-10): ");
            int table = askValue.nextInt();

            if (table >= 1 && table <=10){
                String printTopOrder = "Order ID #" + idOrder + " Table number: " + table;
                String listProduct = "";

                boolean isAdding = true;
                //Neteja Scanner
                askValue.nextLine();
                do{
                    //Afegir Producte
                    System.out.print("Product Name: ");
                    String item = askValue.nextLine();

                    System.out.print("Quantity: ");
                    int itemQuantity = askValue.nextInt();

                    System.out.print("Unit Price: ");
                    productPrice = askValue.nextDouble();
                    productPrice = productPrice * itemQuantity;

                    //Neteja Scanner
                    askValue.nextLine();

                    //Guardem Linea de producte + unitats
                    listProduct += "\n--> " + item + "\nUnits: " + itemQuantity + " = " + productPrice + " euros";
                    orderFinalMount += productPrice;
                    System.out.println();
                    System.out.print("Continue adding (Y/N): ");
                    String addMore = askValue.nextLine();
                    if (addMore.equalsIgnoreCase("n")) {
                        isAdding = false;
                    }else if (addMore.equalsIgnoreCase("y")) {
                        isAdding = true;
                    }else{
                        System.out.println("Try again, invalid input.");
                    }
                }while(isAdding);
                orderList += printTopOrder + listProduct + "\n";
                System.out.println("Order #" + idOrder + " added correctly!");
                idOrder++;
                showLastOrder();
            }else{
                System.out.println("Please enter valid table number.");
                mainMenu();
            }
            
            
        }
    }
    //METODE QUE CONTÉ EL MENU
    public void mainMenu(){
        
        System.out.println();
        System.out.println("1) Create new order");
        System.out.println("2) Modify lastest order");
        System.out.println("3) Show lastest order");
        System.out.println("4) Exit");
        System.out.println();
        System.out.print("Select an option (1,2,3,4): ");
        mainOption = askOptionMenu.nextInt();

    }
    //METODE PER VEURE L'ULTIMA COMANDA
    public void showLastOrder(){
        //Print Order al final de la creació de cada comanda
                System.out.println("---- ORDER ----");
                System.out.println(orderList);
                System.out.println("---- BILL MOUNT ----");
                System.out.println("Total: "+orderFinalMount);
    }
    //METODE PER MODIFICAR DADES DE LA ULTIMA COMANDA
    public void modifyOrder(){
        
    }




}

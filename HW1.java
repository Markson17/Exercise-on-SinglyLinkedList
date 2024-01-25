import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HW1 {

    private static SinglyLinkedList appleIPhoneSellerList = new SinglyLinkedList();
    private static SinglyLinkedList earBudsSellerList = new SinglyLinkedList();
    private static SinglyLinkedList keyboardSellerList = new SinglyLinkedList();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input: Please provide the input file name as a command-line argument.");
            return;
        }

        String inputFileName = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                String eventType = words[0];

                switch (eventType) {
                    case "AddSeller":
                        String productToAdd = words[1];
                        String sellerToAdd = words[2];
                        double productPrice = Double.parseDouble(words[3]);
                        double shippingCost = Double.parseDouble(words[4]);
                        int quantity = Integer.parseInt(words[5]);

                        // Check for non-positive quantity
                        if (quantity <= 0) {
                            System.out.println(eventType + " " + productToAdd + " " + sellerToAdd + " " + productPrice + " " + shippingCost + " " + quantity + " NonPositiveQuantityError");
                        } else {
                            SinglyLinkedList listToAdd = getListForProduct(productToAdd);
                            listToAdd.add(new SinglyLinkedList.Node(sellerToAdd, productPrice, shippingCost, quantity));
                            System.out.println(eventType + " " + productToAdd + " " + sellerToAdd + " " + productPrice + " " + shippingCost + " " + quantity);
                        }
                        break;

                    case "RemoveSeller":
                        String productToRemoveFrom = words[1];
                        String sellerToRemove = words[2];
                        SinglyLinkedList listToRemoveFrom = getListForProduct(productToRemoveFrom);

                        // Attempt to remove the seller
                        listToRemoveFrom.remove(sellerToRemove);
                        System.out.println(eventType + " " + productToRemoveFrom + " " + sellerToRemove + " " + "NonExistingSellerError");
                        break;

                    case "IncreaseInventory":
                        String sellerToIncrease = words[1];
                        int quantityIncrease = Integer.parseInt(words[2]);
                        SinglyLinkedList listToIncrease = getListForProduct(words[3]);
                        listToIncrease.updateInventory(sellerToIncrease, quantityIncrease);
                        System.out.println(eventType + " " + words[1] + " " + sellerToIncrease + " " + quantityIncrease);
                        break;

                    case "CustomerPurchase":
                        String productToPurchaseFrom = words[1];
                        String sellerToPurchaseFrom = words[2];
                        int quantityToPurchase = Integer.parseInt(words[3]);
                        SinglyLinkedList listToPurchaseFrom = getListForProduct(productToPurchaseFrom);

                        // Print the details of the case
                        System.out.println(eventType + " " + productToPurchaseFrom + " " + sellerToPurchaseFrom + " " + quantityToPurchase);

                        // Print the updated inventory or an error message
                        listToPurchaseFrom.updateInventory(sellerToPurchaseFrom, -quantityToPurchase);
                        break;

                    case "DisplaySellerList":
                        String product = words[1];
                        SinglyLinkedList listToDisplay = getListForProduct(product);

                        // Print the name of the case
                        System.out.println(eventType + " " + product);

                        System.out.println(listToDisplay);
                        break;

                    default:
                        System.out.println("Invalid event type: " + eventType);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }

    private static SinglyLinkedList getListForProduct(String product) {
        switch (product) {
            case "appleIPhone":
                return appleIPhoneSellerList;
            case "earBuds":
                return earBudsSellerList;
            case "keyboard":
                return keyboardSellerList;
            default:
                throw new IllegalArgumentException("Invalid product: " + product);
        }
    }
}

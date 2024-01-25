
public class SinglyLinkedList {
    private Node head;
    private boolean headerPrinted = false;

    public void add(Node newNode) {
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        Node previous = null;
        while (current != null) {
            if (newNode.getTotalCost() < current.getTotalCost()) {
                break;
            } else if (newNode.getTotalCost() == current.getTotalCost()) {
                if (newNode.getSeller().compareTo(current.getSeller()) < 0) {
                    break;
                }
            }
            previous = current;
            current = current.getNext();
        }

        if (previous == null) {
            newNode.setNext(head);
            head = newNode;
        } else {
            newNode.setNext(current);
            previous.setNext(newNode);
        }
    }

    public void remove(String seller) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.getSeller().equals(seller)) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return;
            }
            previous = current;
            current = current.getNext();
        }

    }

    public Node find(String seller) {
        Node current = head;
        while (current != null) {
            if (current.getSeller().equals(seller)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node getHead() {
        return head;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Print header only if it hasn't been printed yet
        if (!headerPrinted) {
            sb.append(
                    String.format("%-15s %-15s %-15s %-15s%n", "Seller", "ProductPrice", "ShippingCost", "TotalCost"));
            headerPrinted = true; // Set the flag to true after printing the header
        }

        Node current = head;
        while (current != null) {
            sb.append(String.format("%-15s %-15.2f %-15.2f %-15.2f%n", current.getSeller(), current.getProductPrice(),
                    current.getShippingCost(), current.getTotalCost()));
            current = current.getNext();
        }

        // Reset the headerPrinted flag
        headerPrinted = false;

        return sb.toString();
    }

    public void updateInventory(String seller, int quantityChange) {
        Node node = find(seller);
        if (node == null) {
            return;
        }

        node.setQuantity(node.getQuantity() + quantityChange);
        if (node.getQuantity() <= 0) { // Updated condition to include quantity 0
            remove(seller);
            System.out.println("DepletedInventoryRemoveSeller " + seller); // Removed unnecessary exception check
        }
    }

    // Nested Node class definition
    public static class Node {
        private String seller;
        private double productPrice;
        private double shippingCost;
        private int quantity;
        private Node next;

        public Node(String seller, double productPrice, double shippingCost, int quantity) {
            this.seller = seller;
            this.productPrice = productPrice;
            this.shippingCost = shippingCost;
            this.quantity = quantity;
            this.next = null;
        }

        public String getSeller() {
            return this.seller;
        }

        public String setSeller(String seller) {
            return this.seller = seller;
        }

        public double getProductPrice() {
            return this.productPrice;
        }

        public double setProductPrice(double productPrice) {
            return this.productPrice = productPrice;
        }

        public double getShippingCost() {
            return this.shippingCost;
        }

        public double setShippingCost(double shippingCost) {
            return this.shippingCost = shippingCost;
        }

        public int getQuantity() {
            return this.quantity;
        }

        public int setQuantity(int quantity) {
            return this.quantity = quantity;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public double getTotalCost() {
            return this.productPrice + this.shippingCost;
        }

        public String toString() {
            return String.format("%-10s %8.2f %8.2f %8.2f", seller, productPrice, shippingCost, getTotalCost());
        }

    }

}

# Shopping and Inventory Management System(Exercise on SinglyLinkedList)

## Overview

This program simulates the management of multiple sellers for various products, such as appleIPhone, earBuds, and keyboard. The seller list is maintained using a singly linked list, ordered by the total cost (product price plus shipping cost), with ties broken alphabetically by seller names. The system handles various events like adding sellers, removing sellers, updating inventory, customer purchases, and displaying seller lists.

## Instructions

To run the program, execute the `HW1.java` file with the input file name as a command-line argument. The input file should contain events that describe shopping and inventory actions.

## Input Format

The input file should contain the following types of events:

- `AddSeller product seller price shippingCost quantity [NonPositiveQuantityError]`
- `RemoveSeller product seller [NonExistingSellerError]`
- `IncreaseInventory product seller quantity updatedInventory`
- `CustomerPurchase product seller quantity updatedInventory or NotEnoughInventoryError`
- `DisplaySellerList product`

## Output Format

The program prints events to the standard output, and each event is on a separate line. Possible events include:

- `AddSeller product seller price shippingCost quantity [NonPositiveQuantityError]`
- `RemoveSeller product seller [NonExistingSellerError]`
- `IncreaseInventory product seller quantity updatedInventory`
- `CustomerPurchase product seller quantity updatedInventory or NotEnoughInventoryError`
- `DepletedInventoryRemoveSeller product seller`
- `DisplaySellerList product`

Additionally, the program outputs the seller details in the following format:

```
seller          productPrice    shippingCost    totalCost
walmart         20.99           0.00            20.99
amazon          16.95           5.00            21.95
bestbuy         21.99           0.00            21.99
```

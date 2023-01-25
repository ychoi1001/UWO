'''
This program computes the cost of breakfast at the Good Morning America! restaurant.
This program is expected to prompt the user for input and validate it before computing the results.
'''

# Declare variables and assign values
EGG = 0.99
BACON = 0.49
SAUSAGE = 1.49
HASH_BROWN = 1.19
TOAST = 0.79
COFFEE = 1.09
TEA = 0.89
cost = 0.0

# Accept inputs whether they contain upper-case or lower-case cahracters
# Be robust to leading and trailing spaces, including cases when multiple spaces separate words in input lines
def formatInput(textLine):
    textLine = textLine.lower().strip()
    wordList = textLine.split()
    textLine = " ".join(wordList)
    return textLine

# While loop to continue to ask for additional menu items by re-displaying the choices
while (True):
    # Prompt the user for various piecees of information about the desired breakfast
    item = input("Enter item (q to terminate): small breakfast, regular breakfast, big breakfast, egg, bacon, sausage, hash brown, toast, coffee, tea: ")

    # Format a chosen item
    formatItem = formatInput(item)
    
    # Terminate the execution of while loop
    if formatItem == 'q':
        break

    # When an invalid input is detected, the program will display an error message
    # and prompt for the input until the user enters it correctly
    while formatItem not in ['small breakfast', 'regular breakfast', 'big breakfast', 'egg', 'bacon', 'sausage', 'hash brown', 'toast', 'coffee', 'tea']:
        # Display an error message
        item = input("Invalid! Please enter a valid input: ")

        # Format a chosen item
        formatItem = formatInput(item)

    # Ask for the quantity
    itemQty = input("Enter quantity: ")

    # Robust to users entering input other than numbers when quantities are requested
    while not itemQty.isnumeric():
        # Display an error message
        itemQty = input("Invalid! Please enter a valid quantity: ")
    
    # Convert data type to int
    itemQty = int(itemQty)
    
    # Compute the total breakfast cost for an entire table of customers
    if formatItem == 'small breakfast':
        cost = cost + itemQty * (EGG + HASH_BROWN + (2 * TOAST) + (2 * BACON) + SAUSAGE)
    elif formatItem == 'regular breakfast':
        cost = cost + itemQty * ((2 * EGG) + HASH_BROWN + (2 * TOAST) + (4 * BACON) + (2 * SAUSAGE))
    elif formatItem == 'big breakfast':
        cost = cost + itemQty * ((3 * EGG) + (2 * HASH_BROWN) + (4 * TOAST) + (6 * BACON) + (3 * SAUSAGE))
    elif formatItem == 'egg':
        cost = cost + itemQty * EGG
    elif formatItem == 'bacon':
        cost = cost + itemQty * BACON
    elif formatItem == 'sausage':
        cost = cost + itemQty * SAUSAGE
    elif formatItem == 'hash brown':
        cost = cost + itemQty * HASH_BROWN
    elif formatItem == 'toast':
        cost = cost + itemQty * TOAST
    elif formatItem == 'coffee':
        cost = cost + itemQty * COFFEE
    elif formatItem == 'tea':
        cost = cost + itemQty * TEA    

# Calculate tax
tax = round(cost, 2) * 0.13

# Display the pre-tax total, the tax, and the total with tax
print("%-10.8s$%10.2f" %("Cost : ", round(cost, 2)))
print("%-10.8s$%10.2f" %("Tax : ", tax))
print("%-10.8s$%10.2f" %("Total : ", tax + round(cost, 2)))


    



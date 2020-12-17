# vending_machine

Vending machine in Java with some basic functionalities: Bank (keep track on incoming money, give change), Control Panel (class to dispense a product based on code input), Storage (keep track of products in the machine) and two classes with reporting functionality: Report (generate a PDF report containing a summary of the products sold) and EmailSender (send the report per email).

Queue used for Bank class, enum used for the currency, considering constans were used for them. Map was used for the Product class.

The Report class uses the itextpdf with PDFWriter to print a report with the products sold. This report can be sent by email with javax. Used a dummy gmail email address.

Also created to exceptions for lack of change and Sold Out for a product.

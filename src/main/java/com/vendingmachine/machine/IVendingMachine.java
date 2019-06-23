package com.vendingmachine.machine;

import java.util.Queue;

import com.vendingmachine.bank.Money;
import com.vendingmachine.machine.product.Product;

public interface IVendingMachine {

	public Product dispenceProduct(int shelfCode);

	public boolean payProductPrice(int shelfCode, Queue<Money> amountPaid);

	public Queue<Money> computeChange(double amountReceived, double amountExpected);

	public boolean validateAmountReceived(Queue<Money> moneyReceived);

	public boolean checkProductAvailability();

}

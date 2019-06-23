package com.vendingmachine.machine;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import com.vendingmachine.bank.Bank;
import com.vendingmachine.bank.Money;
import com.vendingmachine.exception.NotFullPaidException;
import com.vendingmachine.exception.SoldOutException;
import com.vendingmachine.machine.product.Product;
import com.vendingmachine.machine.storage.Storage;

public class VendingMachineService implements IVendingMachine {

	private Storage storage;

	private Bank bank;

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	@Override
	public Product dispenceProduct(int shelfCode) {
		Map<Integer, Queue<Product>> storageMap = storage.getStorageMap();
		Queue<Product> productQueue = storageMap.get(shelfCode);
		if (productQueue.isEmpty()) {
			throw new SoldOutException("Product is not available.");
		}
		Product head = productQueue.poll();
		return head;
	}

	@Override
	public boolean payProductPrice(int shelfCode, Queue<Money> amountPaid) {
		Map<Integer, Queue<Product>> storageMap = storage.getStorageMap();
		Queue<Product> productQueue = storageMap.get(shelfCode);
		double productPrice = productQueue.poll().getPrice();

		double amountPaidAsDouble = getTotalMoneyAsDouble(amountPaid);

		if (amountPaidAsDouble >= productPrice) {
			// return change (to do method)
			updateBank(amountPaid);
			Queue<Money> change = computeChange(amountPaidAsDouble, productPrice);
			updateBankWithChange(change);
			dispenceProduct(shelfCode);
			return true;

		} else {
			if (amountPaidAsDouble < productPrice) {
				throw new NotFullPaidException("Saracule!");
			}
		}
		return false;
	}

	@Override
	public Queue<Money> computeChange(double amountReceived, double amountExpected) {

		double totalChange = amountExpected - amountReceived;
		double change = totalChange % 5;
		double noOfCoins = totalChange / 5;
		Queue<Money> coins = new ArrayDeque<>();

		if (noOfCoins != 0 && checkAvailableMoney(getBank().getFiveDollarStack(), noOfCoins)) {
			// create queue of coins to return
			for (int i = 0; i <= noOfCoins; i++) {
				coins.add(Money.FIVE_DOLLAR);
			}
		}
		if (change == 0) {
			return coins;
		} else {
			change = change % 1;
			noOfCoins = change / 1;
			if (noOfCoins != 0 && checkAvailableMoney(getBank().getOneDollarStack(), noOfCoins)) {
				for (int i = 0; i <= noOfCoins; i++) {
					coins.add(Money.ONE_DOLLAR);
				}
			}
			if (change == 0) {
				return coins;
			} else {
				change = change % 0.5;
				noOfCoins = change / 0.5;
				if (noOfCoins != 0 && checkAvailableMoney(getBank().getFiftyCentsStack(), noOfCoins)) {
					for (int i = 0; i <= noOfCoins; i++) {
						coins.add(Money.FIFTY_CENT);
					}
				}
				if (change == 0) {
					return coins;
				}
			}
		}
		if (change == 0) {
			return coins;
		} else {
			change = change % 0.1;
			noOfCoins = change / 0.1;
			if (noOfCoins != 0 && checkAvailableMoney(getBank().getTenCentsStack(), noOfCoins)) {
				for (int i = 0; i <= noOfCoins; i++) {
					coins.add(Money.TEN_CENT);
				}
			}
			if (change == 0) {
				return coins;
			}
		}
		return coins;
	}

	@Override
	public boolean validateAmountReceived(Queue<Money> moneyReceived) {

		// parcurgem queue-ul si tot ce e diferit de banii pe care ii avem se respinge

		return false;

	}

	@Override
	public boolean checkProductAvailability() {
		// TODO Auto-generated method stub
		return false;
	}

	// to do: metoda care verifica in storage daca produsul e disponibil

	// to do: metoda care verifica in bank daca avem suficient rest disponibil

	private boolean checkAvailableMoney(Queue<Money> moneyStack, double enteredMoney) {

		if (moneyStack.size() > enteredMoney) {
			return true;
		} else {
			throw new NotSufficientChangeException("Sorry, not enough change!");
		}

	}

	private void updateBank(Queue<Money> moneyReceived) {
		for (Money money : moneyReceived) {
			switch (money) {
			case FIVE_DOLLAR:
				getBank().getFiveDollarStack().add(money);
				break;
			case ONE_DOLLAR:
				getBank().getOneDollarStack().add(money);
				break;
			case FIFTY_CENT:
				getBank().getFiftyCentsStack().add(money);
				break;
			case TEN_CENT:
				getBank().getTenCentsStack().add(money);
				break;
			}
		}
	}

	private void updateBankWithChange(Queue<Money> moneyReceived) {
		for (Money money : moneyReceived) {
			switch (money) {
			case FIVE_DOLLAR:
				getBank().getFiveDollarStack().poll();
				break;
			case ONE_DOLLAR:
				getBank().getOneDollarStack().poll();
				break;
			case FIFTY_CENT:
				getBank().getFiftyCentsStack().poll();
				break;
			case TEN_CENT:
				getBank().getTenCentsStack().poll();
				break;
			}
		}
	}

	private double getTotalMoneyAsDouble(Queue<Money> money) {
		double total = 0;
		for (Money m : money) {
			if (m.equals(Money.FIVE_DOLLAR)) {

				switch (m) {
				case FIVE_DOLLAR:
					total += 5;
					break;
				case ONE_DOLLAR:
					total += 1;
					break;
				case FIFTY_CENT:
					total += 0.5;
					break;
				case TEN_CENT:
					total += 0.1;
					break;
				}
			}
		}
		return total;
	}
}
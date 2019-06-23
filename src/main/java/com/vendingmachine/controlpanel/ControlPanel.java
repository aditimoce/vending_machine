package com.vendingmachine.controlpanel;

import com.vendingmachine.machine.IVendingMachine;
import com.vendingmachine.machine.product.Product;

public class ControlPanel {
	private IVendingMachine iVendingMachine;

	public Product dispenceProduct(int shelfCode) {

		return iVendingMachine.dispenceProduct(shelfCode);
	}

	public IVendingMachine getiVendingMachine() {
		return iVendingMachine;
	}

	public void setiVendingMachine(IVendingMachine iVendingMachine) {
		this.iVendingMachine = iVendingMachine;
	}

}

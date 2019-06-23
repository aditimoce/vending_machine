package com.vendingmachine.controlpanel;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import com.vendingmachine.email.EmailSender;
import com.vendingmachine.machine.VendingMachineService;
import com.vendingmachine.machine.product.CocaCola;
import com.vendingmachine.machine.product.Product;
import com.vendingmachine.machine.storage.Storage;
import com.vendingmachine.report.PDFGenerator;

public class ControlPanelTest {

	@Test
	public void dispenceProductTest() {
		VendingMachineService iVendingMachine = new VendingMachineService();
		Storage storage = new Storage();
		Map<Integer, Queue<Product>> storageMap = new HashMap<>();
		Queue<Product> productQueue = new ArrayDeque<>();
		Product p1 = new CocaCola();
		p1.setId(1);
		p1.setPrice(3.5);
		Product p2 = new CocaCola();
		p2.setId(2);
		p2.setPrice(5);
		productQueue.add(p1);
		productQueue.add(p2);
		storageMap.put(3, productQueue);

		ControlPanel controlPanel = new ControlPanel();
		controlPanel.setiVendingMachine(iVendingMachine);

		storage.setStorageMap(storageMap);
		iVendingMachine.setStorage(storage);

		// Product dispencedProduct = controlPanel.dispenceProduct(3);
		PDFGenerator pdfGenerator = new PDFGenerator();
		pdfGenerator.generatePDF(productQueue);
		EmailSender emailSender = new EmailSender();
		// emailSender.sendEmail("zanc.razvan@gmail.com");
		/*
		 * assertNotNull(dispencedProduct); assertEquals(1, dispencedProduct.getId());
		 */

	}

}

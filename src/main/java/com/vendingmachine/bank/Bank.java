package com.vendingmachine.bank;

import java.util.Queue;

public class Bank {
	private int id;
	private Queue<Money> tenCentsStack;
	private Queue<Money> fiftyCentsStack;
	private Queue<Money> oneDollarStack;
	private Queue<Money> fiveDollarStack;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Queue<Money> getTenCentsStack() {
		return tenCentsStack;
	}

	public void setTenCentsStack(Queue<Money> tenCentsStack) {
		this.tenCentsStack = tenCentsStack;
	}

	public Queue<Money> getFiftyCentsStack() {
		return fiftyCentsStack;
	}

	public void setFiftyCentsStack(Queue<Money> fiftyCentsStack) {
		this.fiftyCentsStack = fiftyCentsStack;
	}

	public Queue<Money> getOneDollarStack() {
		return oneDollarStack;
	}

	public void setOneDollarStack(Queue<Money> oneDollarStack) {
		this.oneDollarStack = oneDollarStack;
	}

	public Queue<Money> getFiveDollarStack() {
		return fiveDollarStack;
	}

	public void setFiveDollarStack(Queue<Money> fiveDollarStack) {
		this.fiveDollarStack = fiveDollarStack;
	}

}

package rl;

import rl.apis.DummySvcImpl;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class MainDriverClass {
	public static void main(String[] args) throws InterruptedException {
		DummySvcImpl  dummySvc = new DummySvcImpl();
		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();

		Thread.sleep(3000);

		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();
		dummySvc.doAction();
	}
}

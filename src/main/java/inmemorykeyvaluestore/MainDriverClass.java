package inmemorykeyvaluestore;

import inmemorykeyvaluestore.implementations.KeyValueStore;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author naveen.chauhan on 19/07/22
 */
public class MainDriverClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		KeyValueStore keyValueStore = new KeyValueStore();
		while(!input.equalsIgnoreCase("Exit")) {
			input = scanner.nextLine();
			String[] commands = input.split(" ");

			switch (commands[0]) {
				case "put":
					keyValueStore.put(commands[1], commands[2]);
					break;
				case "get":
					String value = keyValueStore.get(commands[1]);
					System.out.println(value);
					break;
				case "keys":
					List<String> result = keyValueStore.keys();
					System.out.println(Arrays.toString(result.toArray()).replace("[", "").replace("]", "").trim());
					break;
				case "delete":
					keyValueStore.delete(commands[1]);
					break;
				case "search":
					value = keyValueStore.search(commands[1], commands[2]);
					System.out.println(value);
					break;
			}
		}

	}

	public static void print(String[] strings) {
		for (String str: Arrays.asList(strings)) {
			System.out.print(str + " ");
		}
		System.out.println();
	}
}

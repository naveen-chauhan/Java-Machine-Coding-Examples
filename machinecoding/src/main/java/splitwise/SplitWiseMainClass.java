package splitwise;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 04/07/22
 */
public class SplitWiseMainClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String command = "";
		SplitWiseSvc splitWiseService = new SplitWiseSvc();
		while (!command.equalsIgnoreCase("0")) {

			command = scanner.nextLine();
//			System.out.println(command);
			String[] commandDetails = command.split(" ");

//			System.out.println(Arrays.toString(commandDetails));

			if (commandDetails.length == 1 && commandDetails[0].equals("SHOW")) {
				splitWiseService.show();

			} else if (commandDetails.length == 2 && commandDetails[0].equals("SHOW")) {
				splitWiseService.show(commandDetails[1]);
			} else if (commandDetails[0].equals("EXPENSE")) {
				splitWiseService.expense(commandDetails);
			} else {
				System.out.println("Error: Unidentified Input, Please try again");
			}
		}

	}
}

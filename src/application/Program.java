package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Department;
import entities.Worker;
import entities.enums.WorkerLevel;
import services.HourContract;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		
		//Department department = new Department(departmentName);
		
		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
		
		System.out.print("How many contracts to this worker? ");
		int numberOfContracts = sc.nextInt();
		
		for(int i = 0; i < numberOfContracts; i++) {
			System.out.println("Enter contract #" + (i + 1) + " data: ");
			System.out.print("Date (DD/MM/YYYY): ");
			Date date = formatter.parse(sc.next());
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(date, valuePerHour, hours);
			worker.addContract(contract);
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthYear = sc.next();
		/* alternate method:
		 * int month = Integer.parseInt(monthYear.substring(0, 2));
		 * int year = Integer.parseInt(monthYeah.substring(3);
		 */
		String[] dateParts = monthYear.split("/");
		int month = Integer.parseInt(dateParts[0]);
		int year = Integer.parseInt(dateParts[1]);
		double income = worker.income(year, month);
		
		System.out.println("Name: " + workerName);
		System.out.println("Department: " + departmentName);
		System.out.println("Income for " + monthYear + ": " + String.format("%.2f", income));
		
		sc.close();
		
	}

}

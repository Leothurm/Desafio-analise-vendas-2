package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter file full path: ");
		String path = sc.nextLine();
		System.out.println();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			Map<String, Double> sales = new LinkedHashMap<>();
			String line = br.readLine();

			while(line != null) {
				String[] fields = line.split(",");
				
				Integer month = Integer.parseInt(fields[0]);
				Integer years = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);

				if(sales.get(seller) == null) {	
					sales.put(seller,total);
				}else {
					sales.put(seller, total + sales.get(seller));
				}
				
				line = br.readLine();
				
			}

			for(String key : sales.keySet()) {
				System.out.println(key + " - R$ " + String.format("%.2f", sales.get(key)));
			}
			
			
			
			
		} catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		sc.close();
	}

}

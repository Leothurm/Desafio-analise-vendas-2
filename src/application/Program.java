package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;



public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter file full path: ");
		String path = sc.nextLine();
		System.out.println();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Sale> list = new ArrayList<>();
			String line = br.readLine();

			while(line != null) {
				String[] fields = line.split(",");
				
				Integer month = Integer.parseInt(fields[0]);
				Integer years = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);
			    list.add(new Sale(month, years, seller, items, total));
			
			    line = br.readLine();
			}
			
			Map<String, Double> sales = new LinkedHashMap<>();
			
			for(Sale sale : list) {
				if(sales.get(sale.getSeller()) == null) {	
					sales.put(sale.getSeller(),sale.getTotal());
				} else {
					sales.put(sale.getSeller(), sale.getTotal() + sales.get(sale.getSeller()));
				}
		
			}

			
			Map<String, Double> salesOrdered = sales.entrySet()
				.stream()	
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
					
			
			System.out.println("Total de vendas por vendedor em ordem crescente: ");
			salesOrdered.forEach((key, value) -> System.out.println(key  + " - " +  String.format("%.2f",value)));
					
			
		} catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		sc.close();
	}

}

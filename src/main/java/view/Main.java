package view;

import model.Date;

public class Main {
	public static void main(String[] args) {

		// não botei muita coisa aqui pra focar nos testes
		try {
			Date d1 = new Date(31, 12, 2014);
    	var d2 = d1.plusDays(400);
    	var d3 = d1.minusDays(400);
			System.out.println(d2.toString());
			System.out.println(d3.toString());
		} catch (Exception e) {
			System.out.println("\n>> " + e.getMessage());
		}
	}
}
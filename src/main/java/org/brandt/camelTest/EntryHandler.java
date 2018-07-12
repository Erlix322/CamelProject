package org.brandt.camelTest;

import java.util.List;

public class EntryHandler {

	public void printInfo() {
		System.out.println("INFOOOOO");
	}
	
	public List<String> printContent(List<List<String>> csvData) {
			
		return csvData.get(0);
	}
}

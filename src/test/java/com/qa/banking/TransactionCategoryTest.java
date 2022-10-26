package com.qa.banking;

import com.qa.utils.DBUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionCategoryTest {
	private final TransactionCategory TCAT = new TransactionCategory();
	
	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().executeQuery();
	}
	
	@Test
	public void voidTotalForLeisureCategoryIs10Pound50() {
		double totalCost = TCAT.getTotalCostForCategory("Leisure");
		assertEquals(10.50, totalCost);
	}
}
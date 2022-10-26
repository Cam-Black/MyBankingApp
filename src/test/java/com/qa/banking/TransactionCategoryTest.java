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
		DBUtils.getInstance().init("src/test/resources/schema.sql", "src/test/resources/data.sql");
	}
	
	@Test
	public void totalForLeisureCategoryIs10Pound50() {
		double expectedCost = TCAT.getTotalCostForCategory("Leisure");
		assertEquals(10.50, expectedCost);
	}
	
	@Test
	public void totalForGroceriesCategoryIs20Pound80() {
		double expectedCost = TCAT.getTotalCostForCategory("Groceries");
		assertEquals(20.80, expectedCost);
	}
}
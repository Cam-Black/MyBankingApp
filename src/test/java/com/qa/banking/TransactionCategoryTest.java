package com.qa.banking;

import com.qa.utils.DBUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionCategoryTest {
	private final TransactionCategory TCAT = new TransactionCategory();
	
	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testTotalForLeisureCategoryIs10Pound50() {
		double expectedCost = TCAT.getTotalCostForCategory("Leisure");
		assertEquals(10.50, expectedCost);
	}
	
	@Test
	public void testTotalForGroceriesCategoryIs20Pound80() {
		double expectedCost = TCAT.getTotalCostForCategory("Groceries");
		assertEquals(20.80, expectedCost);
	}
	
	@Test
	public void testTotalForAllCategories() {
		String result = TCAT.getTotalCostPerCategory();
		String expected = "Leisure Total Spend: 10.50\nGroceries Total Spend: 20.80\n";
		assertEquals(result, expected);
	}
	
	@Test
	public void testAllTransactionsForLeisure() {
		List<Transaction> transactions = TCAT.getAllTransactionsByCategory("Leisure");
		List<Transaction> expected = new ArrayList<>();
		expected.add(new Transaction(LocalDate.of(2022, 3, 15), "WHSmiths", 10.5, "Leisure"));
		assertEquals(expected.toString(), transactions.toString());
	}
	
	@Test
	public void testAllTransactionsForGroceries() {
		List<Transaction> transactions = TCAT.getAllTransactionsByCategory("Groceries");
		List<Transaction> expected = new ArrayList<>();
		expected.add(new Transaction(LocalDate.of(2022, 8, 21), "Tesco", 10.30, "Groceries"));
		expected.add(new Transaction(LocalDate.of(2022, 5, 16), "Co-Op", 10.5, "Groceries"));
		assertEquals(expected.toString(), transactions.toString());
	}
	
	@Test
	public void testAvgInAMonthForDirectDebit() {
		double directDebitAvg = TCAT.getAllTransactionsInAMonth("Direct Debit");
		assertEquals(150.0, directDebitAvg, 0.0);
	}
}
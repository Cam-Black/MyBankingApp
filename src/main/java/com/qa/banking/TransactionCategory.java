package com.qa.banking;

import com.qa.utils.DBUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionCategory {
	
	public TransactionCategory() {
		super();
	}
	
	public double getTotalCostForCategory(String category) {
		try (Connection conn = DBUtils.getInstance().getConnection();
		     PreparedStatement ps = conn.prepareStatement("SELECT SUM(amount) FROM transactions where category = ?")) {
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public String getTotalCostPerCategory() {
		try (Connection conn = DBUtils.getInstance().getConnection(); Statement stmnt = conn.createStatement()) {
			List<String> categories = new ArrayList<>();
			ResultSet rs = stmnt.executeQuery("SELECT category FROM transactions WHERE category IS NOT NULL");
			StringBuilder sb = new StringBuilder();
			while (rs.next()) {
				categories.add(rs.getString(1));
			}
			
			categories.stream().distinct().forEach(el -> {
				double cost = getTotalCostForCategory(el);
				sb.append(el);
				sb.append(" Total Spend: ");
				sb.append(String.format("%.2f", cost));
				sb.append("\n");
			});
			return sb.toString();
		} catch (Exception e) {
			System.err.println(e);
		}
		return "";
	}
	
	public List<Transaction> getAllTransactionsByCategory(String category) {
		try (Connection conn = DBUtils.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM transactions where category = ? order by transaction_date desc;")) {
			List<Transaction> transactions = new ArrayList<>();
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				transactions.add(modelResults(rs));
			}
			
			return transactions;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public Transaction modelResults(ResultSet rs) throws SQLException {
		LocalDate transactionDate = rs.getDate("transaction_date").toLocalDate();
		String vendor = rs.getString("vendor");
		Double amount = rs.getDouble("amount");
		String category = rs.getString("category");
		return new Transaction(transactionDate, vendor, amount, category);
	}
	
	public double getAvgSpendInAMonth(String category) {
		try (Connection conn = DBUtils.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(
				"SELECT AVG(amount) FROM transactions WHERE category = ? AND  MONTH(transaction_date) = MONTH" +
						"(CURRENT_DATE()) AND YEAR(transaction_date) = YEAR (CURRENT_DATE());")) {
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public double getMinSpend(String category, String year) {
		try (Connection conn = DBUtils.getInstance().getConnection();
		     PreparedStatement ps =
				     conn.prepareStatement(
						     "SELECT MIN(amount) FROM transactions WHERE category = ? AND YEAR(transaction_date) = ?")) {
			ps.setString(1, category);
			ps.setString(2, year);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public double getMaxSpend(String category, String year) {
		try (Connection conn = DBUtils.getInstance().getConnection();
		     PreparedStatement ps =
				     conn.prepareStatement(
						     "SELECT MAX(amount) FROM transactions WHERE category = ? AND YEAR(transaction_date) = ?")) {
			ps.setString(1, category);
			ps.setString(2, year);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
}

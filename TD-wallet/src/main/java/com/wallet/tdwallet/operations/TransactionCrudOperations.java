package com.wallet.tdwallet.operations;

import com.wallet.tdwallet.connection.DBConnection;
import com.wallet.tdwallet.model.Transaction;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperations implements CrudOperations<Transaction> {
    DBConnection dbConnection = new DBConnection();
    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try (Statement statement = dbConnection.getConnection().createStatement()) {
            String query = "SELECT * from transaction ";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(result.getInt("transaction_id"));
                    transaction.setAccountId(result.getInt("account_id"));
                    transaction.setTransactionLabel(result.getString("transaction_label"));
                    transaction.setTransactionAmount(result.getDouble("transaction_amount"));
                    transaction.setTransactionType(result.getString("transaction_type"));
                    Timestamp timestamp = result.getTimestamp("transaction_date");
                    LocalDateTime localDate = timestamp.toLocalDateTime();
                    transaction.setTransactionDate(localDate);

                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching transaction from the database", e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> savedTransactions = new ArrayList<>();
        try {
            for (Transaction transaction : toSave) {
                String query = "INSERT INTO transaction (account_id,transaction_label,transaction_amount,transaction_type, transaction_date) VALUES ( ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, transaction.getAccountId());
                preparedStatement.setString(2, transaction.getTransactionLabel());
                preparedStatement.setDouble(3, transaction.getTransactionAmount());
                preparedStatement.setString(4,transaction.getTransactionType());
                LocalDateTime localDate = transaction.getTransactionDate();
                Timestamp timestamp = Timestamp.from(Instant.from(localDate));
                preparedStatement.setTimestamp(5, timestamp);

                preparedStatement.executeUpdate();
                savedTransactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savedTransactions;
    }

    @Override
    public Transaction save(Transaction toSave) {
        try {
            String query = "INSERT INTO transaction (account_id,transaction_label,transaction_amount,transaction_type, transaction_date) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, toSave.getAccountId());
            preparedStatement.setString(2, toSave.getTransactionLabel());
            preparedStatement.setDouble(3, toSave.getTransactionAmount());
            preparedStatement.setString(4,toSave.getTransactionType());
            LocalDateTime localDate = toSave.getTransactionDate();
            Timestamp timestamp = Timestamp.from(Instant.from(localDate));
            preparedStatement.setTimestamp(5, timestamp);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toSave;
    }
}

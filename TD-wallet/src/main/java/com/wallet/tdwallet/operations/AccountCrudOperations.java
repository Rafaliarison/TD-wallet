package com.wallet.tdwallet.operations;

import com.wallet.tdwallet.connection.DBConnection;
import com.wallet.tdwallet.model.Account;
import com.wallet.tdwallet.model.Currency;
import com.wallet.tdwallet.model.Transaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations<Account> {
    DBConnection dbConnection = new DBConnection();
    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try (Statement statement = dbConnection.getConnection().createStatement()) {
            String query = "SELECT * FROM account LEFT JOIN transaction ON account.id = transaction.account_id";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    Account account = new Account();
                    account.setAccountId(result.getInt("account_id"));
                    account.setAccountName(result.getString("account_name"));
                    account.setAccountType(result.getString("account_type"));
                    account.setAccountCurrency(result.getInt("account_currency"));
                    account.setAccountSolde(result.getDouble("account_solde"));

                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(result.getInt("transaction_id"));
                    transaction.setTransactionLabel(result.getString("transaction_label"));
                    transaction.setTransactionAmount(result.getDouble("transaction_amount"));
                    transaction.setTransactionType(result.getString("transaction_type"));
                    Timestamp timestamp = result.getTimestamp("transaction_type");
                    transaction.setTransactionDate(timestamp.toLocalDateTime());

                    account.getTransactions().add(transaction);

                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users from the database", e);
        }
        return accountList;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        List<Account> savedAccounts = new ArrayList<>();
        try {
            for (Account account : toSave) {
                String query = "INSERT INTO account (account_name, account_type, account_currency, account_solde) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, account.getAccountName());
                preparedStatement.setString(2, account.getAccountName());
                preparedStatement.setInt(3, account.getAccountCurrency());
                preparedStatement.setDouble(4, account.getAccountSolde());

                preparedStatement.executeUpdate();
                savedAccounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savedAccounts;
    }

    @Override
    public Account save(Account toSave) {
        try {
            String query = "INSERT INTO account (account_name, account_type, account_currency, account_solde) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, toSave.getAccountName());
            preparedStatement.setString(2, toSave.getAccountName());
            preparedStatement.setInt(3, toSave.getAccountCurrency());
            preparedStatement.setDouble(4, toSave.getAccountSolde());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toSave;
    }
}

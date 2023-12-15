package com.wallet.tdwallet.service;

import com.wallet.tdwallet.connection.DBConnection;
import com.wallet.tdwallet.model.Account;
import com.wallet.tdwallet.repository.TransactionRepository;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@AllArgsConstructor
public class TransactionService {
    DBConnection connection = new DBConnection();
    Connection dbconnection;
    public Account makeTransaction(int accountId, String transactionLable, double transactionAmount, String transactionType){
        String sql = "insert into transaction(transaction_label, transaction_amount, transaction_type, transaction_date) values (? ,?, ?, now())";
        try(PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, transactionLable);
            preparedStatement.setDouble(2, transactionAmount);
            preparedStatement.setString(3, transactionType);
            preparedStatement.executeUpdate();

        }
    }
}

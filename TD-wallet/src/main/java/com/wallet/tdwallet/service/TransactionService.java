package com.wallet.tdwallet.service;

import com.wallet.tdwallet.connection.Connection;
import com.wallet.tdwallet.model.Account;
import lombok.AllArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@AllArgsConstructor
public class TransactionService {
    Connection connection = new Connection();
    Connection dbConnect = (Connection) connection.connect();
    Account accountBeforeTransaction = getAccountDetails(accountId);
    public Account makeTransaction(int accountid, String transactionLable, double transactionAmount, String transactionType){
        String sql = "insert into transaction(transaction_label, transaction_amount, transaction_type, transaction_date) values (? ,?, ?, now())";
        try(PreparedStatement preparedStatement = connection.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, transactionLable);
            preparedStatement.setDouble(2, transactionAmount);
            preparedStatement.setString(3, transactionType);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int transactionId = generatedKeys.getInt(1);
                double newBalance = (transactionType.equals("debit")) ? accountBeforeTransaction.getSolde() - transactionAmount : accountBefore.getSolde() + transactionAmount ;
            }
        }
    }

}

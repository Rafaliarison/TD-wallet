package com.wallet.tdwallet.repository;

import com.wallet.tdwallet.connection.DBConnection;
import com.wallet.tdwallet.model.Solde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SoldeRepository {
    Connection dbconnection;
    public List<Solde> findAll(){
        String sql = "select * from solde;";
        List<Solde> solde = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbconnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                solde.add(new Solde(
                        resultSet.getInt("soldeId"),
                        (LocalDateTime) resultSet.getObject("soldeDate"),
                        resultSet.getFloat("soldeAmount")
                ));
            }
            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e){
            System.out.println("Error occured while finding all solde :" + e.getMessage());
        }
        return solde;
    }

    public List<Solde> findByDate(){
        String sql = "select account_solde from account where solde_date = ?;";
        List<Solde> soldes = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbconnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                soldes.add(new Solde(
                        resultSet.getInt("soldeId"),
                        (LocalDateTime) resultSet.getObject("soldeDate"),
                        resultSet.getFloat("soldeAmount")
                ));
            }
            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e){
            System.out.println("Can't find the specific solde : " + e.getMessage());
        }
        return soldes;
    }

    public List<Solde> findByIntervalleDate(int accountId, String startDate, String endDate){
        String sql = "select solde.solde_date, account.account_solde " +
                "from account " +
                "join solde on account.account_solde = solde.solde_id " +
                "where account.account_id = ? AND solde.solde_date BETWEEN ? AND ? " +
                "order by solde.solde_date";
        List<Solde> soldeHistory = new ArrayList<>();
        try (
             PreparedStatement preparedStatement = dbconnection.prepareStatement(sql)) {

            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String date = resultSet.getString("solde_date");
                    double balance = resultSet.getDouble("account_solde");
                    soldeHistory.add(new Solde(date, balance));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldeHistory;
    }
}

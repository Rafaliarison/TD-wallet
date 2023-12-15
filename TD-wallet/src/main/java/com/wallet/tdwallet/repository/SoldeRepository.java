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


}

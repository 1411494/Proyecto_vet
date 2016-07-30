package pe.edu.utp.vet.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 19/07/2016.
 */
public class VaccineEntity {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public int getVaccineAction(Vaccine vaccine) {

        try {
            String sql = "{call vaccine_Action (?,?,?,?,?,?)}";
            CallableStatement cst = connection.prepareCall(sql);
            cst.setInt("_vaccine_id", vaccine.getVaccine_id());
            cst.setString("_pet_id", vaccine.getPet_id());
            cst.setString("_weight", vaccine.getWeight());
            cst.setString("_disease", vaccine.getDisease());

            //Format to date
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String _Date = dateFormat.format(vaccine.getDate());
            String _NextDate = dateFormat.format(vaccine.getNext_date());

            cst.setString("_date", _Date);
            cst.setString("_next_date", _NextDate);

            cst.execute();
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public List<Vaccine> getVaccine_List_by_PetId(String  _searchByPet) {

        List<Vaccine> vaccines = new ArrayList<>();

        if (connection == null) return null;

        try {
            String sql = "{call vaccine_List_by_PetId (?)}";
            CallableStatement cst = connection.prepareCall(sql);
            cst.setString("_pet_id", _searchByPet);

            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                Vaccine vaccine1 = new Vaccine();

                vaccine1.setVaccine_id(rs.getInt("vaccine_id"));
                vaccine1.setPet_id(rs.getString("pet_id"));
                vaccine1.setDate(rs.getDate("date"));
                vaccine1.setWeight(rs.getString("weight"));
                vaccine1.setDisease(rs.getString("disease"));

                vaccine1.setNext_date(rs.getDate("next_date"));

                vaccines.add(vaccine1);
            }
            return vaccines;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}

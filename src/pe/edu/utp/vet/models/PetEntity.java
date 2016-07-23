package pe.edu.utp.vet.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 19/07/2016.
 */
public class PetEntity {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public int getPetAction(Pet pet) {

        try {
            String sql = "{call pet_Action (?,?,?,?,?,?,?)}";
            CallableStatement cst = connection.prepareCall(sql);
            cst.setString("_pet_id", pet.getPet_id());
            cst.setString("_dni", pet.getDni());
            cst.setString("_pet_name", pet.getPet_name());
            cst.setString("_breed", pet.getBreed());
            cst.setString("_hair_color", pet.getHair_color());

            java.sql.Date sqlDate = (java.sql.Date) pet.getBirth_date();
            cst.setDate("_birth_date", sqlDate);
            cst.setBoolean("_status", pet.isStatus());

            cst.execute();
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public List<Pet> getPetListByDNI(String _dni) {

        List<Pet> pets = new ArrayList<>();
        if (connection == null) return null;

        try {
            String sql = "{call pet_List_by_DNI (?)}";
            CallableStatement cst = connection.prepareCall(sql);
            cst.setString("_dni", _dni);

            cst.execute();
            ResultSet rs = cst.getResultSet();


            while (rs.next()) {
                Pet pet1 = new Pet();
                pet1.setPet_id(rs.getString("pet_id"));
                pet1.setDni(rs.getString("dni"));
                pet1.setPet_name(rs.getString("pet_name"));
                pet1.setBreed(rs.getString("breed"));
                pet1.setHair_color(rs.getString("hair_color"));
                pet1.setAge_year(rs.getString("age_year"));
                pet1.setAge_month(rs.getString("age_month"));
                pet1.setAge_day(rs.getString("age_day"));
                pet1.setBirth_date(rs.getDate("birth_date"));
                pet1.setStatus(rs.getBoolean("status"));

                pets.add(pet1);
            }
            return pets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


}
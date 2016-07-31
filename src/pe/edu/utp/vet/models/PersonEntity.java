package pe.edu.utp.vet.models;


import javax.faces.convert.Converter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.CallableStatement;

/**
 * Created by Marco on 17/07/2016.
 */
public class PersonEntity {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Person> getPersonsList(String searchBy) {
        List<Person> persons = new ArrayList<>();
        if (connection == null) return null;

        try {
            String sql = "{call person_List (?,?,?)}";
            CallableStatement cst = connection.prepareCall(sql);
            cst.setString("_dni", searchBy);
            cst.setString("_first_name", searchBy);
            cst.setString("_last_name", searchBy);
            //   cst.setString("_dni", "");
            //     cst.setString("_first_name","");
            // cst.setString("_last_name", "");

            cst.execute();
            ResultSet rs = cst.getResultSet();
            while (rs.next()) {
                Person person1 = new Person();
                person1.setDni(rs.getString("dni"));
                person1.setFirst_name(rs.getString("first_name"));
                person1.setLast_name(rs.getString("last_name"));
                person1.setEmail(rs.getString("email"));
                person1.setAddress(rs.getString("address"));
                person1.setPhone_number(rs.getString("phone_number"));
                person1.setBirth_date(rs.getDate("birth_date"));
                person1.setStatus(rs.getBoolean("status"));

                persons.add(person1);
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int getPersonAction(Person person) {

        try {

            String sql = "{call person_Action (?,?,?,?,?,?,?,?)}";
            CallableStatement cst = connection.prepareCall(sql);
            cst.setString("_dni", person.getDni());
            cst.setString("_first_name", person.getFirst_name());
            cst.setString("_last_name", person.getLast_name());
            cst.setString("_email", person.getEmail());
            cst.setString("_address", person.getAddress());
            cst.setString("_phone_number", person.getPhone_number());

            //Format to date
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String _birthDate = dateFormat.format(person.getBirth_date());

            cst.setString("_birth_date", _birthDate);
            cst.setBoolean("_status", person.isStatus());

            cst.execute();
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}

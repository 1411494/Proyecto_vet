package pe.edu.utp.vet.models;

import com.sun.net.httpserver.Filter;
import com.sun.org.apache.regexp.internal.RE;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import java.io.PrintWriter;
import javax.swing.JOptionPane;


/**
 * Created by Marco on 17/07/2016.
 */
@ManagedBean(name = "hrservice")
@SessionScoped

public class HRService {
    private Connection connection;
    private String searchBy;
    /**************************************************************
     * Vaccine Actions
     **************************************************************/
    private Vaccine vaccine;
    /**************************************************************
     * Pet Actions
     **************************************************************/
    private Pet pet;
    /**************************************************************
     * User Actions
     **************************************************************/

    private User user;
    /**************************************************************
     * Person Actions
     **************************************************************/
    private Person person;

    public HRService() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/MySQLDataSourceVet");
            setConnection(ds.getConnection());
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }



    private PetEntity getPetEntity() {
        PetEntity petEntity = new PetEntity();
        petEntity.setConnection(connection);
        return petEntity;
    }

    public void getPetNew() {
        pet = new Pet();
    }




    public void petEdit(Pet _pet) {

        List<Pet> arrayList = getPetListByDni();
        for (Pet pet1 : arrayList) {
            if (pet1.getPet_id().equals(_pet.getPet_id())) {
                Pet petEdit = new Pet();
                petEdit.setDni(pet1.getDni());
                petEdit.setPet_id(pet1.getPet_id());
                petEdit.setPet_name(pet1.getPet_name());
                petEdit.setBreed(pet1.getBreed());
                petEdit.setHair_color(pet1.getHair_color());
                petEdit.setBirth_date(pet1.getBirth_date());
                petEdit.setStatus(pet1.isStatus());

                setPet(petEdit);
            }
        }
    }


     public List<Pet> getPetListByDni()
     {
      return getPetEntity().getPetListByDNI(searchBy);
     }


    public void getPetList(Person _person) {

            searchBy =_person.getDni();

    }

    public void getPetAction() {

        getPetEntity().getPetAction(pet);
    }

    private UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setConnection(getConnection());
        return userEntity;
    }

    public User getUserList_by_DNI(User user) {
        return getUserEntity().getUserList_by_DNI(user);
    }

    public void getUserAction() {
        getUserEntity().getUserAction(user);
    }

    private PersonEntity getPersonEntity() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setConnection(getConnection());
        return personEntity;
    }

    public List<Person> getPersonsList() {

        if (searchBy == null) {
            searchBy = "";
        }

        return getPersonEntity().getPersonsList(searchBy);
    }

    public void getPersonNew() {
        person = new Person();
    }

    public void getPersonAction() {
        getPersonEntity().getPersonAction(person);
    }

    public void personEdit(Person _person) {

        List<Person> arrayList = getPersonsList();
        for (Person person1 : arrayList) {
            if (person1.getDni().equals(_person.getDni())) {
                Person personEdit = new Person();
                personEdit.setDni(person1.getDni());
                personEdit.setFirst_name(person1.getFirst_name());
                personEdit.setLast_name(person1.getLast_name());
                personEdit.setAddress(person1.getAddress());
                personEdit.setEmail(person1.getEmail());
                personEdit.setPhone_number(person1.getPhone_number());
                personEdit.setBirth_date(person1.getBirth_date());
                personEdit.setStatus(person1.isStatus());

                setPerson(personEdit);
            }
        }
    }



    private VaccineEntity getVaccineEntity() {
        VaccineEntity vaccineEntity = new VaccineEntity();
        vaccineEntity.setConnection(connection);
        return vaccineEntity;
    }

    public List<Vaccine> getVaccine_List_by_PetId(Vaccine vaccine) {
        return getVaccineEntity().getVaccine_List_by_PetId(vaccine);
    }

    public void getVaccineAction() {
        getVaccineEntity().getVaccineAction(vaccine);
    }


    /**************************************************************
     * Connection
     **************************************************************/

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    /**************************************************************
     * Person Actions
     **************************************************************/
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    /**************************************************************
     * User Actions
     **************************************************************/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**************************************************************
     * Pet Actions
     **************************************************************/
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**************************************************************
     * Vaccine Actions
     **************************************************************/
    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }


    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }
}

package pe.edu.utp.vet.models;

import com.oracle.jrockit.jfr.UseConstantPool;
import com.sun.net.httpserver.Filter;
import com.sun.org.apache.regexp.internal.RE;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.jws.soap.SOAPBinding;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    /**************************************************************
     * Var
     **************************************************************/

    private Connection connection;
    private Vaccine vaccine;
    private Pet pet;
    private String searchBy;
    private String searchByPet;
    private  String userName;
    private  String userPassword;
    private User user;
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


    /**************************************************************
     * Person Actions
     **************************************************************/


        public  void    getUserNew()
        {
            userName = "";
            userPassword="";
          //  user = new User();
           // user.setUser_name("");
            //user.setPassword("");
        }

    public  void setSearchByNew()
    {
        searchBy = "";
    }

    public void getUserLogin( )
    {
        User UserLogin = new User();
        UserLogin.setUser_name(userName);
        UserLogin.setPassword(userPassword);

       User _user=  getUserEntity().getUserLogin(UserLogin);
        if(_user != null)
        {
            searchBy = _user.getDni();

            if(person == null)
                person = new Person();
            // searchBy ="40102030";
            List<Person> arrayList= getPersonEntity().getPersonsList(searchBy);

            for (Person person1 : arrayList) {
                if (person1.getDni().equals(searchBy)) {

                    Person personView = new Person();
                    personView.setDni(person1.getDni());
                    personView.setFirst_name(person1.getFirst_name());
                    personView.setLast_name(person1.getLast_name());

                    setPerson(personView);
                }
            }

            //  customerListByDni();
        }

    }


    /**************************************************************
     * Person Actions
     **************************************************************/

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

    /**************************************************************
     * Vaccine Actions
     **************************************************************/
    private VaccineEntity getVaccineEntity() {
        VaccineEntity vaccineEntity = new VaccineEntity();
        vaccineEntity.setConnection(connection);
        return vaccineEntity;
    }

    public  void  getVaccineNew()
    {
        vaccine= new Vaccine();
    }
    public void getVaccineList(Pet _pet) {
        searchByPet =_pet.getPet_id();
    }

    public List<Vaccine> getVaccineListbyPetId() {
        return getVaccineEntity().getVaccine_List_by_PetId(searchByPet);
    }

    public void getVaccineAction() {
        getVaccineEntity().getVaccineAction(vaccine);
    }

    public void vaccineEdit(Vaccine _vaccine) {
        List<Vaccine> arrayList = getVaccineListbyPetId();
        for (Vaccine vaccine1 : arrayList) {
            if (vaccine1.getVaccine_id() ==_vaccine.getVaccine_id()) {

                Vaccine vaccine1Edit = new Vaccine();

                vaccine1Edit.setPet_id(vaccine1.getPet_id());
                vaccine1Edit.setVaccine_id(vaccine1.getVaccine_id());
                vaccine1Edit.setWeight(vaccine1.getWeight());
                vaccine1Edit.setDisease(vaccine1.getDisease());
                vaccine1Edit.setDate(vaccine1.getDate());
                vaccine1Edit.setNext_date(vaccine1.getNext_date());

                setVaccine(vaccine1Edit);
            }
        }
    }

    /**************************************************************
     * Pet Actions
     **************************************************************/
    private PetEntity getPetEntity() {
        PetEntity petEntity = new PetEntity();
        petEntity.setConnection(connection);
        return petEntity;
    }

    public void getPetNew() {
        pet = new Pet();
    }

    public List<Pet> getPetListByDni()
    {
        return getPetEntity().getPetListByDNI(searchBy);
    }

    public void getPetList(Person _person) {
        searchBy =_person.getDni();
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

    public void getPetAction() {

        getPetEntity().getPetAction(pet);
    }

    /**************************************************************
     * User Actions
     **************************************************************/

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

    /**************************************************************
     * Search By
     **************************************************************/
    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchByPet() {
        return searchByPet;
    }

    public void setSearchByPet(String searchByPet) {
        this.searchByPet = searchByPet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

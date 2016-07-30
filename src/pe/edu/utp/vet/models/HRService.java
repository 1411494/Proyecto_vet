package pe.edu.utp.vet.models;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.jws.soap.SOAPBinding;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.*;


/**
 * Created by Marco on 17/07/2016.
 */
@ManagedBean(name = "hrservice")
@SessionScoped

public class HRService {

    //region VARIABLE_DECLARATION

    private Connection connection;
    private Vaccine vaccine;
    private Pet pet;
    private User user;
    private User userEdit;
    private Person person;

    private String searchBy;
    private String searchByPet;
    private String userName;
    private String userPassword;

    //endregion;

    //region HR_SERVICE

    public HRService() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/MySQLDataSourceVet");
            setConnection(ds.getConnection());
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    //endregion;

    //region ACTION_PERSON

    private PersonEntity getPersonEntity() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setConnection(getConnection());
        return personEntity;
    }

    public void getPersonNew() {
        person = new Person();
    }

    public List<Person> getPersonsList() {

        if (searchBy == null) {
            searchBy = "";
        }

        return getPersonEntity().getPersonsList(searchBy);
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

    //endregion;

    //region ACTION_PET

    private PetEntity getPetEntity() {
        PetEntity petEntity = new PetEntity();
        petEntity.setConnection(connection);
        return petEntity;
    }

    public void getPetNew() {
        pet = new Pet();
        pet.setImage("resources/Images/Pet/anonimoPet.jpg");
        pet.setStatus(true);
        pet.setDni(person.getDni());
    }

    public List<Pet> getPetListByDni() {
        return getPetEntity().getPetListByDNI(searchBy);
    }

    public void getPetList(Person _person) {
        searchBy = _person.getDni();
        personEdit(_person);
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
                petEdit.setImage(pet1.getImage());
                petEdit.setStatus(pet1.isStatus());

                setPet(petEdit);
            }
        }
    }

    public void getPetAction() {

        getPetEntity().getPetAction(pet);
    }

    //endregion;

    //region ACTION_VACCINE

    private VaccineEntity getVaccineEntity() {
        VaccineEntity vaccineEntity = new VaccineEntity();
        vaccineEntity.setConnection(connection);
        return vaccineEntity;
    }

    public void getVaccineNew() {
        vaccine = new Vaccine();
        vaccine.setPet_id(pet.getPet_id());
    }

    public void getVaccineList(Pet _pet) {
        searchByPet = _pet.getPet_id();
        petEdit(_pet);
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
            if (vaccine1.getVaccine_id() == _vaccine.getVaccine_id()) {

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

    //endregion;

    //region ACTION_USER

    private UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setConnection(getConnection());
        return userEntity;
    }

    public User getUserList_by_DNI(User user) {
        return getUserEntity().getUserList_by_DNI(user);
    }

    public void getUserAction() {
        getUserEntity().getUserAction(userEdit);
    }

    public void getUserEdit(Person _person) {

        User _user = new User();
        _user.setDni(_person.getDni());

        _user = getUserList_by_DNI(_user);

        if (_user.getDni().equals(_person.getDni())) {
            User userEdit = new User();
            userEdit.setDni(_user.getDni());
            userEdit.setUser_name(_user.getUser_name());
            userEdit.setPassword(_user.getPassword());
            userEdit.setType(_user.getType());
            userEdit.setStatus(_user.isStatus());
            setUserEdit(userEdit);
        } else {
            _user.setDni(_person.getDni());
            _user.setStatus(true);
            setUserEdit(_user);
        }

    }

    //endregion;

    //region ACTION_USER_LOGIN

    public void getUserNew() {
        userName = "";
        userPassword = "";
        searchBy = "";
        searchByPet = "";

        userEdit = new User();
    }

    public void setSearchByNew() {
        searchBy = "";
    }

    public void getUserLogin() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();

        User UserLogin = new User();
        UserLogin.setUser_name(userName);
        UserLogin.setPassword(userPassword);

        user = new User();
        user = getUserEntity().getUserLogin(UserLogin);
        if (user != null) {

            if (person == null)
                person = new Person();

            searchBy = user.getDni();
            List<Person> arrayList = getPersonEntity().getPersonsList(user.getDni());
            for (Person person1 : arrayList) {
                if (person1.getDni().equals(user.getDni())) {

                    Person personView = new Person();
                    personView.setDni(person1.getDni());
                    personView.setFirst_name(person1.getFirst_name());
                    personView.setLast_name(person1.getLast_name());
                    personView.setPhone_number(person1.getPhone_number());
                    personView.setAddress(person1.getAddress());
                    setPerson(personView);
                }
            }

            try {
                if (person.getDni().length() > 0) {
                    if (user.getType().equals("A")) //Administrador
                    {
                        searchBy = "";
                        ctx.redirect("personList.xhtml");
                    } else {
                        ctx.redirect("customerView.xhtml");
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //endregion;

    //region PROPERTIES

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

    public User getUserEdit() {

        return userEdit;
    }

    public void setUserEdit(User userEdit) {

        this.userEdit = userEdit;
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

    //endregion;

}

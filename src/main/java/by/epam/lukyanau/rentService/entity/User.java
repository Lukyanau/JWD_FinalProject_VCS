package by.epam.lukyanau.rentService.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class User extends Account {
    public enum Role{
        ADMIN(1),
        USER(2);

        private final int roleId;

        Role(int roleId){this.roleId = roleId;}

        public int getRoleId(){return roleId;}

        private static final Map<Integer,Role> ROLE_MAP = new HashMap<>();

        static {
            for (Role element: values()) {
                ROLE_MAP.put(element.getRoleId(), element);
            }
        }

        public static Role getRoleById(int roleId) {
            return ROLE_MAP.get(roleId);
        }


    }
    private String name;
    private String surname;
    private String login;
    private String email;
    private String phoneNumber;
    private Role role;

    public User(String name, String surname, String login, String email, String phoneNumber, int roleId) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.getRoleById(roleId);
    }
    public User(int userId, String name, String surname, String login, String email, String phoneNumber, int roleId) {
        super(userId);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.getRoleById(roleId);
    }

    public User(BigDecimal balance, byte status, String name, String surname, String login, String email, String phoneNumber, int roleId) {
        super(balance, status);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.getRoleById(roleId);
    }

    public User(BigDecimal balance, int userId, byte status, String name, String surname, String login, String email, String phoneNumber, int roleId) {
        super(balance, userId, status);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.getRoleById(roleId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}

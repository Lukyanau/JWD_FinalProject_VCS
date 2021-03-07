package by.epam.lukyanau.rentService.dao;

public class SqlQuery {
    //User Table
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE login = ?";
    public static final String ADD_USER = "INSERT INTO users(login,name,surname,email,phone,role) VALUES (?,?,?,?,?,?)";

    //Car Table


    //Account Table
    public static final String CHECK_ACCOUNT = "SELECT * FROM accounts WHERE user_id = ?";
    public static final String CREATE_ACCOUNT = "INSERT INTO accounts(balance,id_status,user_id) VALUES (?,?,?)";


    private SqlQuery() {

    }
}

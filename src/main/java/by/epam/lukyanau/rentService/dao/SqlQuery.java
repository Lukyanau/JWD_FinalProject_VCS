package by.epam.lukyanau.rentService.dao;

public class SqlQuery {
    //User Table
    public static final String FIND_ALL_USERS = "SELECT * FROM users WHERE role = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String FIND_USER_ID_BY_LOGIN = "SELECT id FROM users WHERE login = ?";
    public static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String UPDATE_PASSWORD_BY_LOGIN = "UPDATE users SET password = ? WHERE login = ?";
    public static final String ADD_USER = "INSERT INTO users(login,name,surname,email,phone,role) VALUES (?,?,?,?,?,?)";
    public static final String CHECK_STATUS_BY_ID = "SELECT id_status FROM accounts where user_id = ?";
    public static final String BAN_ACCOUNT_BY_LOGIN = "UPDATE accounts SET id_status = (SELECT id FROM account_status WHERE status = 'banned') WHERE user_id = ?";

    //Car Table
    public static final String ADD_CAR = "INSERT INTO cars(model,color,mark,description,price,status) VALUES (?,?,?,?,?,true)";
    public static final String FIND_MARK_ID_BY_MARK = "SELECT id FROM marks WHERE mark_name = ?";


    //Account Table
    public static final String CHECK_ACCOUNT = "SELECT * FROM accounts WHERE user_id = ?";
    public static final String CREATE_ACCOUNT = "INSERT INTO accounts(balance,id_status,user_id) VALUES (?,?,?)";


    private SqlQuery() {

    }
}

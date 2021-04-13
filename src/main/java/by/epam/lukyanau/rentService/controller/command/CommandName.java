package by.epam.lukyanau.rentService.controller.command;

import by.epam.lukyanau.rentService.controller.command.impl.*;
import by.epam.lukyanau.rentService.controller.command.impl.page.*;

public enum CommandName {
    EMPTY_COMMAND(new EmptyCommand(), "empty_command"),
    CHANGE_LANGUAGE(new ChangeLanguage(), "change_language"),
    SIGN_UP(new SignUp(), "sign_up"),
    SIGN_IN(new SignIn(), "sign_in"),
    PASSING_SIGN_UP(new PassingToRegistration(), "passing_sign_up"),
    PASSING_SIGN_IN(new PassingToSignIn(), "passing_sign_in"),
    PASSING_ADMIN_CARS(new PassingToAdminCars(), "passing_admin_cars"),
    PASSING_ERROR(new PassingToErrorPage(), "passing_error"),
    PASSING_HOME(new PassingToHomePage(), "passing_home"),
    PASSING_USERS_ADMIN(new PassingToAdminUsers(), "passing_users_admin"),
    PASSING_CATALOG(new PassingToUserCatalog(), "passing_catalog"),
    PASSING_ADMIN_ORDERS(new PassingToAdminOrders(), "passing_admin_orders"),
    PASSING_USER_PROFILE(new PassingToUserProfile(), "passing_user_profile"),
    PASSING_PAYMENT_CARD(new PassingToPaymentCard(), "passing_payment_card"),
    ADD_CAR(new AddCar(), "add_car"),
    BAN_ACCOUNT(new BanAccount(),"ban_account"),
    UNBAN_ACCOUNT(new UnbanAccount(),"unban_account"),
    SORT_USERS(new SortUsers(),"sort_users"),
    SORT_CARS(new SortCars(),"sort_cars"),
    SORT_ORDERS(new SortOrders(),"sort_orders"),
    ACTIVATE_CAR(new ActivateCar(),"activate_car"),
    DEACTIVATE_CAR(new DeactivateCar(),"deactivate_car"),
    DELETE_CAR(new DeleteCar(),"delete_car"),
    FILTER_CARS(new FilterCars(),"filter_cars"),
    MAKE_ORDER(new MakeOrder(),"make_order"),
    APPROVE_ORDER(new ApproveOrder(),"approve_order"),
    SHOW_ALL_ORDERS(new ShowAllOrders(),"show_all_orders"),
    PAYMENT(new Payment(),"payment"),
    MAKE_DEPOSIT(new MakeDeposit(),"make_deposit"),
    LOG_OUT(new LogOut(), "log_out");


    private final Command command;
    private final String nameCommand;

    CommandName(Command command, String nameCommand) {
        this.command = command;
        this.nameCommand = nameCommand;
    }

    public Command getCommand() {
        return command;
    }

    public String getNameCommand() {
        return nameCommand;
    }
}

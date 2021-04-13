package by.epam.lukyanau.rentService.controller.command.impl;

import by.epam.lukyanau.rentService.controller.Router;
import by.epam.lukyanau.rentService.controller.command.Command;
import by.epam.lukyanau.rentService.controller.command.MessageAttribute;
import by.epam.lukyanau.rentService.controller.command.PagePath;
import by.epam.lukyanau.rentService.controller.command.PropertiesMessageKey;
import by.epam.lukyanau.rentService.controller.command.impl.util.CommandUtil;
import by.epam.lukyanau.rentService.entity.User;
import by.epam.lukyanau.rentService.service.exception.ServiceException;
import by.epam.lukyanau.rentService.service.impl.UserServiceImpl;
import by.epam.lukyanau.rentService.service.validator.PaymentCardValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

import static by.epam.lukyanau.rentService.service.util.RequestParameterName.*;

public class MakeDeposit implements Command {
    private UserServiceImpl service = UserServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();

        try {
            String numberCard = request.getParameter(NUMBER_CARD);
            String dateCard = request.getParameter(DATE_CARD);
            String cvCodeCard = request.getParameter(CV_CODE_CARD);
            String transferAmountString = request.getParameter(TRANSFER_AMOUNT_CARD);
            Map<String, String> checkedData = PaymentCardValidator.validateParameters(numberCard,
                    dateCard, cvCodeCard, transferAmountString);

            if (PaymentCardValidator.defineIncorrectValues(checkedData)) {
                User currentUser = (User) session.getAttribute(MessageAttribute.USER);
                double transferAmountValue = Double.parseDouble(transferAmountString);
                Optional<User> userOptional = service.findUserById(currentUser.getUserId());
                if (userOptional.isPresent()) {
                    User updatedUser = userOptional.get();
                    service.depositMoney(updatedUser.getLogin(), transferAmountValue);
                    session.setAttribute(MessageAttribute.USER, updatedUser);
                }
                request.setAttribute(MessageAttribute.DEPOSIT_MESSAGE, transferAmountString);
                router = new Router(PagePath.NOTIFICATION);
            } else {
                String locale = (String) session.getAttribute(MessageAttribute.LANGUAGE);
                String messageWithLocale = CommandUtil.makePartWithLocale(locale, PropertiesMessageKey.CREDIT_CARD_ERROR_MESSAGE);
                request.setAttribute(MessageAttribute.PAYMENT_ERROR_MESSAGE, messageWithLocale);
                request.setAttribute(MessageAttribute.PAYMENT_CARD_DATA, checkedData);
                router = new Router(PagePath.PAYMENT_CARD);
            }
        } catch (ServiceException exp) {
            LOGGER.error(exp);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}


import com.h2.*;

import java.util.Arrays;
import java.util.Map;

public class Finance {
    final public static String BEST_LOAN_RATES = "bestLoanRates";
    final public static String SAVINGS_CALCULATOR = "savingsCalculator";
    final public static String MORTGAGE_CALCULATOR = "mortgageCalculator";

    final public static Map<String,String> commandsToUsage = Map.of(BEST_LOAN_RATES, "usage: bestLoanRates",
            SAVINGS_CALCULATOR, "usage: savingsCalculator <credits separated by ','> <debits separated by ','>",
            MORTGAGE_CALCULATOR, "usage: mortgageCalculator <loanAmount> <termInYears> <annualRate>");

    private static boolean validateCommandArguments(String[] args)
    {
        switch (args[0])
        {
            case BEST_LOAN_RATES:
                return args.length == 1;
            case SAVINGS_CALCULATOR:
                return args.length == 3;
            case MORTGAGE_CALCULATOR:
                return args.length == 4;
            default:
                return false;
        }
    }

    private static void executeCommand(String command, String[] arguments)
    {
        switch (command)
        {
            case BEST_LOAN_RATES:
                System.out.println("Finding best loan rates ...");
                BestLoanRates.main(arguments);
                return;
            case SAVINGS_CALCULATOR:
                System.out.println("Finding your net savings ...");
                SavingsCalculator.main(arguments);
                return;
            case MORTGAGE_CALCULATOR:
                System.out.println("Finding your monthly payment ...");
                MortgageCalculator.main(arguments);
        }
    }

    public static void main(String[] args) {
        String command = args[0];
        if (commandsToUsage.containsKey(command))
        {
            boolean isValidCommand=validateCommandArguments(args);
            if (isValidCommand)
            {
                executeCommand(command, Arrays.copyOfRange(args, 1, args.length));
            }
            else
            {
                System.out.println(command + ": command not found");
            }
        }
        else
        {
            System.out.println(command + ": command not found");
        }
    }
}

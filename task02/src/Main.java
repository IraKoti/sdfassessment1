import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main
 */
public class Main {
    private static double last = 0;
    private static String[] operator = {"+","-","/","*"};
    private static final List<String> operatorList = new ArrayList<>(Arrays.asList(operator));
    private static double aNumber = 0;
    private static double bNumber = 0;

    public static void main(String[] args) {
        start();

    }
    
    public static void start()
    {
        System.out.println("Welcome.");
        process();
    }

    public static void process()
    {
        String input,opr;
        String[] aInput;
        boolean exit = false;
        Console cons = System.console();
        while(!exit)
        {      
            input = cons.readLine("> ");
            aInput = input.split(" ");
            if(aInput[0].equals("exit"))
            {
                exit = true;
                System.out.println("Bye bye");
            }
            else
            {
                aNumber = checkLast(aInput[0]);
                opr = aInput[1];
                bNumber = checkLast(aInput[2]);;
                // for (String a : operatorList) 
                // {
                //     if((right = opr.contains(a)))
                //     {          
                //         break;
                //     }
                //     System.out.println(right);
                // }
                switch (opr) {
                    case "+":
                        last = aNumber + bNumber;
                        System.out.println(last);
                        setLast(last);
                        break;
                    case "-":
                        last = aNumber - bNumber;
                        System.out.println(last);
                        setLast(last);
                        break;
                    case "*":
                        last = aNumber * bNumber;
                        System.out.println(last);
                        setLast(last);
                        break;
                    case "/":
                        last = aNumber / bNumber;
                        System.out.println(last);
                        setLast(last);
                        break;
                    default:
                        System.out.println("Operator not found");
                        break;
                }
            }
        }

        

    }

    public static double checkLast(String value)
    {
        if(value.equals("$last"))
        {
            return getLast();
        }
        else
        {
            return Double.parseDouble(value);
        }
    }

    public static double getLast() {
        return last;
    }

    public static void setLast(double last) {
        Main.last = last;
    }
}
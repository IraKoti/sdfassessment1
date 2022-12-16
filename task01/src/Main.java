import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        mainProcess(args);
    }

    public static void mainProcess(String[] args)
    {
        // String line = "Thank you for staying with us over these <<years>> years.";
        // // Pattern p = Pattern.compile(".*\\<<.*");
        // // Matcher m = p.matcher(line);
        // // boolean b = m.matches();
        // // System.out.println(m.start());
        // // System.out.println(b);
        // System.out.println(line.indexOf("<<"));
        // System.out.println(line.lastIndexOf(">>"));
        // Integer begin = line.indexOf("<<")+2;
        // Integer last = line.lastIndexOf(">>");
        // System.out.println(line.substring(begin, last));
        
        String firstFile, secondFile;
        if(args.length<2)
        {
            System.out.println("Please enter 2 file names.");
            System.exit(1);
        }
        else
        {
            firstFile = args[0];
            secondFile = args[1];
            FileProcess fc = new FileProcess();
            fc.Check(firstFile);
            fc.Check(secondFile);
            fc.process();
        }
    }
}

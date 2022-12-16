import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcess {
    private String csvFile;
    private String txtFile;
    private String[] keySplit;
    private String[] valueSplit;

    public void Check(String fileName)
    {
            File file = new File(fileName);
            if(file.exists())
            {
                //System.out.printf("%s file is found.\n",fileName);
                // for checking which is csv file and which is txt file
                if(fileName.endsWith(".csv"))
                {
                    setCsvFile(fileName);
                }
                else if(fileName.endsWith(".txt"))
                {
                    setTxtFile(fileName);
                }
            }
            else
            {
                System.out.printf("%s file is not found.\n",fileName);
                System.exit(1);
            }

    }

    public void process()
    {
        readCsv();
    }

    public void readCsv()
    {
        String line;
        Integer position;
        //String[] keySplit, valueSplit;
        //List<String> key = new ArrayList<>();
        Map<String, String> csvMap = new LinkedHashMap<>();
        try {
            Reader csv = new FileReader(getCsvFile());
            //BufferedReader br = new BufferedReader(csv);
            LineNumberReader lnr = new LineNumberReader(csv);
            while(null != (line = lnr.readLine()))
            {
                position = lnr.getLineNumber();
                //System.out.printf("position: %d\n line: %s\n",position,line);
                if(position==1)
                {
                    keySplit = line.split(",");
                    //System.out.println("key "+keySplit.length);
                    for(Integer a=0; a<keySplit.length; a++)
                    {
                        //System.out.println(keySplit[a]);
                        csvMap.put(keySplit[a], " ");
                        //System.out.println("csv map:"+csvMap);
                    }
                }
                else
                {
                    valueSplit = line.split(",");
                    //System.out.println(valueSplit.length);
                    for(Integer b=0; b<valueSplit.length; b++)
                    {
                        //System.out.println("value "+valueSplit[b]);
                        csvMap.replace(keySplit[b],valueSplit[b]);
                        
                    }
                    writeTxtFile(csvMap,position);
                    
                }
                //System.out.println("csv map:"+csvMap);
            }
            
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.printf("%s file is not found.\n",getCsvFile());
        }
        catch (IOException e) {
            // TODO: handle exception
            System.out.printf("%s file is unreadable.\n",getCsvFile());
        }
        
    }

    public void writeTxtFile(Map<String,String> mapValue, Integer position)
    {
        String line;
        String payload="";
        String newFileTxt = getTxtFile().replace(".txt", "")+position+".txt";
        try {
            Reader file = new FileReader(getTxtFile());
            BufferedReader br = new BufferedReader(file);
            System.out.println("text file "+getTxtFile());
            File fnt = new File(newFileTxt);
            if(!fnt.exists())
            {
                fnt.createNewFile();
            }
            Writer wf = new FileWriter(fnt);
            BufferedWriter bw = new BufferedWriter(wf);
            while (null != (line = br.readLine())) 
            {
                //System.out.println(line);
                payload = replaceLine(line,mapValue);
                bw.write(payload);
                bw.newLine();
                bw.flush();
                //System.out.println("payload:"+payload);
            }
            bw.close();
            br.close();

            // System.out.println(line.indexOf("<<"));
            // System.out.println(line.lastIndexOf(">>"));
            // Integer begin = line.indexOf("<<")+2;
            // Integer last = line.lastIndexOf(">>");
            // System.out.println(line.substring(begin, last));
            
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.printf("%s file is not found.\n",getCsvFile());
        }
        catch (IOException e) {
            // TODO: handle exception
            System.out.printf("%s file is unreadable.\n",getCsvFile());
        }
    }

    public String replaceLine(String line, Map<String,String> mapValue)
    {
        //System.out.println("line1 : "+line);
        //System.out.println("map : "+mapValue);
        String newLine=line, wordKey;
        String oldWord, newWord;
        boolean exit = false;
        Pattern p = Pattern.compile(".*\\<<.*");
        Matcher m = p.matcher(line);
        boolean b = m.matches();
        //System.out.println("result found"+b);
        if(m.matches())
        {  
                Integer begin = line.indexOf("<<");
                //Integer last = line.lastIndexOf(">>");
                Integer last = line.indexOf(">>");
                oldWord = line.substring(begin,last+2);
                wordKey = line.substring(begin+2, last);
                newWord = mapValue.get(wordKey);
                // System.out.println("wordKey: "+ wordKey);
                // System.out.println("old word: "+oldWord);
                // System.out.println("new word: "+newWord);
                newLine = line.replace(oldWord, newWord);
                if(newLine.contains("\\n"))
                {
                    System.out.println("line n found");
                    newLine = newLine.replaceAll(".*\\n.*", "\\r\n");
                }
                // System.out.println("wordKey: "+ wordKey);
                // System.out.println("old word: "+oldWord);
                // System.out.println("new word: "+newWord);
                //System.out.println("new line: "+newLine);
                line = newLine;
            }
        
        return newLine;
    }

    public String getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public String getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(String txtFile) {
        this.txtFile = txtFile;
    }
}

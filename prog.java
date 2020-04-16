import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


class MainClass{
    String directoryPath;
    String fileName;

    public MainClass(String directoryPath, String fileName){
        directoryPath = directoryPath;
        fileName = fileName;
    }
}


class prog{

    public static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
      }


    public static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command, pro.getInputStream());
      }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String filePath;
        filePath = args[0];
        System.out.println("Entered path: " + filePath+"\n");
        //System.out.println(" ");
        //System.out.println("here");
        
    
        File file = new File(filePath);
        File[] listOfFiles = file.listFiles();
        File tempFile;
        File[] listOfFiles2;
        while(1>0){
            tempFile = new File(filePath);
            listOfFiles2 = tempFile.listFiles();
            if(tempFile.exists() && tempFile.isDirectory()){
                //System.out.println("voila!!");
                System.out.println("Showing the contents of directory...");
                for (int i = 0; i < listOfFiles2.length; i++) {
                    if (listOfFiles2[i].isFile()) {
                        System.out.print(listOfFiles2[i].getName() + "   ");
                    } else if (listOfFiles2[i].isDirectory()) {
                        System.out.println(listOfFiles2[i].getName() + "   ");
                    }
                }
                break;
            }
            else{
                System.out.println("Entered path doesn't exists in file system!");
                System.out.println("Please enter path again: ");
                String pathReEnter = input.nextLine();
                filePath = pathReEnter;
                String newCommand = "java prog " + pathReEnter;
                System.out.println(newCommand);
                //System.exit(0);
            }
        }

        
        System.out.println(" ");
        String enteredFileName;
        
        int choice;
        System.out.println(" ");
        //System.out.println("here");
        System.out.print("Enter name of file to open: ");
        enteredFileName = input.nextLine();
        //System.out.println(enteredFileName);
        //System.out.println("here");
        MainClass object = new MainClass(filePath, enteredFileName);

        String temp;

        String line = null;
        String dataToBeWritten;
        
        int checkPoint = 0;

        for(int i = 0;i < listOfFiles2.length;i++){
            if(listOfFiles2[i].isFile()){
                temp = listOfFiles2[i].getName();
                if(temp.equals(enteredFileName)){
                    checkPoint = checkPoint +1;
                    System.out.println("File "+"'" +temp+"'" + " is now selected!");
                    System.out.println(" ");
                    System.out.println("Size of selected file on disk: " + listOfFiles2[i].length() + "bytes");
                    System.out.println("Displaying file permissions: "); 
                    System.out.println("Readable: " + tempFile.canRead()); 
                    System.out.println("Writable: "+ tempFile.canWrite()); 
                    System.out.println(" ");
                    try{
                        FileWriter fileWriter = new FileWriter(temp, true);//true means haa append
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        System.out.print("Enter message to be written on file: ");
                        dataToBeWritten = input.nextLine();
                        bufferedWriter.newLine();
                        bufferedWriter.write(dataToBeWritten);
                        bufferedWriter.close();
                        
                    }
                    catch(IOException ex){
                        System.out.println("Error writing to file!");
                    }
                            
                            
                       System.out.println(" ");
                       System.out.println("Displaying contents of file "+"'"+temp+"'");

                                try{
                                    FileReader fileReader = new FileReader(temp);
                                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                                    while((line = bufferedReader.readLine()) != null){
                                        System.out.println(line);
                                    }
                                    bufferedReader.close();
                                }
                                catch(FileNotFoundException ex){
                                    System.out.println("File named "+ "'" +temp+"'"+" doesn't exist on file system!");
                                }
                                catch(IOException ex){
                                    System.out.println("Error reading file!");
                                }
                            }
                            
                    }
                    

                }
                if(checkPoint == 0){
                    System.out.println("File named '"+ enteredFileName+"' doesn't exist on file system!");
                }
            }
}
        
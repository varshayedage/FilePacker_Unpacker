
// ---------------------------------PROJECT FILE PACKER UNPACKER------------------------------------------------

import java.util.*;
import java.io.*;

class FilePackerUnpacker {
    public static void main(String args[]) throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("---------- Marvellous Packer Unpacker ----------");
        System.out.println("------------------------------------------------");

        String UserChoice = null;

        do {
            System.out.println("****************************************************************");
            System.out.println("You want to paked the files or You want to unpacked the file : ");
            System.out.println("****************************************************************");
            System.out.println("pack   : For Packing Purpose Please enter pack ");
            System.out.println("unpack : For Unpacking Purpose Please enter unpack ");
            System.out.println("exit   : For Exit please enter exit ");
            System.out.println("****************************************************************");

            Scanner sobj = new Scanner(System.in);
            UserChoice = sobj.nextLine();

            switch (UserChoice) {
                case "pack":

                    boolean bRet = false;

                    System.out.println("Enter the name of Directory which contains all files that you want to pack :");
                    String DirectoryName = sobj.nextLine();

                    System.out.println("Enter the name of packed file that you want to create :");
                    String PackedFile = sobj.nextLine();

                    File fobjPack = new File(PackedFile);

                    bRet = fobjPack.createNewFile();
                    if (bRet == false) {
                        System.out.println("Unable to create packed file...");
                        return;
                    }

                    File fobj = new File(DirectoryName);

                    bRet = fobj.isDirectory();
                    if (bRet == true) {
                        System.out.println("Directory is present");
                        File Arr[] = fobj.listFiles();
                        System.out.println("Number of files in the directory are : " + Arr.length);

                        String Header = null;

                        // To write the data into packed file
                        FileOutputStream fcombine = new FileOutputStream(PackedFile);

                        int iRet = 0;
                        byte Buffer[] = new byte[1024];

                        System.out.println("Packing activity started...");

                        // Travel Directory
                        for (int i = 0; i < Arr.length; i++) {
                            // Create header
                            Header = Arr[i].getName() + " " + Arr[i].length();
                            System.out.println("File packed with the name : " + Arr[i].getName());

                            // add extra white spaces at end of header
                            for (int j = Header.length(); j < 100; j++) {
                                Header = Header + " ";
                            }

                            // Convert string header into byte array
                            byte hArr[] = Header.getBytes();

                            // Write header into packed file
                            fcombine.write(hArr, 0, 100);

                            // To read the file from directory
                            FileInputStream fiobj = new FileInputStream(Arr[i]);

                            // write the data into packed file after header
                            while ((iRet = fiobj.read(Buffer)) != -1) {
                                fcombine.write(Buffer, 0, iRet);
                            }
                            fiobj.close();
                        }
                        System.out.println("Packing activity completed..");
                        System.out.println("Total file packed succesfully : " + Arr.length);

                        System.out.println("------------------------------------------------");
                        System.out.println("Thank you for using Marvellous Packer Unpacker");
                        System.out.println("------------------------------------------------");

                    } else {
                        System.out.println("There is no such directory");
                    }
                    break;
                case "unpack":
                    byte Header[] = new byte[100];
                    int FileSize = 0;
                    String str = null;
                    // Scanner sobj = new Scanner(System.in);
                    int iRet = 0;
                    int iCnt = 0;

                    System.out.println("Enter the name of packed file that you want to unpack : ");
                    String PackedFile1 = sobj.nextLine();

                    File fobj1 = new File(PackedFile1);
                    FileInputStream fiobj = new FileInputStream(fobj1);

                    while ((iRet = fiobj.read(Header, 0, 100)) > 0) {
                        String Hstr = new String(Header);

                        str = Hstr.trim();
                        String Tokens[] = str.split(" ");

                        File NewFile = new File(Tokens[0]);
                        NewFile.createNewFile();

                        FileSize = Integer.parseInt(Tokens[1]);

                        byte Buffer[] = new byte[FileSize];
                        fiobj.read(Buffer, 0, FileSize);

                        FileOutputStream foobj = new FileOutputStream(NewFile);
                        foobj.write(Buffer, 0, FileSize);

                        System.out.println(Tokens[0] + " unpacked succesfully");

                        iCnt++;
                    }
                    System.out.println("------------------------------------------------");

                    System.out.println("Unpacking activity completed...");
                    System.out.println("Total file unpacked succesfully : " + iCnt);

                    System.out.println("------------------------------------------------");
                    System.out.println("Thank you for using Marvellous Packer Unpacker");
                    System.out.println("------------------------------------------------");

                    break;

                case "exit":
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Please enter pack or unpack.......");
                    break;
            }
        } while (!UserChoice.equalsIgnoreCase("exit"));
    }
}

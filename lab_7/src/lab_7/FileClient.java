package lab_7;


import java.io.*;
import java.net.*;

public class FileClient {

    private DatagramSocket socket = null;
    private FileHandler event = null;
    private String sourceFilePath;
    private String destinationPath;
    private String hostName;

    public FileClient() {
        this.hostName = "localHost";
        this.sourceFilePath = "C:/Users/admin/Desktop/eere.txt";
        this.destinationPath = "E:/Ayya Data/";
    }
    
    public void createConnection() {
        try {
            //send the file out to the server
            socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(hostName);
            byte[] incomingData = new byte[1024];
            //The file that is to be sent
            event = getFileHandler();
            
            //send the file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(event);
            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9871);
            socket.send(sendPacket);
            System.out.println("File sent from client");
            
            //receive confirmation
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            socket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);
            //Thread.sleep(2000);
            System.exit(0);
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileHandler getFileHandler() {
        FileHandler  fileHandle = new FileHandler();
        String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
        String path = sourceFilePath.substring(0, sourceFilePath.lastIndexOf("/") + 1);
         fileHandle.setDestinationDirectory(destinationPath);
         fileHandle.setFilename(fileName);
         fileHandle.setSourceDirectory(sourceFilePath);
        File file = new File(sourceFilePath);
        if (file.isFile()) {
            try {
                //Read the file and build the file handle

DataInputStream diStream = new DataInputStream(new FileInputStream(file));
long len = (int) file.length();
byte[] fileBytes = new byte[(int) len];
int read = 0;
int numRead = 0;
while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
read = read + numRead;
}
fileHandle.setFileSize(len);
fileHandle.setFileData(fileBytes);
fileHandle.setStatus("Success");
                
                
                
                
                
                
                
                
            } catch (Exception e) {
                e.printStackTrace();
                 fileHandle.setStatus("Error");
            }
        } else {
            System.out.println("path specified is not pointing to a file");
             fileHandle.setStatus("Error");
        }
        return  fileHandle;
    }

    public static void main(String[] args) {
        FileClient client = new FileClient();
        client.createConnection();
    }

}

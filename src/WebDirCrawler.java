import java.io.*;
import java.net.*;
import java.util.*;

public class WebDirCrawler {
    private String targetUrl;
    private String dirListPath;

    WebDirCrawler(String targetUrl, String dirListPath){
        this.targetUrl = targetUrl;
        this.dirListPath = dirListPath;
    }
    public String getTargetUrl() {
        return targetUrl;
    }
    public String getDirListPath(){
        return dirListPath;
    }
    public void setTargetUrl(String targetUrl){
        if(targetUrl.equals("")){
            this.targetUrl = "http://www.example.com/";
        } else {
            this.targetUrl = targetUrl;
        };
    }
    public void setDirListPath(String dirListPath){
        if(dirListPath.equals("")){
            this.dirListPath = "../../../dirList/topimDir.txt";
        } else {
            this.dirListPath = dirListPath;
        };
    }
    public int testPing(){
        int returnValue;

        try {
            returnValue = this.dirRequest("/");
        } catch (java.net.MalformedURLException urlExc){
            returnValue = 0;
        } catch (java.io.IOException ioExc){
            returnValue = 1;
        }

        return returnValue;
    }
    public String startAttack(){
        String returnValue = "attack succesfull";
        // banner
        System.out.print("attack started");
        System.out.print("target url:     " + this.targetUrl);
        System.out.print("directory list: " + this.dirListPath);

        // read word list file
        try {
            File dirList  = new File(this.dirListPath);
            Scanner readList = new Scanner(dirList);
            while(readList.hasNext()){
                // made request
                System.out.println(readList.nextLine());
                String currentLine = readList.nextLine();
                int reqCode = this.dirRequest(currentLine);
                if(reqCode == 200){
                    System.out.print("200:  " + currentLine + "\n");
                } else if(reqCode == 500){
                    System.out.print("500:  " + currentLine + "\n");
                }
                System.out.print("req:  " + currentLine + "\r");
            }
        } catch(FileNotFoundException fileExc){
            returnValue = "error: directory list not found";
        } catch(MalformedURLException urlExc){
            returnValue = "error: malformed url target";
        } catch(IOException ioExc){
            returnValue = "error: i/o operation falied";
        }

        return returnValue;
    }
    private int dirRequest(String dir) throws java.net.MalformedURLException, java.io.IOException {
        int requestCode;

        URL reqUrl = new URL(this.targetUrl);
        HttpURLConnection reqCon = (HttpURLConnection) reqUrl.openConnection();
        reqCon.setRequestMethod("GET");
        reqCon.setConnectTimeout(1000);
        reqCon.connect();

        requestCode = reqCon.getResponseCode();

        return requestCode;
    }
}

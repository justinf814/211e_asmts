import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Justin Foreman
 * Date:    1/27/14
 * Homework Assignment : 1 (Client)
 * Objective:   This program gets an IPv4 or FQDN address and sends
 *              it to LittleDNS, then receives the converted values.
 */

class LittleDNSClient
{
    public static void main(String[] args) throws IOException
    {
        Scanner clientIn = new Scanner(System.in);
        
        System.out.println("Enter an IP or FQDN: ");
        String toResolve = clientIn.nextLine().trim();
                
        try (Socket s = new Socket("127.0.0.1", 41586))
        {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();
            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true);
            ArrayList<String> line = new ArrayList<String>();
            
            out.println(toResolve);
            
            while (in.hasNextLine())
            {
                line.add(in.nextLine());
            }
                
            for (String l : line )
            {
                System.out.println(l);
            }
            
            in.close();
            clientIn.close();
        }
    }
}
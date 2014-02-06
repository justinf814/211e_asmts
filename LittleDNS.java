import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Justin Foreman
 * Date:    1/27/14
 * Homework Assignment : 1 (Server)
 * Objective:   This program is a server that receives an IPv4 address
 *              or an FQDN and returns a human readable DNS or Reverse DNS
 *              lookup.
 */

public class LittleDNS implements Runnable
{
	public static void main(String[] args)
	{	
	    Runnable r = new LittleDNS();
		new Thread(r).start();
	}
	
//**************run()**********************************************************
	public void run()
	{
	    try (ServerSocket ss = new ServerSocket(41586))
	    {
            Socket s = ss.accept();
	        try
	        {
	            InputStream inStream = s.getInputStream();
	            OutputStream outStream = s.getOutputStream();
	            Scanner in = new Scanner(inStream);
	            PrintWriter out = new PrintWriter(outStream, true);
	            String line = in.nextLine().trim();
	            
	            if (line.matches("\\D+"))
	            {
	                InetAddress[] address = InetAddress.getAllByName(line);
	                for (InetAddress a : address)
	                    out.println(a);
	            }
	            else
	            {
	                InetAddress address = InetAddress.getByName(line);
	                String hostname = address.getCanonicalHostName();
	                out.println(line + " resolves to " + hostname);
	            }
	            
	            in.close();
	        }
	        finally
	        {
	            s.close();
	        }
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
    } 
}


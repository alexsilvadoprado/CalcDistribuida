package operatorServer.launch;

import operatorServer.OperatorServer;
import base.operator.OperatorType;

/**
 * 
 * Lan�ador do servidor de opera��es
 *
 */
public class OperatorServerLauncher
{
	public static void main(String[] args) throws Exception 
	{
        if (!isArgsValid(args)) 
        {
            showUsageMode();
            System.exit(1);
        }

        OperatorType operatorType =  OperatorType.valueOf(args[0]);

        try 
        {
            OperatorServer operatorServer = new OperatorServer(operatorType);
            Thread thread = new Thread(operatorServer);
            thread.start();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	
	private static boolean isArgsValid(String[] args) 
	{
        boolean returnValue = true;

        if (args.length != 1) 
        {
            returnValue = false;
        } else 
        {
            try 
            {
                OperatorType.valueOf(args[0]);
                returnValue = true;
            } catch (Exception e) 
            {
                returnValue = false;
            }
        }

        return returnValue;
    }
	
	private static void showUsageMode() 
	{
        String msg = "uso: java -jar OperatorServer.jar [tipo]\n";
        msg+="\nonde tipo pode ser:\n";

        OperatorType[] types = OperatorType.values();
        for (OperatorType type: types)
        {
            msg+=type+"\n";
        }
        System.out.println(msg);
    }
}

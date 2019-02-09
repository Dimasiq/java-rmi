import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyClient{
    public static void main(String[] args) throws RemoteException, NotBoundException{
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Registry registry = LocateRegistry.getRegistry("127.0.0.1");
        MyRemoteInterface stub = (MyRemoteInterface) registry.lookup("MyRemoteObject");

        System.out.println("Increased number: " + stub.getIncreasedNumber(1));
    }
}
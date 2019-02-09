import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteServer {
    public static void main(String[] args){
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        MyRemoteInterface myRemoteObject = new MyRemoteObject();

        try{
            MyRemoteInterface stub = (MyRemoteInterface) UnicastRemoteObject.exportObject(myRemoteObject, 0);
            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("MyRemoteObject", stub);
            System.out.println("Bound 'MyRemoteObject'");
        }catch(Exception e){
            System.out.println("RMI error: " + e);
        }
    }
}

interface MyRemoteInterface extends Remote {
    Integer getIncreasedNumber(Integer number) throws RemoteException;
}

class MyRemoteObject implements MyRemoteInterface {

    @Override
    public Integer getIncreasedNumber(Integer number) throws RemoteException {
        return number + 1;
    }
}

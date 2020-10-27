import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		Servidor ser = new Servidor();
		Cliente cli = new Cliente();
		ser.start();
		cli.start();
	}

}

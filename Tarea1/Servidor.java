import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Servidor extends Thread{
	// Iniciamos
	Scanner lee = new Scanner(System.in);
	// Declaramos variables de tipo Socket que vamos a usar
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteAEnviar;
	int puerto;
	// Constructor para enviar el puerto
	public Servidor() { puerto = 9990; }
	@Override
	// Funcion para iniciar el Servidor
	public void run() {
		try {
			// Creamos un socket bajo UDP
			socketUDP = new DatagramSocket(puerto);
			while (true) {
				// -- RECIBIENDO EL PAQUETE
				// Declaramos nuestro paquete el cual recibiremos
				paqueteRecibido = new DatagramPacket(new byte[1024], 1024);
				// Recibe el paquete
				socketUDP.receive(paqueteRecibido);
				//System.out.println("Llego un paquete...");
				// Recibiendo vector e bytes y convirtiendo a cadena
				String cadenaRecibido = new String(paqueteRecibido.getData());
				//System.out.println("Solicitud Recibida: " + cadenaRecibido);
				// -- ENVIANDO EL PAQUETE
				String cad = "";
				int num = Integer.parseInt(cadenaRecibido.substring(0, 1));
				switch (num) {
				case 1:
					System.out.println("Ingrese su Nombre: ");
					String n = lee.next();
					System.out.println("Ingrese su C.I.: ");
					String ca= lee.next();
					String guardar;
					guardar="La Persona: "+n+"  con C.I.: "+ca;
					System.out.println(guardar);
					System.out.println("Se guardo con éxito :)");
					break;
				case 2:
					do {
					System.out.println("Ingrese su C.I.:");
					String ca1= lee.next();
			/*		if(ca1.equals(ca)) {
						System.out.println("Nombre: "+n);
						System.out.println("C.I.: "+ca);
						System.out.println("Puerto: "+puerto);
						break;
					}else {
						System.out.println("No se encuentra");}
			*/		} while (num!=0);
				default:
					break;
				}
				byte mensajeEnviar[] = new byte[1024];
				// Convierte cadena a Vector de Bytes
				mensajeEnviar = cad.getBytes();
				// Se crea el datagrama que contendra el mensaje
				paqueteAEnviar = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, paqueteRecibido.getAddress(),
						paqueteRecibido.getPort());
				// Luego se Envia el paquete al cliente
				socketUDP.send(paqueteAEnviar);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Funcion para finalizar la conexion
	public void finalizar() {
		try {
			socketUDP.close();
			System.out.println("Conexion finalizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
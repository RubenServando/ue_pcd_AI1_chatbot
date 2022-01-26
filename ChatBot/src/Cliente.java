import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//	Solicita la IP del servidor de productos --> 127.0.0.1 (local host)
		String serverAddress = System.console().readLine("Introduzca la IP de nuestro servidor de productos"
														+ " y conozca nuestros descuentos: \n");	
		
		while(true) {
			//	solicitar un número de 1 a 5
			String number = System.console().readLine("Introduzca el número del producto: \n"
													 + "\u001B[1m 1." + " Carnes\n"
													 + "\u001B[1m 2." + " Pescados\n"
													 + "\u001B[1m 3." + " Mariscos\n"
													 + "\u001B[1m 4." + " Embutidos\n"
													 + "\u001B[1m 5." + " Repostería\n");
			//	crear un socket con el puerto y la dirección IP determinados
			Socket socket = new Socket(serverAddress, 1234);
			
			//	enviar el dato al servidor
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(number);
			
			//	recibir las respuesta del servidor
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String answer = input.readLine();
			
			//	mostrar por consola
			System.out.println(answer);
			
			//	cerrar el socket
			socket.close();
		}
	}

}

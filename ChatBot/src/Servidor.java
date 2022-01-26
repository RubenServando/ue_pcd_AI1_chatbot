import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket listener = new ServerSocket(1234);
		
		System.out.println("Servidor Iniciado");
		
		try {
		while(true) {
			
			Socket socket = listener.accept();
			
			//	leer el número del cliente
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			int productNumber = 0;
			
			String inputString = input.readLine();
			
			//	comprobar que sea un número entero (parseo)
			if(isNumeric(inputString)) {
				productNumber = Integer.parseInt(inputString);
			}
			
			//	crear el stream de salida
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			// mostrar aviso de errores en el tipo de dato introducido
			if(!isNumeric(inputString)) {
				out.println(inputString + "ni siquiera es un número");
			} else if(productNumber < 1 || productNumber > 5) {
				out.println("El número " + productNumber + " no es válido, introduzca un número de 1 a 5");				
			}

			switch(productNumber) {
			case (1):
				out.println(" 10% de descuento en\u001B[1m Solomillo de Cerdo.\n ");
				break;
			case(2):
				out.println(" 25% de descuento en\u001B[1m Merluza.\n" );
				break;
			case(3):
				out.println(" 5% de descuento en\u001B[1m Centollo.\n" );
				break;
			case(4):
				out.println(" 15% de descuento en\u001B[1m Jamón de Bellota.\n" );
				break;
			case(5):
				out.println(" 20% de descuento en\u001B[1m Cupcakes.\n" );
				break;
			}		
			//	cerrar el socket
			socket.close();			
		}
			
		} finally {
			//	cerrar el listener
			listener.close();
		}
	}	
	//	método auxiliar para averiguar si la entrada es un número entero
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}
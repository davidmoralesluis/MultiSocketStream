package org.example;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public Cliente(String mensaje) throws IOException {

        Scanner sc = new Scanner(System.in);
        String serverHostname = "localhost";
        int serverPort = 12345;
        String servidor="";
        int largoTexto=0;
        String espacio="";



            // Creamos un socket y nos conectamos al servidor
            Socket clientSocket = new Socket(serverHostname, serverPort);

            // Obtenemos los flujos de entrada y salida del socket
            OutputStream out = clientSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            InputStream in = clientSocket.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            // Enviamos un mensaje al servidor
            dos.writeUTF(mensaje);

            // Recibimos la respuesta del servidor
            servidor = dis.readUTF();
            largoTexto=servidor.length();
        for (int i = 0; i <50-(largoTexto*2); i++) {
            espacio+=" ";
        }

            Interfaz.campoChat.setText(Interfaz.campoChat.getText()+espacio+"servidor -> "+servidor+"\n");

            System.out.println("ReServidor: " + servidor);

            // Cerramos el socket y los flujos de entrada y salida
            dos.close();
            dis.close();
            clientSocket.close();

    }
}

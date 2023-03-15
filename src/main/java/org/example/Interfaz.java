package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interfaz extends JFrame implements ActionListener {

    private JFrame ventana;
    private JLabel titulo;
    private JTextField campoTexto;
    static public JTextArea campoChat;
    private JButton send;

    Timer empezar,tic;

    private static Interfaz instance = null;

    public static Interfaz getInstance() throws IOException {

        if (instance == null) {
            // como no está creada, la creo
            // desde aqui si que puedo acceder al constructor
            // porque estoy en la misma clase
            instance = new Interfaz();
        }
        // devuelvo la instancia
        return instance;
    }

    private Interfaz() throws IOException{
        ventana = new JFrame("Chat Room");
        ventana.setBounds(50, 100, 400, 500);
        ventana.setLayout(null);
        ventana.getContentPane().setBackground(Color.white);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setResizable(true);

        empezar = new Timer(10,this);
        empezar.start();

        tic = new Timer(10,this);


        titulo = new JLabel("Chat Room");
        titulo.setBounds(130, 30, 200, 30);
        titulo.setForeground(Color.red);
        titulo.setFont(new Font("Courier 10 Pitch", Font.BOLD+Font.CENTER_BASELINE, 25));
        ventana.add(titulo);

        campoTexto = new JTextField();
        campoTexto.setBounds(100, 350, 200, 30); // Establecer la posición y tamaño del campo de texto
        campoTexto.setVisible(true);
        ventana.add(campoTexto);

        campoChat = new JTextArea();
        campoChat.setBounds(50, 100, 300, 200); // Establecer la posición y tamaño del campo de texto
        campoChat.setBackground(Color.yellow);
        campoChat.setVisible(true);

        ventana.add(campoChat);

        send = new JButton("Enviar");
        send.setBounds(150, 400, 100, 30); // Establecer la posición y tamaño del botón
        send.setBackground(Color.BLUE);
        send.setVisible(true);
        send.addActionListener(this);
        ventana.add(send);

        ventana.setVisible(true);

        campoChat.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==empezar){
            ventana.getContentPane().setBackground(Color.orange);
            System.out.println("start");
            empezar.stop();
        }

        if (e.getSource()== send){
            System.out.println("boton1");
            campoChat.setText(campoChat.getText()+campoTexto.getText()+"\n");
            System.out.println("send");
            campoTexto.setText("");
            tic.start();
        }

        if(e.getSource()==tic){
            try {
                new Cliente(campoTexto.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            tic.stop();
        }


    }
}

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static Socket socket;
    private static PrintWriter out;

    public static void main(String[] args) {
        Frame frame = new Frame("Chat Client");
        TextField textField = new TextField();
        TextArea textArea = new TextArea();

        frame.add(textArea, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.setSize(400, 300);
        frame.setVisible(true);

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);

            textField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String message = textField.getText();
                    out.println(message);
                    textField.setText("");
                }
            });

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                textArea.append(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

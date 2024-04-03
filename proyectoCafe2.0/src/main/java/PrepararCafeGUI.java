/**
 *
 * @author Juan Andres Luque Mahecha (marcus)
 * https://github.com/juanandresluque 
*/

//importar clases y paquetes con Abstract Window Toolki(AWT)'Frame','Button','Label','TextField','Checkbox'
import java.awt.*;

//Clases para manejar eventos de interfaz gráfica de usuario en Java. 'ActionEvent','ActionListener'
import java.awt.event.*;

/*Clases y paquetes relaconados con Swing, bibliotecas gradicas para usuario Java. Jframe(Ventana de inicio),
Jbutton(boton),Jlabel(Etiqueta),JtextField(Campo de texto),JradioButton(Boton redondo)*/
import javax.swing.*;

//Se crea una nueva clase que será una ventana de interfaz y en la cual se ejecutará todo

public class PrepararCafeGUI extends JFrame {
    private JRadioButton siCafeMolidoRadioButton;
    private JRadioButton noCafeMolidoRadioButton;
    private JRadioButton ollaRadioButton;
    private JRadioButton cafeteraRadioButton;
    private JRadioButton siTazaRadioButton;
    private JRadioButton noTazaRadioButton;
    private JRadioButton siAguaRadioButton;
    private JRadioButton noAguaRadioButton;
    private JButton prepararCafeButton;
    private JLabel tiempoEsperaLabel; // Etiqueta para mostrar el tiempo de espera

    // Variable para almacenar el tiempo de espera durante la preparación del café
    private int tiempoEspera;

    // Constructor de la clase PrepararCafeGUI
    public PrepararCafeGUI() {
        // Configuración de la ventana principal
        setTitle("Preparación de Café");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 10, 10));

        // Componentes para verificar los ingredientes y utensilios necesarios
        JLabel cafeMolidoLabel = new JLabel("¿Tiene café molido?");
        siCafeMolidoRadioButton = new JRadioButton("Sí");
        noCafeMolidoRadioButton = new JRadioButton("No");
        ButtonGroup cafeMolidoGroup = new ButtonGroup();
        cafeMolidoGroup.add(siCafeMolidoRadioButton);
        cafeMolidoGroup.add(noCafeMolidoRadioButton);
        JPanel cafeMolidoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cafeMolidoPanel.add(siCafeMolidoRadioButton);
        cafeMolidoPanel.add(noCafeMolidoRadioButton);
        add(cafeMolidoLabel);
        add(cafeMolidoPanel);

        JLabel recipienteLabel = new JLabel("¿Va a utilizar una olla o una cafetera?");
        ButtonGroup recipienteGroup = new ButtonGroup();
        ollaRadioButton = new JRadioButton("Olla");
        cafeteraRadioButton = new JRadioButton("Cafetera");
        recipienteGroup.add(ollaRadioButton);
        recipienteGroup.add(cafeteraRadioButton);
        JPanel recipientePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        recipientePanel.add(ollaRadioButton);
        recipientePanel.add(cafeteraRadioButton);
        add(recipienteLabel);
        add(recipientePanel);

        JLabel tazaLabel = new JLabel("¿Tiene una taza o tazas?");
        siTazaRadioButton = new JRadioButton("Sí");
        noTazaRadioButton = new JRadioButton("No");
        ButtonGroup tazaGroup = new ButtonGroup();
        tazaGroup.add(siTazaRadioButton);
        tazaGroup.add(noTazaRadioButton);
        JPanel tazaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tazaPanel.add(siTazaRadioButton);
        tazaPanel.add(noTazaRadioButton);
        add(tazaLabel);
        add(tazaPanel);

        JLabel aguaLabel = new JLabel("¿Tiene agua?");
        siAguaRadioButton = new JRadioButton("Sí");
        noAguaRadioButton = new JRadioButton("No");
        ButtonGroup aguaGroup = new ButtonGroup();
        aguaGroup.add(siAguaRadioButton);
        aguaGroup.add(noAguaRadioButton);
        JPanel aguaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aguaPanel.add(siAguaRadioButton);
        aguaPanel.add(noAguaRadioButton);
        add(aguaLabel);
        add(aguaPanel);

        // Botón para iniciar la preparación del café
        prepararCafeButton = new JButton("Preparar Café");
        prepararCafeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prepararCafe();
            }
        });
        add(new JLabel());
        add(prepararCafeButton);

        // Etiqueta para mostrar el tiempo de espera durante la preparación del café
        JLabel tiempoEsperaTextLabel = new JLabel("Tiempo de espera:");
        tiempoEsperaLabel = new JLabel("");
        add(tiempoEsperaTextLabel);
        add(tiempoEsperaLabel);
    }

    // Método para preparar el café
    private void prepararCafe() {
        // Verificar si se tienen todos los ingredientes y utensilios necesarios
        boolean tieneCafeMolido = siCafeMolidoRadioButton.isSelected();
        boolean tieneOlla = ollaRadioButton.isSelected();
        boolean tieneTaza = siTazaRadioButton.isSelected();
        boolean tieneAgua = siAguaRadioButton.isSelected();

        // Verificar si faltan elementos para la preparación del café
        if (!tieneCafeMolido || (!tieneOlla && !tieneTaza) || !tieneAgua) {
            JOptionPane.showMessageDialog(this, "Debe asegurarse de tener todos los materiales e ingredientes.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si se tiene café molido
        if (!tieneCafeMolido) {
            JOptionPane.showMessageDialog(this, "Debe tener café molido para continuar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si se ha seleccionado una olla o una cafetera
        if (!tieneOlla && !tieneTaza) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una olla o una cafetera para continuar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si se tiene agua
        if (!tieneAgua) {
            JOptionPane.showMessageDialog(this, "Debe tener agua para continuar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el número de tazas de café a preparar
        int numTazas = Integer.parseInt(JOptionPane.showInputDialog(this, "¿Cuántas tazas de café quieres hacer?"));
        double aguaTotalMililitros = numTazas * 250; // Cada taza equivale a 250 mililitros
        int aguaTotalTazas = (int) Math.round(aguaTotalMililitros / 240.0); // 1 taza equivale a 240 mililitros
        double cafeTotalGramos = numTazas * 15; // Cada taza equivale a 15 gramos de café
        int tiempoTotalSegundos = numTazas * 3 * 60; // 3 minutos por cada taza
        int minutos = tiempoTotalSegundos / 60;
        int segundos = tiempoTotalSegundos % 60;

        // Crear panel para mostrar la información sobre la preparación del café
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel infoLabel = new JLabel("Necesitarás " + aguaTotalTazas + " tazas de agua para hacer " + numTazas + " tazas de café.\n" +
                "Necesitarás " + cafeTotalGramos + " gramos de café para hacer " + numTazas + " tazas de café.\n" +
                "Llegando a 96 grados Celsius... Estimación: Aproximadamente " +
                minutos + " minutos y " + segundos + " segundos.\n" +
                "Si usó cafeterá el proceso durará la mitad del tiempo estimado");
        panel.add(infoLabel);

        // Determinar el tiempo de espera antes de la preparación del café
        tiempoEspera = tieneOlla ? 10 : 5; // Demora de 10 segundos si se usó olla, de lo contrario 5 segundos

        // Etiqueta para mostrar el contador del tiempo de espera
        JLabel tiempoEsperaCountLabel = new JLabel(Integer.toString(tiempoEspera));
        panel.add(tiempoEsperaCountLabel);

        // Iniciar un hilo para contar el tiempo de espera
        new Thread(() -> {
            for (int i = tiempoEspera; i >= 0; i--) {
                final int count = i;
                SwingUtilities.invokeLater(() -> tiempoEsperaCountLabel.setText(Integer.toString(count)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Habilitar el botón "OK" cuando el contador llegue a cero
            JOptionPane.getRootFrame().getComponents()[1].setEnabled(true);
            tiempoEsperaLabel.setVisible(false); // Ocultar el contador después de que llegue a cero
        }).start();

        // Mostrar la ventana de información sobre la preparación del café
        JOptionPane.showMessageDialog(this, panel, "Información", JOptionPane.INFORMATION_MESSAGE);

        // Verificar si se ha vertido el agua correctamente sobre el café
        boolean vertidoCorrecto = false;
        while (!vertidoCorrecto) {
            int confirmacionVertidoAgua = JOptionPane.showConfirmDialog(this,
                    "¿Ha vertido el agua a 96 grados Celsius sobre la medida del café indicada anteriormente?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirmacionVertidoAgua == JOptionPane.YES_OPTION) {
                vertidoCorrecto = true;
            } else {
                JOptionPane.showMessageDialog(this, "Es necesario verter el agua para tener un café de calidad.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Mostrar el paso 6: dejar reposar el café durante 5 minutos
        JOptionPane.showMessageDialog(this, "Paso 6: Dejar reposar el café durante 5 minutos.",
                "Paso 6", JOptionPane.INFORMATION_MESSAGE);

        // Crear panel para mostrar el tiempo de descanso en el paso 7
        JPanel paso7Panel = new JPanel(new GridLayout(0, 1));
        JLabel tiempoDescansoTextLabel = new JLabel("Tiempo de descanso:");
        tiempoEsperaLabel = new JLabel("5 minutos");
        paso7Panel.add(tiempoDescansoTextLabel);
        paso7Panel.add(tiempoEsperaLabel);

        // Mostrar la ventana del paso 7: esperar el tiempo de descanso
        JOptionPane.showMessageDialog(this, paso7Panel, "Paso 7", JOptionPane.INFORMATION_MESSAGE);

        // Esperar 5 segundos para simular el tiempo de descanso
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar mensaje indicando que el café está listo
        JOptionPane.showMessageDialog(this, "¡Disfruta de tu café!", "¡Café listo!", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrepararCafeGUI gui = new PrepararCafeGUI();
            gui.setVisible(true);
        });
    }
}

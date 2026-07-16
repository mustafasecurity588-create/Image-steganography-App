
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SteganographyApp extends JFrame {

    private JTextField imagePathField;
    private JPasswordField keyField;
    private JTextArea secretTextArea;
    private JTextArea outputTextArea;
    private JLabel imagePreview;

    private BufferedImage originalImage;
    private BufferedImage encodedImage;

    private static final String MAGIC = "Stego:";
    private static final String SEP = "|";
    public SteganographyApp() {
        setTitle("Steganography Toolkit APP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1040, 720);
        setLocationRelativeTo(null);


        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(14, 14, 14, 14));
        root.setBackground(new Color(30, 49, 106));

        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createCenter(), BorderLayout.CENTER);
        root.add(createFooter(), BorderLayout.SOUTH);

        setContentPane(root);
        applyDarkTheme();
    }

    private void applyDarkTheme() {
        UIManager.put("control", new Color(40, 44, 52));
        UIManager.put("info", new Color(40, 44, 52));
        UIManager.put("nimbusBase", new Color(35, 39, 47));
        UIManager.put("nimbusBlueGrey", new Color(58, 63, 72));
        UIManager.put("text", Color.BLACK);
        UIManager.put("nimbusFocus", new Color(90, 120, 180));
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JLabel title = new JLabel("Steganography Toolkit");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(235, 238, 245));

        JLabel subtitle = new JLabel("it can hide your secret message in a picture");
        subtitle.setForeground(new Color(170, 176, 186));

        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setOpaque(false);
        titleBox.add(title);
        titleBox.add(subtitle);

        JPanel filePanel = new JPanel(new GridBagLayout());
        filePanel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        imagePathField = new JTextField();
        styleField(imagePathField);

        JButton browsebutton = new JButton("Browse");
        styleButton(browsebutton);
        browsebutton.addActionListener(e -> chooseImage());

        keyField = new JPasswordField();
        styleField(keyField);

        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        JLabel imageLabel = new JLabel("Add Image:");
        imageLabel.setForeground(new Color(220, 224, 232));
        filePanel.add(imageLabel, c);

        c.gridx = 1; c.gridy = 0; c.weightx = 1;
        filePanel.add(imagePathField, c);

        c.gridx = 2; c.gridy = 0; c.weightx = 0;
        filePanel.add(browsebutton, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0;
        JLabel keyLabel = new JLabel("Add a Key:");
        keyLabel.setForeground(new Color(220, 224, 232));
        filePanel.add(keyLabel, c);

        c.gridx = 1; c.gridy = 1; c.weightx = 1;
        filePanel.add(keyField, c);

        panel.add(titleBox, BorderLayout.WEST);
        panel.add(filePanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCenter() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 12));
        panel.setOpaque(false);

        JPanel left = new JPanel(new BorderLayout(8, 8));
        left.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 75, 85)), "Image Preview"));
        left.setBackground(new Color(30, 33, 40));
        left.getBorder();

        imagePreview = new JLabel("No image loaded", SwingConstants.CENTER);
        imagePreview.setOpaque(true);
        imagePreview.setBackground(new Color(20, 22, 28));
        imagePreview.setForeground(new Color(180, 186, 196));
        imagePreview.setPreferredSize(new Dimension(460, 430));
        left.add(new JScrollPane(imagePreview), BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout(8, 8));
        right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 75, 85)), "Enter your secret message here"));
        right.setBackground(new Color(30, 33, 40));

        secretTextArea = new JTextArea();
        styleArea(secretTextArea);
        right.add(new JScrollPane(secretTextArea), BorderLayout.CENTER);

        panel.add(left);
        panel.add(right);
        return panel;
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttons.setOpaque(false);

        JButton encodeButton = new JButton("Encode");
        JButton decodeButton = new JButton("Decode");
        JButton saveButton = new JButton("Save encoded Image");

        styleButton(encodeButton);
        styleButton(decodeButton);
        styleButton(saveButton);

        encodeButton.addActionListener(e -> encodeMessage());
        decodeButton.addActionListener(e -> decodeMessage());
        saveButton.addActionListener(e -> saveEncodedImage());

        buttons.add(encodeButton);
        buttons.add(decodeButton);
        buttons.add(saveButton);

        outputTextArea = new JTextArea(6, 20);
        outputTextArea.setEditable(false);
        styleArea(outputTextArea);

        panel.add(buttons, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        return panel;
    }

    private void styleField(JTextField field) {
        field.setBackground(new Color(32, 36, 44));
        field.setForeground(new Color(235, 238, 245));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(75, 80, 92)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleArea(JTextArea area) {
        area.setBackground(new Color(20, 22, 28));
        area.setForeground(new Color(230, 233, 238));
        area.setCaretColor(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(66, 100, 180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }

    private void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            imagePathField.setText(file.getAbsolutePath());
            try {
                originalImage = ImageIO.read(file);
                encodedImage = null;
                showImage(originalImage);
                outputTextArea.setText("Image loaded successfully.");
            } catch (Exception ex) {
                outputTextArea.setText("Failed to load image.");
            }
        }
    }

    private void encodeMessage() {
        if (originalImage == null) {
            outputTextArea.setText("Please load an image first");
            return;
        }

        String message = secretTextArea.getText().trim();
        String key = new String(keyField.getPassword()).trim();

        if (message.isEmpty()) {
            outputTextArea.setText("Please enter a secret message.");
            return;
        }
        if (key.isEmpty()) {
            outputTextArea.setText("Please enter a key.");
            return;
        }

        String payload = MAGIC + key + SEP + message + "###";
        int requiredBits = payload.length() * 8;
        int availableBits = originalImage.getWidth() * originalImage.getHeight();

        if (requiredBits > availableBits) {
            outputTextArea.setText("Message is too long for this image");
            return;
        }

        encodedImage = encodeText(originalImage, payload);
        showImage(encodedImage);
        outputTextArea.setText("Message encoded successfully");
    }

    private void decodeMessage() {
        BufferedImage img = (encodedImage != null) ? encodedImage : originalImage;

        if (img == null) {
            outputTextArea.setText("Please load an image first.");
            return;
        }

        String key = new String(keyField.getPassword()).trim();
        if (key.isEmpty()) {
            outputTextArea.setText("Please enter the key to decode.");
            return;
        }

        String decoded = decodeText(img);

        if (!decoded.startsWith(MAGIC)) {
            outputTextArea.setText("No hidden message found.");
            return;
        }

        decoded = decoded.substring(MAGIC.length());
        int sepIndex = decoded.indexOf(SEP);
        if (sepIndex == -1) {
            outputTextArea.setText("Invalid hidden message format.");
            return;
        }

        String savedKey = decoded.substring(0, sepIndex);
        String message = decoded.substring(sepIndex + 1);

        if (!savedKey.equals(key)) {
            outputTextArea.setText("Wrong key");
            return;
        }

        outputTextArea.setText("Decoded message:\n" + message);
    }

    private void saveEncodedImage() {
        if (encodedImage == null) {
            outputTextArea.setText("No encoded image to save.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".png")) {
                path += ".png";
            }

            try {
                ImageIO.write(encodedImage, "png", new File(path));
                outputTextArea.setText("Encoded image saved successfully.");
            }
            catch(Exception ex)
            {
                outputTextArea.setText("Failed to save image.");
            }
        }
    }

    private void showImage(BufferedImage img) {
        if (img == null) {
            return;
        }
        Image scaled = img.getScaledInstance(460, 430, Image.SCALE_SMOOTH);
        imagePreview.setIcon(new ImageIcon(scaled));
        imagePreview.setText("");
    }

    private BufferedImage encodeText(BufferedImage image, String text) {
        BufferedImage encoded = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        int bitIndex = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                if (bitIndex < text.length() * 8)
                {
                    int charIndex = bitIndex / 8;
                    int bitPos = 7 - (bitIndex % 8);
                    int bit = (text.charAt(charIndex) >> bitPos) & 1;
                    pixel = (pixel & 0xFFFFFFFE) | bit;
                    bitIndex++;
                }

                encoded.setRGB(x, y, pixel);
            }
        }

        return encoded;
    }



    private String decodeText(BufferedImage image) {
        StringBuilder result = new StringBuilder();
        int currentChar = 0;
        int bitCount = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int bit = pixel & 1;

                currentChar = (currentChar << 1) | bit;
                bitCount++;

                if (bitCount == 8) {
                    char c = (char) currentChar;
                    result.append(c);

                    if (result.length() >= 3 && result.substring(result.length() - 3).equals("###")) {
                        return result.substring(0, result.length() - 3);
                    }

                    currentChar = 0;
                    bitCount = 0;
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new SteganographyApp().setVisible(true));
    }
}
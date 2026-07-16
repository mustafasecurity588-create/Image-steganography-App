# 🔐 Steganography Toolkit

A clean, modern Java Swing application that allows users to securely hide and retrieve secret messages inside images using **LSB (Least Significant Bit) Steganography**.

The application provides a simple graphical interface for encoding and decoding text messages with password protection.


## 📸 Preview

![Uploading image.png…]()

## ✨ Features

- 🖼️ Load PNG images
- ✍️ Hide secret text inside an image
- 🔑 Password (Key) protection
- 🔍 Decode hidden messages
- 💾 Save encoded images
- 🌙 Clean Dark Theme GUI
- ⚡ Simple and beginner-friendly Java code
- 🖥️ Built entirely using Java Swing


## 🛠 Technologies Used

- Java
- Java Swing
- AWT
- BufferedImage
- ImageIO


## 📂 Project Structure

```
SteganographyToolkit/
│
├── SteganographyApp.java
└── README.md
```

## 🚀 How It Works

### Encoding

1. Browse and select an image.
2. Enter your secret message.
3. Enter a secret key.
4. Click **Encode**.
5. Save the newly encoded PNG image.

### Decoding

1. Load the encoded image.
2. Enter the correct secret key.
3. Click **Decode**.
4. The hidden message will be displayed.


## 🔒 Security

The application uses:

- LSB (Least Significant Bit) image steganography
- Password verification before revealing the hidden message

If the wrong password is entered, the hidden message cannot be viewed.


## 🧠 Steganography Technique

Each character of the message is converted into binary.

The application stores each bit inside the **Least Significant Bit (LSB)** of every image pixel.

Because only one bit of each pixel is modified, the visual appearance of the image remains almost identical to the original.



## ▶️ Running the Project

### Requirements

- Java JDK 8 or later

### Compile

```bash
javac SteganographyApp.java
```

### Run

```bash
java SteganographyApp
```



## 📌 Limitations

- Supports PNG images only.
- Large messages require larger images.
- Uses a simple LSB implementation intended for educational purposes.



## 📖 Future Improvements

- AES encryption before embedding
- Drag & Drop image support
- Image capacity indicator
- Multiple image formats
- Better error handling
- Theme customization
- Copy decoded message button


## 👨‍💻 Author

Developed as a Java OOP and GUI project to demonstrate the fundamentals of image steganography using Java Swing.


## 📜 License

This project is open source and available for educational and personal use.

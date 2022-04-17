from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont
from PyQt5.QtWidgets import QFrame, QLabel, QLineEdit, QPushButton

from customWidgets.buttons.customButton import CustomButton


class RegisterFrame(QFrame):

    def __init__(self, parent):
        super(RegisterFrame, self).__init__(parent)
        self.registerText = QLabel(self)
        self.usernameLineEdit = QLineEdit(self)
        self.passwordLineEdit = QLineEdit(self)
        self.repeatPasswordLineEdit = QLineEdit(self)
        self.registerButton = CustomButton(self, "Register", 115, 135, 35)
        self.backButton = CustomButton(self, "Back", 115, 135, 35)
        self.initUI()

    def initUI(self):
        # username line edit
        self.usernameLineEdit.setPlaceholderText("Username")
        self.usernameLineEdit.setGeometry(120, 340, 400, 45)
        self.usernameLineEdit.setFont(QFont("Arial", 15))
        self.usernameLineEdit.setAlignment(Qt.AlignCenter)
        self.usernameLineEdit.setStyleSheet("border: None; border-radius: 10px;")

        # password line edit
        self.passwordLineEdit.setPlaceholderText("Password")
        self.passwordLineEdit.setGeometry(120, 430, 400, 45)
        self.passwordLineEdit.setFont(QFont("Arial", 15))
        self.passwordLineEdit.setAlignment(Qt.AlignCenter)
        self.passwordLineEdit.setStyleSheet("border: None; border-radius: 10px;")

        # repeat password line edit
        self.repeatPasswordLineEdit.setPlaceholderText("Repeat password")
        self.repeatPasswordLineEdit.setGeometry(120, 490, 400, 45)
        self.repeatPasswordLineEdit.setFont(QFont("Arial", 15))
        self.repeatPasswordLineEdit.setAlignment(Qt.AlignCenter)
        self.repeatPasswordLineEdit.setStyleSheet("border: None; border-radius: 10px;")

        # register button
        self.registerButton.setGeometry(180, 600, 115, 35)

        # back button
        self.backButton.setGeometry(350, 600, 115, 35)

        # welcomeFrames label
        font = QFont("Helvetica")
        font.setWeight(30)
        font.setPixelSize(50)
        font.setBold(True)
        self.registerText.setFont(font)
        self.registerText.setGeometry(190, 260, 260, 50)
        self.registerText.setAlignment(Qt.AlignCenter)
        self.registerText.setText("REGISTER")

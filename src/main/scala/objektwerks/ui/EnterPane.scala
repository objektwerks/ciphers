package objektwerks.ui

import scalafx.Includes.*
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Alert, Button, Label, TextField}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{HBox, Priority}

final class EnterPane(model: Model) extends HBox:
  spacing = 6
  padding = Insets(6)
  
  val textLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Text:"

  val textField = new TextField:
    hgrow = Priority.Always
    onKeyReleased = (event: KeyEvent) => if event.code == KeyCode.Enter then model.addEncoding( text.value )

  val numberLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Number:"

  val numberField = new TextField:
    prefWidth = 100
    onKeyReleased = (event: KeyEvent) =>
      if event.code == KeyCode.Enter then
        val number = text.value.toIntOption.getOrElse(0)
        val isListed = model.addNumber(number)
        if isListed then
          new Alert(AlertType.Information) {
            initOwner(App.stage)
            title = "Numbers"
            headerText = "Number Listed"
            contentText = s"$number is listed."
          }.showAndWait()

  val clearButton = new Button:
    prefWidth = 75
    text = "Clear"
    onAction = { _ =>
      textField.text = ""
      numberField.text = ""
      model.clear()
    }

  val hbox = new HBox:
    spacing = 6
    children = List(textLabel, textField, numberLabel, numberField, clearButton)

  children = List(hbox)
  HBox.setHgrow(this, Priority.Always)
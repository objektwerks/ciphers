package objektwerks

import scalafx.Includes.*
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Alert, Button, Label, TextField, TitledPane}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{HBox, Priority}

object TitledEnterPane:
  def apply(): TitledPane =
    new TitledPane:
      text = "Enter"
      content = EnterPane()

final class EnterPane extends HBox:
  val textLabel = new Label:
    padding = Insets(6)
    alignment = Pos.CenterLeft
    text = "Text:"

  val textField = new TextField:
    padding = Insets(6)
    hgrow = Priority.Always
    onKeyReleased = (event: KeyEvent) => if event.code == KeyCode.Enter then Model.addEncoding( text.value )

  val numberLabel = new Label:
    padding = Insets(6)
    alignment = Pos.CenterLeft
    text = "Number:"

  val numberField = new TextField:
    padding = Insets(6)
    prefWidth = 100
    onKeyReleased = (event: KeyEvent) =>
      if event.code == KeyCode.Enter then
        val number = text.value.toIntOption.getOrElse(0)
        val isListed = Model.addNumber(number)
        if isListed then
          new Alert(AlertType.Information) {
            initOwner(App.stage)
            title = "Numbers"
            headerText = "Number Listed"
            contentText = s"$number is listed."
          }.showAndWait()

  val clearButton = new Button:
    padding = Insets(6)
    prefWidth = 75
    text = "Clear"
    onAction = { _ =>
      textField.text = ""
      numberField.text = ""
      Model.clear()
    }

  val grid = new HBox:
    spacing = 6
    padding = Insets(6)
    children = List(textLabel, textField, numberLabel, numberField, clearButton)

  children = List(grid)
  HBox.setHgrow(grid, Priority.Always)
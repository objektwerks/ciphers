package objektwerks

import java.time.LocalDate

import scalafx.geometry.{Insets, Pos}
import scalafx.Includes.*
import scalafx.scene.control.{ButtonType, Dialog, Label}
import scalafx.scene.layout.{GridPane, VBox}

final class ChemicalDialog(date: LocalDate) extends Dialog[LocalDate]:
  initOwner(App.stage)
  title = "Date"
  headerText = "Date Encodings"

  println(date)

  val splitYearLabel = new Label:
    padding = Insets(6)
    alignment = Pos.CenterLeft
    text = "Text:"

  val splitYearText = new Label:
    padding = Insets(6)
    alignment = Pos.CenterLeft
    text = "Text:"

  val grid = new GridPane:
    hgap = 6
    vgap = 6
    padding = Insets(top = 6, right = 6, bottom = 6, left = 6)
    add(splitYearLabel, columnIndex = 0, rowIndex = 0)
    add(splitYearText, columnIndex = 1, rowIndex = 0)

  dialogPane().buttonTypes = List(ButtonType.Close)
  dialogPane().content = new VBox:
    prefWidth = 600
    prefHeight = 200
    spacing = 6
    children = List(grid)
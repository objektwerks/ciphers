package objektwerks.ui

import java.time.LocalDate

import scalafx.beans.property.ObjectProperty
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.control.{DatePicker, Label, Separator}
import scalafx.scene.layout.{GridPane, HBox, Priority}

import objektwerks.Date

final class DatePane(model: Model) extends HBox:
  spacing = 6
  padding = Insets(6)
  prefWidth = 275

  val paneLabel = new Label:
    padding = Insets(6)
    style = "-fx-font-weight: bold"
    text = "Date:"

  // Date section begin.

  val dateLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Date:"

  val datePicker = new DatePicker:
    alignment = Pos.Center
    prefWidth = 110
    value <==> model.observableDate
    onAction = { _ =>
      dayOfYearText.text = Date.dayOfYear(model.observableDate.value)
      remainingDaysInYearText.text = Date.remainingDaysInYear(model.observableDate.value)
    }

  val dayOfYearLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Day of Year:"

  val dayOfYearText = new Label:
    alignment = Pos.Center
    text = Date.dayOfYear(model.observableDate.value)

  val remainingDaysInYearLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Remaining Days in Year:"

  val remainingDaysInYearText = new Label:
    alignment = Pos.Center
    text = Date.remainingDaysInYear(model.observableDate.value)

  val dateGrid = new GridPane:
    hgap = 6
    vgap = 6
    add(dateLabel, columnIndex = 0, rowIndex = 0)
    add(datePicker, columnIndex = 1, rowIndex = 0)
    add(dayOfYearLabel, columnIndex = 0, rowIndex = 1)
    add(dayOfYearText, columnIndex = 1, rowIndex = 1)
    add(remainingDaysInYearLabel, columnIndex = 0, rowIndex = 2)
    add(remainingDaysInYearText, columnIndex = 1, rowIndex = 2)

  // Date section end.

  // Encodings section begin.

  val encodingsSeparator = new Separator:
    orientation = Orientation.Vertical

  val splitYearLabel = new Label:
    alignment = Pos.CenterLeft

  val splitYearText = new Label:
    alignment = Pos.Center

  val splitEachYearLabel = new Label:
    alignment = Pos.CenterLeft

  val splitEachYearText = new Label:
    alignment = Pos.Center

  val splitEachMonthDayYearLabel = new Label:
    alignment = Pos.CenterLeft

  val splitEachMonthDayYearText = new Label:
    alignment = Pos.Center

  val splitRightYearLabel = new Label:
    alignment = Pos.CenterLeft

  val splitRightYearText = new Label:
    alignment = Pos.Center

  val splitEachMonthDayRightYearLabel = new Label:
    alignment = Pos.CenterLeft

  val splitEachMonthDayRightYearText = new Label:
    alignment = Pos.Center

  val encodingsGrid = new GridPane:
    hgap = 6
    vgap = 6
    add(splitYearLabel, columnIndex = 0, rowIndex = 0)
    add(splitYearText, columnIndex = 1, rowIndex = 0)
    add(splitEachYearLabel, columnIndex = 0, rowIndex = 1)
    add(splitEachYearText, columnIndex = 1, rowIndex = 1)
    add(splitEachMonthDayYearLabel, columnIndex = 0, rowIndex = 2)
    add(splitEachMonthDayYearText, columnIndex = 1, rowIndex = 2)
    add(splitRightYearLabel, columnIndex = 0, rowIndex = 3)
    add(splitRightYearText, columnIndex = 1, rowIndex = 3)
    add(splitEachMonthDayRightYearLabel, columnIndex = 0, rowIndex = 4)
    add(splitEachMonthDayRightYearText, columnIndex = 1, rowIndex = 4)

  def setEncodings: Unit =
    val (splitYearExpression, splitYearEncoding) = Date.splitYear(model.observableDate.value)
    splitYearLabel.text = s"$splitYearExpression:"
    splitYearText.text = splitYearEncoding.toString

    val (splitEachYearExpression, splitEachYearEncoding) = Date.splitEachYear(model.observableDate.value)
    splitEachYearLabel.text = s"$splitEachYearExpression:"
    splitEachYearText.text = splitEachYearEncoding.toString

    val (splitEachMonthDayYearExpression, splitEachMonthDayYearEncoding) = Date.splitEachMonthDayYear(model.observableDate.value)
    splitEachMonthDayYearLabel.text = s"$splitEachMonthDayYearExpression:"
    splitEachMonthDayYearText.text = splitEachMonthDayYearEncoding.toString

    val (splitRightYearExpression, splitRightYearEncoding) = Date.splitRightYear(model.observableDate.value)
    splitRightYearLabel.text = s"$splitRightYearExpression:"
    splitRightYearText.text = splitRightYearEncoding.toString

    val (splitEachMonthDayRightYearExpression, splitEachMonthDayRightYearEncoding) = Date.splitEachMonthDayRightYear(model.observableDate.value)
    splitEachMonthDayRightYearLabel.text = s"$splitEachMonthDayRightYearExpression:"
    splitEachMonthDayRightYearText.text = splitEachMonthDayRightYearEncoding.toString

  model.observableDate.onChange { (_, _, _) =>
    setEncodings
  }
  setEncodings

  // Encodings section end.

  // Date Diff section begin.

  val dateDiffSeparator = new Separator:
    orientation = Orientation.Vertical

  val fromDate = ObjectProperty[LocalDate](this, "fromdate", model.observableDate.value)
  val toDate = ObjectProperty[LocalDate](this, "todate", model.observableDate.value)

  fromDate.onChange { (_, _, _) =>
    dateDiffText.text = Date.dateDiff( fromDate.value, toDate.value ).toString
  }

  toDate.onChange { (_, _, _) =>
    dateDiffText.text = Date.dateDiff( fromDate.value, toDate.value ).toString
  }

  val dateDiffLabel = new Label:
    alignment = Pos.CenterLeft
    text = "Diff:"

  val dateDiffText = new Label:
    alignment = Pos.Center
    text = "0"

  val fromDateLabel = new Label:
    alignment = Pos.CenterLeft
    text = "From Date:"

  val fromDatePicker = new DatePicker:
    alignment = Pos.Center
    prefWidth = 110
    value = model.observableDate.value
    onAction = { _ =>
      fromDate.value = value.value
    }

  val toDateLabel = new Label:
    alignment = Pos.CenterLeft
    text = "To Date:"

  val toDatePicker = new DatePicker:
    alignment = Pos.Center
    prefWidth = 110
    value = model.observableDate.value
    onAction = { _ =>
      toDate.value = value.value
    }

  val dateDiffGrid = new GridPane:
    hgap = 6
    vgap = 6
    add(dateDiffLabel, columnIndex = 0, rowIndex = 0)
    add(dateDiffText, columnIndex = 1, rowIndex = 0)
    add(fromDateLabel, columnIndex = 0, rowIndex = 1)
    add(fromDatePicker, columnIndex = 1, rowIndex = 1)
    add(toDateLabel, columnIndex = 0, rowIndex = 2)
    add(toDatePicker, columnIndex = 1, rowIndex = 2)

  // Date Diff section end.

  children = List(dateGrid, encodingsSeparator, encodingsGrid, dateDiffSeparator, dateDiffGrid)
  HBox.setHgrow(this, Priority.Always)

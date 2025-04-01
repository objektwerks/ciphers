package objektwerks.ui

import com.typesafe.config.Config

import scalafx.scene.image.Image

final class Context(config: Config):
  val windowTitle = config.getString("window.title")
  val windowWidth = config.getDouble("window.width")
  val windowHeight = config.getDouble("window.height")

  val aboutAlertHeaderText = config.getString("about.alert.headerText")
  val aboutAlertContentText = config.getString("about.alert.contentText")

  val menuMenu = config.getString("menu.menu")
  val menuAbout = config.getString("menu.about")
  val menuExit = config.getString("menu.exit")

  val labelDate = config.getString("label.date")
  val labelDayOfYear = config.getString("label.dayOfYear")
  val labelRemainingDaysInYear = config.getString("label.remainingDaysInYear")
  val labelFrom = config.getString("label.from")
  val labelTo = config.getString("label.to")
  val labelDiff = config.getString("label.diff")

  val tabDate = config.getString("tab.date")

  def appIcon = Image(Image.getClass.getResourceAsStream("/cipher.png"))
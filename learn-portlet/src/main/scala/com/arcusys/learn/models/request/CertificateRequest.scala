package com.arcusys.learn.models.request

import org.scalatra.ScalatraBase
import com.arcusys.learn.service.util.{ SessionHandlerContract, Parameter }
import com.arcusys.learn.models.{ ValidPeriod }
import scala.util.Try
import com.arcusys.learn.scorm.tracking.model.certificating.PeriodType
import com.arcusys.learn.models.request.CertificateActionType.CertificateActionType

object CertificateSortBy extends Enumeration {
  type CertificateSortBy = Value
  val Name, Description, CreationDate, UserJoined = Value
  def apply(v: String): CertificateSortBy = v.toLowerCase() match {
    case "name"         => Name
    case "description"  => Description
    case "creationdate" => CreationDate
    case "userjoined"   => UserJoined
    case _              => throw new IllegalArgumentException()
  }
}

object CertificateRequest extends BaseCollectionFilteredRequest with BaseRequest {
  val USER_ID = "userID"
  val USER_IDS = "userIDs"
  val COMPANY_ID = "companyID"
  val ID = "id"
  val TITLE = "title"
  val DESCRIPTION = "description"
  val IS_PERMANENT = "isPermanent"
  val PUBLISH_BADGE = "publishBadge"
  val SHORT_DESCRIPTION = "shortDescription"
  val LOGO = "logo"
  val ROOT_URL = "rootUrl"
  val RESULT_AS = "resultAs"
  val COURSE_ID = "courseId"
  val COURSE_IDS = "courseIds"
  val IMAGE_ID = "imageId"
  val VALID_PERIOD_TYPE = "validPeriodType"
  val VALID_PERIOD = "validPeriod"
  val ORG_ID = "orgId"
  val ACTIVITY_NAME = "activityId"
  val ACTIVITY_COUNT = "activityCount"
  val ACTIVITY_NAMES = "activityIds"
  val TINCAN_STMNT_VERB = "tincanStmntVerb"
  val TINCAN_STMNT_OBJ = "tincanStmntObj"
  val TINCAN_STMNT_VALUE = "tincanStmntValue"
  val PERIOD_VALUE = "periodValue"
  val PERIOD_TYPE = "periodType"
  val SCOPE = "scope"

  val DEFAULT_TITLE = "New certificate"
  val DEFAULT_DESCRIPTION = "Description info"
  val DEFAULT_LOGO = ""
  val DEFAULT_COMPANY_ID = 0
  val SHORT_RESULT_VALUE = "short"

  def apply(scalatra: ScalatraBase) = new Model(scalatra)

  class Model(scalatra: ScalatraBase) extends BaseSortableCollectionFilteredRequestModel(scalatra, CertificateSortBy.apply) {
    implicit val httpRequest = scalatra.request

    def id = Parameter(ID).intRequired
    def title = Parameter(TITLE).required
    def description = Parameter(DESCRIPTION).required
    def isPermanent = Parameter(IS_PERMANENT).booleanRequired
    def isPublishBadge = Parameter(PUBLISH_BADGE).booleanOption match {
      case Some(value) => value
      case None        => false
    }
    def shortDescription = Parameter(SHORT_DESCRIPTION).required
    def companyId = Parameter(COMPANY_ID).intOption match {
      case Some(value) => value
      case None        => DEFAULT_COMPANY_ID
    }
    def courseId = Parameter(COURSE_ID).intRequired
    def courseIds = Parameter(COURSE_IDS).multiRequired.map(x => Try(x.toInt).get)
    def userId = Parameter(USER_ID).intRequired
    def userIds = Parameter(USER_IDS).multiRequired.map(x => Try(x.toInt).get)
    def imageId = Parameter(IMAGE_ID).intRequired
    def action(implicit sessionHandler: SessionHandlerContract): CertificateActionType = {
      val action = CertificateActionType(Parameter(ACTION).required)
      action match {
        case CertificateActionType.ADD                => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.ADD_COURSE         => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.ADD_COURSES        => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.ADD_USERS          => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.ADD_ACTIVITY       => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.ADD_ACTIVITIES     => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.ADD_TINCANSTMNT    => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.UPDATE             => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.UPDATE_LOGO        => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.UPDATE_COURSE      => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.UPDATE_ACTIVITY    => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.UPDATE_STMNT       => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.DELETE             => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.DELETE_COURSE      => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.DELETE_USERS       => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.DELETE_ACTIVITY    => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.DELETE_ACTIVITIES  => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.DELETE_TINCANSTMNT => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.CLONE              => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.PUBLISH            => sessionHandler.requireTeacherPermissions()
        case CertificateActionType.UNPUBLISH          => sessionHandler.requireTeacherPermissions()
        case _                                        => Unit
      }

      return action
    }
    def logo = Parameter(LOGO).option match {
      case Some(value) => value
      case None        => DEFAULT_LOGO
    }
    def orgId = Parameter(ORG_ID).intRequired
    def activityName = Parameter(ACTIVITY_NAME).required
    def activityCount = Parameter(ACTIVITY_COUNT).intRequired
    def activity = {
      val activityName = Parameter(ACTIVITY_NAME).required
      val activityCount = Parameter(ACTIVITY_COUNT).intRequired
      val value = Parameter(PERIOD_VALUE).intRequired
      val periodType = PeriodType(Parameter(PERIOD_TYPE).option)

      (activityName, activityCount, value, periodType)
    }
    def activityNames = Parameter(ACTIVITY_NAMES).multiRequired

    def course = {
      val courseId = Parameter(COURSE_ID).intRequired
      val value = Parameter(PERIOD_VALUE).intRequired
      val periodType = PeriodType(Parameter(PERIOD_TYPE).option)

      (courseId, value, periodType)
    }

    def tincanStatement = {
      val verbs = Parameter(TINCAN_STMNT_VERB).required
      val objs = Parameter(TINCAN_STMNT_OBJ).required
      val value = Parameter(PERIOD_VALUE).intOption
      val periodType = PeriodType(Parameter(PERIOD_TYPE).option)

      (verbs, objs, value.getOrElse(0), periodType)
    }

    def isShortResult = Parameter(RESULT_AS).option match {
      case Some(value) => value == "short"
      case None        => false
    }

    def validPeriod: ValidPeriod = {
      val validPeriod = Parameter(VALID_PERIOD).intOption
      ValidPeriod(validPeriod, Parameter(VALID_PERIOD_TYPE).required)
    }

    def scope: Option[Long] = Parameter(SCOPE).longOption

    def rootUrl = if (Parameter(ROOT_URL).required.contains("http://"))
      Parameter(ROOT_URL).required
    else
      "http://" + Parameter(ROOT_URL).required
  }
}

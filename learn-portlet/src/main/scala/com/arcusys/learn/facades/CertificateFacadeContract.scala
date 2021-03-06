package com.arcusys.learn.facades

import com.arcusys.learn.models.request.CertificateSortBy.CertificateSortBy
import com.arcusys.learn.scorm.tracking.model.certificating.PeriodType._
import com.arcusys.learn.models._
import com.arcusys.learn.models.response.certificates.{ CertificateResponseContract }
import com.arcusys.learn.models.StatementResponse
import com.arcusys.learn.models.response.users.UserResponseWithCertificateStatus
import com.arcusys.learn.models.ActivityResponse
import com.arcusys.learn.models.ValidPeriod
import com.arcusys.learn.models.response.users.UserShortResponse
import com.arcusys.learn.models.BadgeResponse

trait CertificateFacadeContract {
  def getAll(companyID: Int, skip: Int, take: Int, filter: String,
    sortBy: CertificateSortBy, isSortDirectionAsc: Boolean, isShortResult: Boolean): Seq[Any]

  def getGoalsStatuses(certificateId: Int, userId: Int): GoalsStatusResponse

  def getCertificatesByUserWithOpenBadges(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract]

  def getCertificatesByUserWithOpenBadges(companyID: Int,
    userId: Int,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract]

  def getCertificatesCountByUserWithOpenBadges(companyID: Int,
    filter: String,
    userId: Int,
    isOnlyPublished: Boolean): Int
  def allCount(companyID: Int, filter: String): Int

  def getStatements(certificateId: Int): Iterable[StatementResponse]

  def getActivities(certificateId: Int): Iterable[ActivityResponse]

  def getById(certificateId: Int): CertificateResponseContract

  def getJoinedUsers(certificateId: Int,
    filterName: String,
    orgId: Int,
    sortBy: CertificateSortBy,
    sortAscDirection: Boolean,
    skip: Int,
    take: Int): Iterable[(String, UserResponseWithCertificateStatus)]

  def getJoinedUsersCount(certificateId: Int,
    filterName: String,
    orgId: Int): Int

  def getFreeStudents(certificateId: Int,
    filterName: String,
    orgId: Int,
    sortBy: CertificateSortBy,
    sortAscDirection: Boolean,
    skip: Int,
    take: Int): Iterable[UserShortResponse]

  def getFreeStudentsCount(certificateId: Int,
    orgId: Int,
    filter: String): Int

  def create(companyId: Int,
    title: String,
    description: String): Any

  def addCourse(certificateId: Int, courseId: Int)

  def addUser(certificateId: Int, userId: Int)

  def addActivity(certificateId: Int, activityName: String, count: Int)

  def addStatementObj(certificateId: Int, verb: String, obj: String)

  def deleteCourse(certificateId: Int, courseId: Int)

  def deleteUser(certificateId: Int, userId: Int)

  def deleteActivity(certificateId: Int, activityName: String)

  def deleteStatementObj(certificateId: Int, verb: String, obj: String)

  def change(id: Int,
    title: String,
    description: String,
    validPeriod: ValidPeriod,
    isOpenBadgesIntegration: Boolean,
    shortDescription: String = "",
    companyId: Int,
    scope: Option[Long]): CertificateResponseContract

  def changeLogo(id: Int, logo: String = "")

  def changeCourse(certificateId: Int, courseId: Int, v: Int, pT: PeriodType)

  def changeActivity(certificateId: Int, activityName: String, count: Int, v: Int, pT: PeriodType)

  def changeStatementObjPeriod(certificateId: Int, verb: String, obj: String, value: Int, period: PeriodType)

  def delete(id: Int)

  def getForUser(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract]

  def getForUserWithStatus(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract]

  def getForUser(companyID: Int,
    userId: Int,
    isShortResult: Boolean): Seq[CertificateResponseContract]

  def getAvailableForUser(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean,
    scope: Option[Long]): Seq[CertificateResponseContract]

  def forUserCount(companyID: Int, userId: Int): Int

  def availableForUserCount(companyID: Int,
    userId: Int,
    isOnlyPublished: Boolean,
    scope: Option[Long]): Int

  def getIssuerBadge(certificateId: Int,
    liferayUserId: Int,
    rootUrl: String): BadgeResponse

  def clone(certificateId: Int): CertificateResponseContract

  def publish(certificateId: Int): CertificateResponseContract

  def unpublish(certificateId: Int): CertificateResponseContract
}

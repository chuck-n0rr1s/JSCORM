package com.arcusys.learn.facades

import com.escalatesoft.subcut.inject.{ Injectable, BindingModule }
import com.arcusys.learn.ioc.Configuration
import com.arcusys.learn.models.request.{ CertificateSortBy }
import com.arcusys.learn.models.request.CertificateSortBy.CertificateSortBy
import com.arcusys.learn.scorm.certificating._
import com.arcusys.learn.liferay.services.{ SocialActivityLocalServiceHelper, UserLocalServiceHelper }
import com.arcusys.learn.settings.model.{ EmptySetting, SettingType }
import java.security.MessageDigest
import com.arcusys.learn.controllers.oauth.utils.HexHelper
import com.arcusys.learn.setting.storage.SettingStorage
import org.joda.time.DateTime
import com.arcusys.learn.models._
import scala.util.{ Failure, Success, Try }
import scala.util.matching.Regex
import org.joda.time.format.ISODateTimeFormat
import com.arcusys.learn.scorm.tracking.model.certificating.PeriodType.PeriodType
import com.arcusys.learn.scorm.tracking.model.certificating._
import com.arcusys.learn.models.response.certificates._
import scala.Some
import com.arcusys.learn.scorm.certificating.models.CertificateActivitySettings
import com.arcusys.learn.models.BadgeModel
import com.arcusys.learn.scorm.certificating.models.CertificateCourseSettings
import com.arcusys.learn.scorm.certificating.models.CertificateStatementObjSettings
import com.arcusys.learn.models.ActivityResponse
import com.arcusys.learn.models.ValidPeriod
import com.arcusys.learn.models.BadgeResponse
import com.arcusys.learn.models.StatementResponse
import com.arcusys.learn.models.IssuerModel
import com.arcusys.learn.models.response.users.{ UserResponseWithCertificateStatus, UserResponseUtils, UserShortResponse }
import com.arcusys.learn.service.util.OpenBadgesHelper
import com.arcusys.learn.models.GoalsStatusResponse
import scala.util.Failure
import scala.Some
import com.arcusys.learn.scorm.certificating.models.CertificateActivitySettings
import com.arcusys.learn.models.BadgeModel
import com.arcusys.learn.scorm.certificating.models.CertificateCourseSettings
import com.arcusys.learn.scorm.certificating.models.CertificateStatementObjSettings
import com.arcusys.learn.models.ActivityResponse
import com.arcusys.learn.scorm.tracking.model.certificating.PeriodType
import com.arcusys.learn.scorm.tracking.model.certificating.PeriodType.PeriodType
import scala.util.Success
import com.arcusys.learn.models.response.users.UserShortResponse
import com.arcusys.learn.models.ValidPeriod
import com.arcusys.learn.models.BadgeResponse
import com.arcusys.learn.models.response.certificates.CourseStatusResponse
import com.arcusys.learn.models.StatementResponse
import com.arcusys.learn.models.IssuerModel
import com.arcusys.learn.models.response.users.UserResponseWithCertificateStatus
import com.arcusys.learn.models.CertificateCourseResponse
import com.arcusys.learn.scorm.tracking.model.certificating.Certificate
import com.arcusys.learn.models.response.certificates.StatementStatusResponse
import com.arcusys.learn.models.response.certificates.ActivityStatusResponse
import com.arcusys.learn.models.response.certificates.CertificateResponse

class CertificateFacade(configuration: BindingModule) extends CertificateFacadeContract with Injectable with Paginator with CertificateStatusChecker {
  def this() = this(Configuration)

  implicit val bindingModule = configuration

  private lazy val certificateRepository = inject[CertificateRepositoryContract]
  private lazy val settingStorage = inject[SettingStorage]
  private lazy val courseFacade = inject[CourseFacadeContract]
  private lazy val userLocalServiceHelper = inject[UserLocalServiceHelper]
  private lazy val certificateToUserRepository = inject[CertificateUserRepositoryContract]
  private lazy val certificateToCourseRepository = inject[CertificateCourseSettingsRepositoryContract]
  private lazy val certificateToActivityRepository = inject[CertificateActivitySettingsRepositoryContract]
  private lazy val certificateToTincanStatementRepository = inject[CertificateStatementObjSettingsRepositoryContract]
  private lazy val userFacade = inject[UserFacadeContract]

  def getAll(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortBy: CertificateSortBy,
    isSortDirectionAsc: Boolean,
    isShortResult: Boolean): Seq[CertificateResponseContract] = {
    val all = certificateRepository.select(("companyID" -> companyID))

    val allFiltered = if (!filter.isEmpty)
      all.filter(i => i.title.toLowerCase.contains(filter.toLowerCase))
    else
      all

    val allSortedAZ = sortBy match {
      case CertificateSortBy.Name         => allFiltered.sortBy(_.title.toLowerCase)
      case CertificateSortBy.Description  => allFiltered.sortBy(_.description)
      case CertificateSortBy.CreationDate => allFiltered.sortBy(_.id)
    }

    takeForPage(allSortedAZ, skip, take, isSortDirectionAsc)
      .map(toCertificateResponse(isShortResult))
  }

  def getCertificatesByUserWithOpenBadges(companyID: Int,
    userId: Int,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract] = {
    val all = getForUser(companyID, userId)
      .filter(c => if (isOnlyPublished) c.isPublished else true)
      .filter(c => getStatus(c.id, userId) == CertificateStatus.Success)

    val allSortedAZ = all.sortBy(_.title.toLowerCase)

    val userEmail = userFacade.byId(userId).getEmailAddress

    val openbadges = OpenBadgesHelper.getOpenBadges(userEmail)
      .map(x => Certificate(id = -1, title = x("title").toString, description = x("description").toString, logo = x("logo").toString, companyId = companyID, createdAt = DateTime.now))
      .filter(p => !allSortedAZ.exists(c => c.title == p.title))

    (allSortedAZ ++ openbadges).map(toCertificateResponse(true))
  }

  def getCertificatesByUserWithOpenBadges(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract] = {
    val all = getCertificatesByUserWithOpenBadges(companyID, userId, isOnlyPublished)
    val allFiltered = if (filter.isEmpty)
      all
    else
      all.filter(i => i.title.toLowerCase.contains(filter.toLowerCase))

    takeForPage(allFiltered, skip, take, sortAZ)
  }

  def getCertificatesCountByUserWithOpenBadges(companyID: Int,
    filter: String,
    userId: Int,
    isOnlyPublished: Boolean): Int = {
    val all = getCertificatesByUserWithOpenBadges(companyID, userId, isOnlyPublished)
    val allFiltered = if (filter.isEmpty)
      all
    else
      all.filter(i => i.title.toLowerCase.contains(filter.toLowerCase))

    allFiltered.length
  }

  def getGoalsStatuses(certificateId: Int, userId: Int): GoalsStatusResponse = GoalsStatusResponse(
    courses = getCoursesStatuses(certificateId, userId).map(x => CourseStatusResponse(x._1, x._2.toString)),
    activities = getActivitiesStatuses(certificateId, userId).map(x => ActivityStatusResponse(x._1, x._2.toString)),
    statements = getStatementsStatuses(certificateId, userId).map(x => StatementStatusResponse(x._1, x._2, x._3.toString)))

  def allCount(companyID: Int, filter: String): Int = if (!filter.isEmpty)
    certificateRepository.select(("companyID" -> companyID)).count(i => i.title.toLowerCase.contains(filter.toLowerCase))
  else
    certificateRepository.select(("companyID" -> companyID)).length

  def forUserCount(companyID: Int, userId: Int): Int = getForUser(companyID, userId).length

  def availableForUserCount(companyID: Int, userId: Int, isOnlyPublished: Boolean, scope: Option[Long]): Int = {
    val certificates = scope match {
      case Some(value) => getAvailableForUser(companyID, userId, isOnlyPublished)
        .filter(x => x.scope.isDefined)
        .filter(x => x.scope.get == value)

      case None => getAvailableForUser(companyID, userId, isOnlyPublished)
    }

    certificates.length
  }

  def getCourses(certificate: Certificate): List[CertificateCourseResponse] = certificateToCourseRepository
    .select(("certificateId" -> certificate.id))
    .map(c => {
      val course = courseFacade.getCourse(c.courseId)
      new CertificateCourseResponse(c.courseId, c.certificateId, course.title, c.value, c.periodType.getOrElse(PeriodType.UNLIMITED).toString)
    })
    .toList

  def getStatements(certificateId: Int): List[StatementResponse] =
    certificateToTincanStatementRepository
      .select(("certificateId") -> certificateId)
      .map(c => new StatementResponse(c.certificateId, c.obj, c.verb, c.value, c.period.getOrElse(PeriodType.UNLIMITED).toString))
      .toList

  def getActivities(certificateId: Int): List[ActivityResponse] = certificateToActivityRepository
    .select("certificateId" -> certificateId)
    .map(c => new ActivityResponse(c.certificateId, c.count, c.activityName, c.value, c.periodType.toString))
    .toList

  def getById(certificateId: Int): CertificateResponse = {
    val c = certificateRepository.get("id" -> certificateId)
    toCertificateResponse(false)(c)
    //
    val users = getUsers(c)
    val courses = getCourses(c)
    val statements = getStatements(c.id)
    val activities = getActivities(c.id)
    CertificateResponse(c.id, c.title, c.shortDescription, c.description, c.logo, c.isPublished, new ValidPeriod(Some(c.validPeriod), c.validPeriodType.toString), c.createdAt, c.isPublishBadge, courses, statements, activities, users, None)
  }

  def getIssuerBadge(certificateId: Int, liferayUserId: Int, rootUrl: String): BadgeResponse = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val recipient = "sha256$" + hashEmail(userLocalServiceHelper.getUser(liferayUserId).getEmailAddress)
    val issuerName = settingStorage
      .getByKey(SettingType.IssuerName)
      .getOrElse(EmptySetting(SettingType.IssuerName))
      .value

    val issuerOrganization = settingStorage
      .getByKey(SettingType.IssuerOrganization)
      .getOrElse(EmptySetting(SettingType.IssuerOrganization))
      .value

    val issuerUrl = settingStorage.getByKey(SettingType.IssuerURL)
      .getOrElse(EmptySetting(SettingType.IssuerOrganization, rootUrl))
      .value

    val issueOn = DateTime.now.toString("yyyy-MM-dd")
    val imageUrl = if (certificate.logo == "")
      "%s/learn-portlet/img/certificate-default.png".format(rootUrl)
    else
      "%s/learn-portlet/api/files/images?folderId=%s&file=%s".format(rootUrl, certificate.id, certificate.logo)

    val description = certificate.shortDescription.replaceAll("%20", " ")

    val issue = IssuerModel(issuerUrl, issuerName, issuerOrganization)
    val badge = BadgeModel(certificate.title, imageUrl, description, rootUrl, issue)

    BadgeResponse(recipient, issueOn, badge)
  }

  def getUsers(certificate: Certificate): Map[String, UserShortResponse] = {
    val formatter = ISODateTimeFormat.dateTime()
    Try(certificateToUserRepository
      .getLeft(certificate)
      .map(u => (formatter.print(u._1), UserShortResponse(u._2.getUserId, u._2.getFullName)))
      .toMap
    )
      .getOrElse(Map())
  }

  def getJoinedUsers(certificateId: Int,
    filterName: String,
    orgId: Int,
    sortBy: CertificateSortBy,
    sortAscDirection: Boolean,
    skip: Int,
    take: Int): Iterable[(String, UserResponseWithCertificateStatus)] = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val certificateStudents = certificateToUserRepository.getLeft(certificate)
    val studentUserIds = certificateStudents.map(student => student._2.getUserId)
    val userFacade = inject[UserFacadeContract]
    val formatter = ISODateTimeFormat.dateTime()
    val users = userFacade
      .all(certificate.companyId)
      .filter(user => studentUserIds.contains(user.getUserId.toInt))
      .filter(user => if (orgId != -1) user.getOrganizationIds.contains(orgId) else true)
      .filter(user => {
        if (filterName != "")
          user.getFullName.toLowerCase.contains(filterName.toLowerCase)
        else
          true
      })
      .map(u => (
        formatter.print(certificateStudents.find(v => v._2.getUserId == u.getUserId).head._1),
        UserResponseWithCertificateStatus(u.getUserId, u.getFullName, UserResponseUtils.getPortraitUrl(u), UserResponseUtils.getPublicUrl(u), getStatus(certificate.id, u.getUserId.toInt).toString)))
      .sortBy(u => sortBy match {
        case CertificateSortBy.UserJoined => u._1
        case _                            => u._2.name
      })

    takeForPage(users, skip, take, !sortAscDirection)
  }

  def getJoinedUsersCount(certificateId: Int,
    filterName: String,
    orgId: Int): Int = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val certificateStudents = certificateToUserRepository.getLeft(certificate)
    val studentUserIds = certificateStudents.map(student => student._2.getUserId)
    val userFacade = inject[UserFacadeContract]
    userFacade
      .all(certificate.companyId)
      .filter(user => studentUserIds.contains(user.getUserId.toInt))
      .filter(user => if (orgId != -1) user.getOrganizationIds.contains(orgId) else true)
      .filter(user =>
        if (filterName != "")
          user.getFullName.toLowerCase.contains(filterName.toLowerCase)
        else
          true)
      .length
  }

  def getFreeStudents(certificateId: Int,
    filterName: String,
    orgId: Int,
    sortBy: CertificateSortBy,
    sortAscDirection: Boolean,
    skip: Int,
    take: Int): Iterable[UserShortResponse] = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val certificateStudents = certificateToUserRepository.getLeft(certificate)
    val studentUserIds = certificateStudents.map(student => student._2.getUserId)
    val userFacade = inject[UserFacadeContract]
    var users = userFacade
      .all(certificate.companyId)
      .filter(user => !studentUserIds.contains(user.getUserId.toInt))
      .filter(user => if (orgId != -1) user.getOrganizationIds.contains(orgId) else true)
    users = users.filter(user => {
      if (filterName != "")
        user.getFullName.toLowerCase.contains(filterName.toLowerCase)
      else
        true
    })

    takeForPage(users.map(u => UserShortResponse(u.getUserId, u.getFullName, UserResponseUtils.getPortraitUrl(u), UserResponseUtils.getPublicUrl(u))), skip, take, sortAscDirection)
  }

  def getFreeStudentsCount(certificateId: Int,
    orgId: Int,
    filterName: String): Int = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val certificateStudents = certificateToUserRepository.getLeft(certificate)
    val studentUserIds = certificateStudents.map(student => student._2.getUserId)
    val userFacade = inject[UserFacadeContract]
    var users = userFacade
      .all(certificate.companyId)
      .filter(user => !studentUserIds.contains(user.getUserId.toInt))
      .filter(user => if (orgId != -1) user.getOrganizationIds.contains(orgId) else true)
    users = users.filter(user => {
      if (filterName != "")
        user.getFullName.toLowerCase.contains(filterName.toLowerCase)
      else
        true
    })
    users.count(p => true)
  }

  private def getForUser(companyID: Int, userId: Int): Seq[Certificate] = {
    val user = userLocalServiceHelper.getUserById(companyID, userId)
    certificateToUserRepository.getRight((DateTime.now, user))
  }

  private def filtering(certificates: Seq[Certificate], filter: String) = if (filter.isEmpty)
    certificates
  else
    certificates.filter(i => i.title.toLowerCase.contains(filter.toLowerCase))

  private def getAvailableForUser(companyID: Int,
    userId: Int,
    isOnlyPublished: Boolean): Seq[Certificate] = {
    val usersCertificates = getForUser(companyID, userId)

    val all = certificateRepository
      .select("companyID" -> companyID)
      .filter(c => if (isOnlyPublished) c.isPublished else true)

    all.filter(certificate => !usersCertificates.exists(c => c.id == certificate.id))
  }

  def getAvailableForUser(companyID: Int,
    filter: String,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract] = {
    val availableCertificates = getAvailableForUser(companyID, userId, isOnlyPublished)
    val filtered = filtering(availableCertificates, filter)
    filtered.map(toCertificateResponse(isShortResult))
  }

  def getAvailableForUser(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean,
    scope: Option[Long]): Seq[CertificateResponseContract] = {
    val certificates = scope match {
      case Some(value) => getAvailableForUser(companyID, userId, isOnlyPublished)
        .filter(x => x.scope.isDefined)
        .filter(x => x.scope.get == value)

      case None => getAvailableForUser(companyID, userId, isOnlyPublished)
    }

    val filteredCertificates = filtering(certificates, filter)
    val allSortedAZ = filteredCertificates.sortBy(_.title.toLowerCase)
    takeForPage(allSortedAZ, skip, take, sortAZ)
      .map(toCertificateResponse(isShortResult))
  }

  override def getForUser(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract] = {
    val all = getForUser(companyID, userId).filter(c => if (isOnlyPublished) c.isPublished else true)
    val allFiltered = filtering(all, filter)
    val allSortedAZ = allFiltered.sortBy(_.title.toLowerCase)
    takeForPage(allSortedAZ, skip, take, sortAZ)
      .map(toCertificateResponse(isShortResult))
  }

  override def getForUserWithStatus(companyID: Int,
    skip: Int,
    take: Int,
    filter: String,
    sortAZ: Boolean,
    userId: Int,
    isShortResult: Boolean,
    isOnlyPublished: Boolean): Seq[CertificateResponseContract] = {
    val all = getForUser(companyID, userId).filter(c => if (isOnlyPublished) c.isPublished else true)
    val allFiltered = filtering(all, filter)
    val allSorted = allFiltered.sortBy(_.title.toLowerCase)

    val c = takeForPage(allSorted, skip, take, sortAZ)

    c.map(toCertificateWithUserStatusResponse(userId))
  }

  override def getForUser(companyID: Int,
    userId: Int,
    isShortResult: Boolean): Seq[CertificateResponseContract] = getForUser(companyID, userId)
    .reverse
    .map(toCertificateResponse(isShortResult))

  def addCourse(certificateId: Int, courseId: Int) = {
    certificateToCourseRepository.create(CertificateCourseSettings(certificateId, courseId))
  }

  def addUser(certificateId: Int, userId: Int) = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val userFacade = inject[UserFacadeContract]
    val user = userFacade.byId(userId)

    certificateToUserRepository.create(certificate, (DateTime.now, user))
  }

  def addActivity(certificateId: Int, activityName: String, count: Int = 1) = {
    certificateToActivityRepository.create(CertificateActivitySettings(certificateId, activityName, count, None, PeriodType.UNLIMITED))
  }

  def deleteCourse(certificateId: Int, courseId: Int) = {
    certificateToCourseRepository.delete("certificateId" -> certificateId, "courseId" -> courseId)
  }

  def deleteUser(certificateId: Int, userId: Int) = {
    val certificate = certificateRepository.get("id" -> certificateId)
    val userFacade = inject[UserFacadeContract]
    val user = userFacade.byId(userId)

    certificateToUserRepository.delete(certificate, (DateTime.now, user))
  }

  def deleteActivity(certificateId: Int, activityName: String) = {
    certificateToActivityRepository.delete("certificateId" -> certificateId, "activityName" -> activityName)
  }

  def create(companyId: Int, title: String, description: String): CertificateResponseContract = {
    val certificate = new Certificate(0, title, description, companyId = companyId, createdAt = new DateTime)
    getById(certificateRepository.create(certificate).id)
  }

  def change(id: Int,
    title: String,
    description: String,
    validPeriod: ValidPeriod,
    isOpenBadgesIntegration: Boolean,
    shortDescription: String = "",
    companyId: Int,
    scope: Option[Long]): CertificateResponseContract = {
    val certificate = certificateRepository.get("id" -> id)

    val c = if (validPeriod.value.isEmpty || validPeriod.value.get < 1)
      new Certificate(id, title, description, certificate.logo, true, isOpenBadgesIntegration, shortDescription, companyId, PeriodType.UNLIMITED, 0, certificate.createdAt, certificate.isPublished, scope)
    else
      new Certificate(id, title, description, certificate.logo, true, isOpenBadgesIntegration, shortDescription, companyId, PeriodType(validPeriod.valueType), validPeriod.value.getOrElse(0), certificate.createdAt, certificate.isPublished, scope)

    certificateRepository.modify(c)
    getById(id)
  }

  def changeLogo(id: Int, newLogo: String = "") {
    val certificate = certificateRepository.get("id" -> id)
    certificateRepository.modify(certificate.copy(logo = newLogo))
  }

  def changeCourse(certificateId: Int, courseId: Int, v: Int, pT: PeriodType) = {
    val c = certificateToCourseRepository.get("certificateId" -> certificateId, "courseId" -> courseId)

    val pT1 = if (v < 1)
      PeriodType.UNLIMITED
    else
      pT

    certificateToCourseRepository.modify(c.copy(value = Some(v), periodType = Some(pT1)))
  }

  def changeActivity(certificateId: Int,
    activityName: String,
    count: Int,
    v: Int,
    pT: PeriodType) = {
    val c = certificateToActivityRepository.get("certificateId" -> certificateId, "activityName" -> activityName)

    val pT1 = if (v < 1)
      PeriodType.UNLIMITED
    else
      pT

    certificateToActivityRepository.modify(c.copy(value = Some(v), periodType = pT1, count = count))
  }

  def delete(id: Int) = {
    certificateRepository.delete("id" -> id)

    SocialActivityLocalServiceHelper.deleteActivities(classOf[Certificate].getName, id)
  }

  private def getCertificateIndexInTitle(title: String): Int = {
    "copy \\d+".r.findFirstIn(title) match {
      case Some(str) => {
        val index = str.replace("copy ", "")
        Try(index.toInt) match {
          case Success(value) => value
          case Failure(value) => 0
        }
      }
      case None => 0
    }
  }

  private def getCertificateRawTitle(c: Certificate, pattern: String): String = {
    val t = c.title
      .replace(pattern, "")
    if (t.lastIndexOf(" ") == -1)
      t
    else
      t.dropRight(1)
  }

  def clone(certificateId: Int): CertificateResponse = {
    val certificate = certificateRepository.get("id" -> certificateId)

    // holly cow
    val newTitle = "copy \\d+".r.findFirstIn(certificate.title) match {
      case Some(value) => {
        val rawTitle = getCertificateRawTitle(certificate, value)
        val certificates = certificateRepository.getByTitle(rawTitle + " copy %") ++ Seq(certificate)

        val maxIndex = certificates
          .map(c => c.title)
          .maxBy(c => getCertificateIndexInTitle(c))

        rawTitle + " copy " + (getCertificateIndexInTitle(maxIndex) + 1)
      }
      case None => {
        val certificates = certificateRepository.getByTitle(certificate.title + " copy %") ++ Seq(certificate)
        val maxIndex = certificates
          .map(c => c.title)
          .maxBy(c => getCertificateIndexInTitle(c))

        certificate.title + " copy " + (getCertificateIndexInTitle(maxIndex) + 1)
      }
    }

    val newCertificate = certificateRepository.create(
      certificate.copy(
        title = newTitle,
        isPublished = false))

    // copy relationships
    certificateToCourseRepository
      .select("certificateId" -> certificate.id)
      .foreach(c => certificateToCourseRepository.create(CertificateCourseSettings(newCertificate.id, c.courseId, c.value, c.periodType)))

    certificateToUserRepository
      .getLeft(certificate)
      .foreach(user => certificateToUserRepository.create(newCertificate, user))

    certificateToActivityRepository
      .select("certificateId" -> certificate.id)
      .foreach(activity => certificateToActivityRepository.create(CertificateActivitySettings(newCertificate.id, activity.activityName, 1, activity.value, activity.periodType)))

    certificateToTincanStatementRepository
      .select("certificateId" -> certificate.id)
      .foreach(st => certificateToTincanStatementRepository.create(
        CertificateStatementObjSettings(newCertificate.id, st.verb, st.obj, st.value, st.period)))

    if (!certificate.logo.isEmpty) {
      val fileFacade = inject[FileFacadeContract]
      val img = fileFacade.getFileContent(certificate.id.toString, certificate.logo)
      fileFacade.saveFile(newCertificate.id.toString, certificate.logo, img)
    }
    getById(newCertificate.id)
  }

  def publish(certificateId: Int): CertificateResponse = {
    val certificate = certificateRepository.get("id" -> certificateId)

    //  SocialActivityLocalServiceHelper.addActivity(0, 0, classOf[Certificate].getName, certificate.id, CertificateActionType.NEW.id, StringPoolHelper.BLANK, 0)

    certificateRepository.modify(certificate.copy(isPublished = true))
    getById(certificate.id)
  }

  def unpublish(certificateId: Int): CertificateResponse = {
    val certificate = certificateRepository.get("id" -> certificateId)

    // SocialActivityLocalServiceHelper.deleteActivities(classOf[Certificate].getName, certificate.id)

    certificateRepository.modify(certificate.copy(isPublished = false))
    getById(certificate.id)
  }

  private def hashEmail(email: String) = {
    val md = MessageDigest.getInstance("SHA-256")
    md.update(email.getBytes)
    HexHelper().toHexString(md.digest())
  }

  private def toCertificateResponse(isShortResult: Boolean)(c: Certificate): CertificateResponseContract = {
    val users = getUsers(c)
    val courses = getCourses(c)
    val statements = getStatements(c.id)
    val activities = getActivities(c.id)
    val scope = c.scope match {
      case Some(value) => Some(courseFacade.getCourse(value.toInt))
      case None        => None
    }
    if (isShortResult)
      CertificateResponseFactory(c, courses.length, statements.length, activities.length, users.count(p => true), scope)
    else
      CertificateResponseFactory(c, courses, statements, activities, users, scope)
  }

  private def toCertificateWithUserStatusResponse(userId: Int)(c: Certificate): CertificateResponseContract = {
    val users = getUsers(c)
    val courses = getCourses(c)
    val statements = getStatements(c.id)
    val activities = getActivities(c.id)

    CertificateResponseFactory(c, courses.length, statements.length, activities.length, users.count(p => true), getStatus(c.id, userId))
  }

  override def deleteStatementObj(certificateId: Int, verb: String, obj: String): Unit = {
    certificateToTincanStatementRepository.delete("certificateId" -> certificateId, "verb" -> verb, "obj" -> obj)
  }

  override def changeStatementObjPeriod(certificateId: Int, verb: String, obj: String, value: Int, period: PeriodType) = {
    val settings = certificateToTincanStatementRepository.get("certificateId" -> certificateId, "verb" -> verb, "obj" -> obj)

    val pT1 = if (value < 1)
      PeriodType.UNLIMITED
    else
      period

    certificateToTincanStatementRepository.modify(settings.copy(value = Some(value), period = Some(pT1)))
  }

  override def addStatementObj(certificateId: Int, verb: String, obj: String): Unit = {
    certificateToTincanStatementRepository.create(CertificateStatementObjSettings(certificateId, verb, obj))
  }
}

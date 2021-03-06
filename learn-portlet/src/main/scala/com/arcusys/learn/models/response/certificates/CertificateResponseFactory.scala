package com.arcusys.learn.models.response.certificates

import com.arcusys.learn.models._
import com.arcusys.learn.models.StatementResponse
import com.arcusys.learn.scorm.tracking.model.certificating.Certificate
import com.arcusys.learn.models.ActivityResponse
import com.arcusys.learn.models.response.users.UserShortResponse
import com.arcusys.learn.scorm.tracking.model.certificating.CertificateStatus.CertificateStatus

/**
 * Created by Iliya Tryapitsin on 03.06.2014.
 */
object CertificateResponseFactory {
  def apply(c: Certificate,
    coursesCount: Int,
    statementsCount: Int,
    activitiesCount: Int,
    usersCount: Int,
    status: CertificateStatus) =
    CertificateWithUserStatusResponse(c.id, c.title, c.shortDescription, c.description, c.logo, c.isPublished, coursesCount, statementsCount, activitiesCount, usersCount, status.toString)

  def apply(c: Certificate,
    coursesCount: Int,
    statementsCount: Int,
    activitiesCount: Int,
    usersCount: Int,
    scope: Option[CourseResponse]) =
    CertificateShortResponse(c.id, c.title, c.shortDescription, c.description, c.logo, c.isPublished, coursesCount, statementsCount, activitiesCount, usersCount, scope)

  def apply(c: Certificate,
    courses: Iterable[CertificateCourseResponse],
    statements: Iterable[StatementResponse],
    activities: Iterable[ActivityResponse],
    users: Map[String, UserShortResponse],
    scope: Option[CourseResponse]) =
    CertificateResponse(c.id, c.title, c.shortDescription, c.description, c.logo, c.isPublished, new ValidPeriod(Some(c.validPeriod), c.validPeriodType.toString), c.createdAt, c.isPublishBadge, courses, statements, activities, users, scope)

  def apply(openbadges: Map[String, Any]) =
    CertificateShortResponse(-1, openbadges("title").toString, "", openbadges("description").toString, openbadges("logo").toString, true, 0, 0, 0, 0, None)
}

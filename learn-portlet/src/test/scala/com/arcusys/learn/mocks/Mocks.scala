package com.arcusys.learn.mocks

import java.nio.ByteBuffer
import java.util.UUID
import javax.servlet.http.Cookie

import com.arcusys.learn.facades._
import com.arcusys.learn.filestorage.storage.FileStorage
import com.arcusys.learn.liferay.LiferayClasses._
import com.arcusys.learn.liferay.services.UserLocalServiceHelper
import com.arcusys.learn.models.Gradebook.{ PackageGradeResponse, StudentResponse }
import com.arcusys.learn.models.request.{ CertificateRequest, CertificateSortBy }
import com.arcusys.learn.models.response.RoleResponse
import com.arcusys.learn.models.response.certificates.CertificateResponse
import com.arcusys.learn.models.response.users.{ UserResponseUtils, UserShortResponse }
import com.arcusys.learn.models.{ BadgeModel, BadgeResponse, CategoryResponse, IssuerModel, ValidPeriod }
import com.arcusys.learn.scorm.certificating.{ CertificateSiteStorage, CertificateStorage, CertificateUserStorage }
import com.arcusys.learn.scorm.course.CourseStorage
import com.arcusys.learn.scorm.tracking.model.PermissionType.PermissionType
import com.arcusys.learn.scorm.tracking.model.certificating.Certificate
import com.arcusys.learn.scorm.tracking.model.{ Course, PermissionType }
import com.arcusys.learn.service.util.SessionHandlerContract
import com.arcusys.learn.setting.storage.SettingStorage
import com.arcusys.learn.storage.StorageFactoryContract
import com.arcusys.learn.tincan.api.serializer.JsonDeserializer
import com.arcusys.learn.tincan.model._
import com.liferay.portal.model.{ Organization, User }
import org.joda.time.DateTime
import org.mockito.{ Matchers, Mockito }

import scala.collection.JavaConverters._
import scala.util.Random

/**
 * Created by Iliya Tryapitsin on 14.03.14.
 */
object Mocks {
  val certificateStorage = Mockito.mock(classOf[CertificateStorage])
  val certificateUserStorage = Mockito.mock(classOf[CertificateUserStorage])
  val fileFacadeContract = Mockito.mock(classOf[FileFacadeContract])
  val settingStorage = Mockito.mock(classOf[SettingStorage])
  val certificateSiteStorage = Mockito.mock(classOf[CertificateSiteStorage])
  val courseStorage = Mockito.mock(classOf[CourseStorage])
  val userLocalServiceHelper = Mockito.mock(classOf[UserLocalServiceHelper])
  val fileStorage = Mockito.mock(classOf[FileStorage])
  val sessionHandlerContract = Mockito.mock(classOf[SessionHandlerContract])
  val storageFactory = Mockito.mock(classOf[StorageFactoryContract])
  val certificateFacadeContract = Mockito.mock(classOf[CertificateFacadeContract])
  val roleFacade = Mockito.mock(classOf[RoleFacadeContract])
  val categoryFacade = Mockito.mock(classOf[CategoryFacadeContract])
  val gradebookFacade = Mockito.mock(classOf[GradebookFacadeContract])
  val userFacade = Mockito.mock(classOf[UserFacadeContract])
  val packageFacade = Mockito.mock(classOf[PackageFacadeContract])

  object General {
    val page = 1
    val count = 10
    val filter = ""
    val sortDirectionByAsc = true
  }

  object CategoryFacade {
    val courseId = 1
    val parentId = 1
    val title = "test title"
    val description = "test description"

    /* def getForCourseIdStub() = {
      SessionHandler.setTeacherPermissions()

      Mockito
        .stub(categoryFacade.getForCourseId(Matchers.eq(Option(courseId))))
        .toReturn(List(
        CategoryResponse(1, "test category 1", "test category description 1", -1),
        CategoryResponse(2, "test category 2", "test category description 2", -1)))

      Mockito
        .stub(categoryFacade.getForCourseId(Matchers.eq(None)))
        .toReturn(List(
        CategoryResponse(1, "test category 1", "test category description 1", -1),
        CategoryResponse(2, "test category 2", "test category description 2", -1),
        CategoryResponse(3, "test category 2", "test category description 3", -1)))

      CategoryFacade
    }*/

    def getChildStub() = {
      SessionHandler.setTeacherPermissions()

      Mockito
        .stub(categoryFacade.getChild(
          Matchers.any(classOf[Option[Int]]),
          Matchers.any(classOf[Option[Int]])))
        .toReturn(List(
          CategoryResponse(1, "test category 1", "test category description 1", parentId),
          CategoryResponse(2, "test category 2", "test category description 2", parentId)))

      CategoryFacade
    }

    def getChildWithQuestionsStub() = {
      SessionHandler.setTeacherPermissions()

      //      Mockito
      //        .stub(categoryFacade.getChildWithQuestion(
      //          Matchers.any(classOf[Option[Int]]),
      //          Matchers.any(classOf[Option[Int]]),
      //          Matchers.any(classOf[Seq[Int]]),
      //          Matchers.any(classOf[Seq[Int]])))
      //        .toReturn(List(
      //          CategoryResponse(1, "test category 1", "test category description 1", parentId),
      //          CategoryResponse(2, "test category 2", "test category description 2", parentId)))

      CategoryFacade
    }

    def createStub() = {
      SessionHandler.setTeacherPermissions()

      Mockito
        .stub(categoryFacade.create(
          Matchers.eq(title),
          Matchers.eq(description),
          Matchers.any(classOf[Option[Int]]),
          Matchers.any(classOf[Option[Int]])))
        .toReturn(CategoryResponse(1, title, description, parentId))

      CategoryFacade
    }

    def updateStub() = {
      SessionHandler.setTeacherPermissions()

      Mockito
        .stub(categoryFacade.update(
          Matchers.eq(parentId),
          Matchers.eq(title),
          Matchers.eq(description)))
        .toReturn(CategoryResponse(1, title, description, parentId))

      CategoryFacade
    }

    def getChildVerify() = {
      Mockito
        .verify(categoryFacade)
        .getChild(
          Matchers.any(classOf[Option[Int]]),
          Matchers.any(classOf[Option[Int]]))
      Mocks.SessionHandler.checkTeacherPermissionsVerify()

      CategoryFacade
    }

    def createVerify() = {
      SessionHandler.checkTeacherPermissionsVerify()

      Mockito
        .verify(categoryFacade)
        .create(
          Matchers.eq(title),
          Matchers.eq(description),
          Matchers.any(classOf[Option[Int]]),
          Matchers.any(classOf[Option[Int]]))

      CategoryFacade
    }

    def updateVerify() = {
      SessionHandler.checkTeacherPermissionsVerify()

      Mockito
        .verify(categoryFacade)
        .update(
          Matchers.eq(parentId),
          Matchers.eq(title),
          Matchers.eq(description))

      CategoryFacade
    }

    def deleteVerify() = {
      SessionHandler.checkTeacherPermissionsVerify()

      Mockito
        .verify(categoryFacade)
        .delete(Matchers.eq(parentId))

      CategoryFacade
    }

    def getChildWithQuestionsVerify() = {
      Mocks.SessionHandler.checkTeacherPermissionsVerify()

      Mockito
        .verify(categoryFacade)
        .getChildWithQuestion(
          Matchers.any(classOf[Option[Int]]),
          Matchers.any(classOf[Option[Int]]),
          Matchers.any(classOf[Seq[Int]]),
          Matchers.any(classOf[Seq[Int]]))

      CategoryFacade
    }

    /*def getForCourseIdVerify(course: Option[Int]) = {
      Mocks.SessionHandler.checkTeacherPermissionsVerify()
      Mockito
        .verify(categoryFacade)
        .getForCourseId(Matchers.eq(course))

      CategoryFacade
    }*/
  }

  object GradebookFacade {
    val courseId = 1
    val studentId = 1

    def getStudentsStub(filter: String = "",
      orgFilter: String = "",
      isSortDirectionAsc: Boolean = true,
      isShortResult: Boolean = true) = Mockito
      .stub(gradebookFacade.getStudents(
        Matchers.eq(courseId),
        Matchers.eq(General.page),
        Matchers.eq(General.count),
        Matchers.eq(filter),
        Matchers.eq(orgFilter),
        Matchers.eq(isSortDirectionAsc)))
      .toReturn(
        Seq(
          StudentResponse(1, "Janne Hietala", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.1f, "comment", 1, 5, Seq()),
          StudentResponse(2, "Jussi Hurskainen", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.2f, "comment", 2, 5, Seq()),
          StudentResponse(3, "Mika Kuikka", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().minusHours(1).toDateTimeISO.toString, 0.3f, "comment", 3, 5, Seq()),
          StudentResponse(4, "Juho Antilla", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().minusDays(1).toDateTimeISO.toString, 0.4f, "comment", 4, 5, Seq()),
          StudentResponse(5, "Timo Harstela", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().minusMinutes(10).toDateTimeISO.toString, 0.5f, "comment", 5, 5, Seq()),
          StudentResponse(6, "Timo Peltonen", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().minusMinutes(15).toDateTimeISO.toString, 0.5f, "comment", 0, 5, Seq())))

    //      } else {
    //        val activity = Activity(
    //          "Activity", "activityId-666",
    //          None, None, None, None, None,
    //          Set(), Seq(), Seq(), Seq(), Seq(), Seq(), Seq()
    //        )
    //        val verb = Verb("verbId_test2", Map("en" -> "experienced"))
    //        val result = Result(Option(Score(1, None, None, None)), None, None, None, None, Seq())
    //        val actor = Agent("Agent", Option("Test test"), Some("mail@mail.com"), None, None, None)
    //        val statement = Statement(UUID.randomUUID(), actor, verb, activity, Some(result), None, None, None, None, None, Seq())
    //
    //        val packages = Seq(
    //          PackageGradeResponse(1, "Package 1", "description", false, 10, Seq(
    //            statement,
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID())
    //
    //          )),
    //          PackageGradeResponse(2, "Package 2", "description", false, 10, Seq(
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()),
    //            statement.copy(id = UUID.randomUUID()))
    //          )
    //        )
    //
    //        Seq(
    //          StudentResponse(1, "Janne Hietala", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.1f, "comment", 1, 5, packages),
    //          StudentResponse(2, "Jussi Hurskainen", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.2f, "comment", 2, 5, packages),
    //          StudentResponse(3, "Mika Kuikka", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.3f, "comment", 3, 5, packages),
    //          StudentResponse(4, "Juho Antilla", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.4f, "comment", 4, 5, packages),
    //          StudentResponse(5, "Timo Harstela", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.5f, "comment", 5, 5, packages),
    //          StudentResponse(6, "Timo Peltonen", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.5f, "comment", 0, 5, packages))
    //
    //      })

    def getExtStudentsStub(filter: String = "",
      orgFilter: String = "",
      packageIds: Seq[Int] = Seq(),
      isSortDirectionAsc: Boolean = true) = {
      val activity = Activity(
        "Activity", "activityId-666",
        None, None, None, None, None,
        Set(), Seq(), Seq(), Seq(), Seq(), Seq(), Seq()
      )
      val verb = Verb("verbId_test2", Map("en" -> "experienced"))
      val result = Result(Option(Score(1, None, None, None)), None, None, None, None, Seq())
      val actor = Agent("Agent", Option("Test test"), Some("mail@mail.com"), None, None, None)
      val statement = Statement(UUID.randomUUID(), actor, verb, activity, Some(result), None, None, None, None, None, Seq())

      val packages = Seq(
        PackageGradeResponse(1, "", "Package 1", "description", false, "10", JsonDeserializer.serializeStatementResult(StatementResult(Seq(
          statement,
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID())), ""))
        ),
        PackageGradeResponse(2, "", "Package 2", "description", false, "10", JsonDeserializer.serializeStatementResult(StatementResult(Seq(
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID()),
          statement.copy(id = UUID.randomUUID())), ""))
        )
      )

      Mockito
        .stub(gradebookFacade.getExtStudents(
          Matchers.eq(courseId),
          Matchers.eq(General.page),
          Matchers.eq(General.count),
          Matchers.eq(filter),
          Matchers.eq(orgFilter),
          Matchers.any[Seq[Int]],
          Matchers.eq(isSortDirectionAsc)))
        .toReturn(Seq(
          StudentResponse(1, "Janne Hietala", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.1f, "comment", 1, 5, packages),
          StudentResponse(2, "Jussi Hurskainen", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.2f, "comment", 2, 5, packages),
          StudentResponse(3, "Mika Kuikka", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.3f, "comment", 3, 5, packages),
          StudentResponse(4, "Juho Antilla", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.4f, "comment", 4, 5, packages),
          StudentResponse(5, "Timo Harstela", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.5f, "comment", 5, 5, packages),
          StudentResponse(6, "Timo Peltonen", "", Seq("Joensuu, Finland"), Seq("Arcusys Ltd."), DateTime.now().toDateTimeISO.toString, 0.5f, "comment", 0, 5, packages)))
    }

    def getGradesForStudentStub(filter: String = "",
      packageIds: Seq[Int] = Seq(),
      isSortDirectionAsc: Boolean = true) = Mockito
      .stub(gradebookFacade.getGradesForStudent(
        Matchers.eq(courseId),
        Matchers.eq(studentId),
        Matchers.eq(General.page),
        Matchers.eq(General.count),
        Matchers.eq(isSortDirectionAsc)))
      .toReturn(StudentResponse(1, "Test 1", "avatar url", Seq("address"), Seq(), "last modified", 0.5f, "comment", 1, 10, Seq()))

    def getStudentsVerify(filter: String = "",
      orgFilter: String = "",
      isSortDirectionAsc: Boolean = true,
      isShortResult: Boolean = true) = {
      Mockito
        .verify(gradebookFacade)
        .getStudents(
          Matchers.eq(courseId),
          Matchers.eq(General.page),
          Matchers.eq(General.count),
          Matchers.eq(filter),
          Matchers.eq(orgFilter),
          Matchers.eq(isSortDirectionAsc))

      GradebookFacade
    }

    def getExtStudentsVerify(filter: String = "",
      orgFilter: String = "",
      packageIds: Seq[Int] = Seq(),
      isSortDirectionAsc: Boolean = true) = {
      Mockito
        .verify(gradebookFacade)
        .getExtStudents(
          Matchers.eq(courseId),
          Matchers.eq(General.page),
          Matchers.eq(General.count),
          Matchers.eq(filter),
          Matchers.eq(orgFilter),
          Matchers.any[Seq[Int]],
          Matchers.eq(isSortDirectionAsc))

      GradebookFacade
    }

    def getGradesForStudentVerify(filter: String = "",
      packageIds: Seq[Int] = Seq(),
      isSortDirectionAsc: Boolean = true) = {
      Mockito
        .verify(gradebookFacade)
        .getGradesForStudent(
          Matchers.eq(courseId),
          Matchers.eq(studentId),
          Matchers.eq(General.page),
          Matchers.eq(General.count),
          Matchers.eq(isSortDirectionAsc))

      GradebookFacade
    }
  }

  object CertificateStorage {
    def getByIdStub() = Mockito
      .stub(Mocks.certificateStorage.getByID(Matchers.any[Int]))
      .toReturn(Option(Certificate(1, "test title", "test description", "test logo", true, false, "short description", 2, createdAt = new DateTime())))
  }

  object UserLocalServiceHelper {

    def getUserStub(liferayFakeUser: User) = Mockito
      .stub(Mocks.userLocalServiceHelper.getUser(Matchers.any[Long]))
      .toReturn(liferayFakeUser)
  }

  object FileStorage {
    val contentLength = 100
  }

  object CourseStorage {
    val courseId = 1
    val userId = 1

    def getStub(courseId: Int = courseId, userId: Int = userId) = {
      Mockito
        .stub(courseStorage.get(Matchers.eq(courseId), Matchers.eq(userId)))
        .toReturn(Option(Course(courseId, userId, "0.5", "Test comment", Option(DateTime.now))))
    }

    def getVerify(courseId: Int = courseId, userId: Int = userId) = {
      Mockito
        .verify(courseStorage)
        .get(Matchers.eq(courseId), Matchers.eq(userId))
    }
  }

  object SessionHandler {
    def setTeacherPermissions() = {
      Mockito
        .stub(sessionHandlerContract.getAttribute(
          Matchers.any[Array[Cookie]],
          Matchers.eq("hasTeacherPermissions")))
        .toReturn(true)

      SessionHandler
    }

    def checkTeacherPermissionsVerify() = {
      Mockito
        .verify(sessionHandlerContract)
        .getAttribute(
          Matchers.any[Array[Cookie]],
          Matchers.eq("hasTeacherPermissions"))

      SessionHandler
    }
  }

  object CertificateFacade {
    val allCertificates = List[CertificateResponse](
      CertificateResponse(1, "Test certificate 1", "Test short description", "Test description", "test logo", true, null, new DateTime(), true, List(), List(), List(), Map("1" -> UserShortResponse(1000, "Test user")), None),
      CertificateResponse(2, "Test certificate 2", "Test short description", "Test description", "test logo", true, null, new DateTime(), true, List(), List(), List(), Map(), None))
    val page = 1
    val count = 10
    val companyId = 100
    val scope = Some(1)
    val sortBy = CertificateSortBy.Name
    val isSortDirectionAsc = true
    val filter = ""
    val userId = 1000
    val certificateId = 1
    val rootUrl = "test root url"
    val id = 1
    val isShortResult = false
    val isOnlyPublished = false

    val title = "test title"
    val description = "test description"
    val logo = "test logo"
    val isPermanent = true

    val publishBadge = false
    val shortDescription = "test short description"

    def getAllStub() = {
      Mockito
        .stub(certificateFacadeContract.getAll(
          Matchers.eq(companyId),
          Matchers.eq((page - 1) * count),
          Matchers.eq(count),
          Matchers.eq(filter),
          Matchers.eq(sortBy),
          Matchers.eq(isSortDirectionAsc),
          Matchers.eq(isShortResult)))
        .toReturn(allCertificates)

      CertificateFacade
    }

    def allCountStub(filter: String = "") = {
      Mockito
        .stub(certificateFacadeContract.allCount(Matchers.eq(companyId), Matchers.eq(filter)))
        .toReturn(allCertificates.length)

      CertificateFacade
    }

    def getAllVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .getAll(
          Matchers.eq(companyId),
          Matchers.eq((page - 1) * count),
          Matchers.eq(count),
          Matchers.eq(filter),
          Matchers.eq(sortBy),
          Matchers.eq(isSortDirectionAsc),
          Matchers.eq(isShortResult))

      CertificateFacade
    }

    def allCountVerify(filter: String = "") = {
      Mockito
        .verify(certificateFacadeContract)
        .allCount(Matchers.eq(companyId), Matchers.eq(filter))

      CertificateFacade
    }

    def getByIdStub() = {
      Mockito
        .stub(
          certificateFacadeContract
            .getById(Matchers.eq(certificateId)))
        .toReturn(
          allCertificates
            .filter(x => x.id == certificateId)
            .head)

      CertificateFacade
    }

    def getByIdVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .getById(Matchers.eq(certificateId))
      CertificateFacade
    }

    def getForUserStub() = {
      Mockito
        .stub(certificateFacadeContract.getForUser(
          Matchers.eq(companyId),
          Matchers.eq((page - 1) * count),
          Matchers.eq(count),
          Matchers.eq(""),
          Matchers.eq(isSortDirectionAsc),
          Matchers.eq(userId),
          Matchers.eq(isShortResult),
          Matchers.eq(isShortResult)))
        .toReturn(allCertificates.filter(x => x.users.filter(u => u._2.id == userId).count(p => true) > 0))

      CertificateFacade
    }

    def forUserCountStub() = {
      Mockito
        .stub(certificateFacadeContract.forUserCount(
          Matchers.eq(companyId),
          Matchers.eq(userId)))
        .toReturn(allCertificates.length)

      CertificateFacade
    }

    def getForUserVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .getForUser(
          Matchers.eq(companyId),
          Matchers.eq((page - 1) * count),
          Matchers.eq(count),
          Matchers.eq(""),
          Matchers.eq(isSortDirectionAsc),
          Matchers.eq(userId),
          Matchers.eq(isShortResult),
          Matchers.eq(isOnlyPublished))

      CertificateFacade
    }

    def forUserCountVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .forUserCount(
          Matchers.eq(companyId),
          Matchers.eq(userId))

      CertificateFacade
    }

    def getIssuerBadgeStub() = {
      Mockito
        .stub(
          certificateFacadeContract
            .getIssuerBadge(
              Matchers.eq(certificateId),
              Matchers.eq(userId),
              Matchers.eq("http://" + rootUrl)))
        .toReturn(
          BadgeResponse("test recipient", "test date",
            BadgeModel("test badge name", "image url", "test descrption", "test criteria",
              IssuerModel("test origin", "test name", "test org"))))

      CertificateFacade
    }

    def getIssuerBadgeVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .getIssuerBadge(
          Matchers.eq(certificateId),
          Matchers.eq(userId),
          Matchers.eq("http://" + rootUrl))

      CertificateFacade
    }

    def createStub() = {
      Mockito
        .stub(
          certificateFacadeContract
            .create(
              Matchers.eq(companyId),
              Matchers.eq(CertificateRequest.DEFAULT_TITLE),
              Matchers.eq(CertificateRequest.DEFAULT_DESCRIPTION)))
        .toReturn(allCertificates.last)

      CertificateFacade
    }

    def createVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .create(
          Matchers.eq(companyId),
          Matchers.eq(CertificateRequest.DEFAULT_TITLE),
          Matchers.eq(CertificateRequest.DEFAULT_DESCRIPTION))

      CertificateFacade
    }

    def deleteVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .delete(Matchers.eq(id))

      CertificateFacade
    }

    def changeStub() = {
      Mockito
        .stub(
          certificateFacadeContract
            .change(
              Matchers.eq(id),
              Matchers.eq(title),
              Matchers.eq(description),
              Matchers.any(classOf[ValidPeriod]),
              Matchers.eq(publishBadge),
              Matchers.eq(shortDescription),
              Matchers.eq(companyId),
              Matchers.any(classOf[Option[Long]])))
        .toReturn(allCertificates.last)

      CertificateFacade
    }

    def changeVerify() = {
      Mockito
        .verify(certificateFacadeContract)
        .change(
          Matchers.eq(id),
          Matchers.eq(title),
          Matchers.eq(description),
          Matchers.any(classOf[ValidPeriod]),
          Matchers.eq(publishBadge),
          Matchers.eq(shortDescription),
          Matchers.eq(companyId),
          Matchers.any(classOf[Option[Long]]))

      CertificateFacade
    }
  }

  object FileFacade {
    val fileId = 1
    val unExistFileId = 2
    val courseID = 1
    val userID = 1
    val groupID = 1

    def doFileContentStub() = {
      val content = ByteBuffer
        .allocate(FileStorage.contentLength)
        .putInt(Random.nextInt())
        .array()

      /*   Mockito
        .stub(fileFacadeContract.getFileContent(Matchers.eq(fileId)))
        .toReturn(content)

      Mockito
        .when(fileFacadeContract.getFileContent(Matchers.eq(unExistFileId)))
        .thenThrow(new EntityNotFoundException())*/

      FileFacade
    }

    /* def doFileContentVerify(id: Int) = {
      Mockito
        .verify(fileFacadeContract)
        .getFileContent(Matchers.eq(id))

      FileFacade
    }*/
  }

  object RoleFacade {
    val permissionType = PermissionType.TEACHER
    val roleId = 1
    val liferayRoleId = 1
    val isDefault = true
    val roleName = "test name"
    val roleDescription = "test description"

    def getForPermissionStub(permissionType: PermissionType = permissionType) = {
      Mockito.reset(roleFacade)

      Mockito
        .stub(roleFacade.getForPermission(Matchers.eq(permissionType)))
        .toReturn(List(RoleResponse(roleId, roleName, roleDescription, 1, permissionType.toString, isDefault)))
    }

    def createStub() = {
      Mockito
        .stub(roleFacade.create(Matchers.eq(liferayRoleId), Matchers.eq(permissionType)))
        .toReturn(RoleResponse(roleId, roleName, roleDescription, 1, permissionType.toString, isDefault))

      SessionHandler.setTeacherPermissions()
    }

    def updateStub() = {
      Mockito
        .stub(roleFacade.update(Matchers.eq(roleId), Matchers.eq(permissionType), Matchers.eq(isDefault)))
        .toReturn(RoleResponse(roleId, roleName, roleDescription, 1, permissionType.toString, isDefault))

      SessionHandler.setTeacherPermissions()
    }

    def getForPermissionVerify() = Mockito
      .verify(roleFacade)
      .getForPermission(Matchers.eq(permissionType))

    def createVerify() = {
      Mockito
        .verify(roleFacade)
        .create(Matchers.eq(roleId), Matchers.eq(permissionType))

      SessionHandler.checkTeacherPermissionsVerify()

      Mockito.reset(sessionHandlerContract)
      Mockito.reset(roleFacade)
    }

    def updateVerify() = {
      Mockito
        .verify(roleFacade)
        .update(Matchers.eq(roleId), Matchers.eq(permissionType), Matchers.eq(isDefault))

      SessionHandler.checkTeacherPermissionsVerify()

      Mockito.reset(sessionHandlerContract)
      Mockito.reset(roleFacade)
    }

    def deleteVerify() = {
      Mockito
        .verify(roleFacade)
        .delete(Matchers.eq(roleId), Matchers.eq(permissionType))

      SessionHandler.checkTeacherPermissionsVerify()
    }
  }

  object UserFacade {
    val courseId = 1
    val isShortResponse = true

    val roleIds = Array(1l, 2l, 3l)
    val firstUser = Mockito.mock(classOf[LUser])
    val secondUser = Mockito.mock(classOf[LUser])
    val users = Seq(firstUser, secondUser)

    Mockito
      .stub(UserResponseUtils.getPortraitUrl(Matchers.any[LUser]))
      .toReturn("test portrait")

    Mockito
      .stub(firstUser.getRoleIds)
      .toReturn(roleIds)

    Mockito
      .stub(firstUser.getUserId)
      .toReturn(1)

    Mockito
      .stub(firstUser.getOrganizations())
      .toReturn(List(Mockito.mock(classOf[Organization])).asJava)

    Mockito
      .stub(firstUser.getFullName)
      .toReturn("First user")

    Mockito
      .stub(firstUser.getUserId)
      .toReturn(1)

    Mockito
      .stub(secondUser.getRoleIds)
      .toReturn(Array(2l, 3l))

    def allStub() = {
      Mockito
        .stub(userFacade.all(
          Matchers.eq(courseId.toLong),
          Matchers.eq(General.page),
          Matchers.eq(General.count),
          Matchers.eq(General.filter),
          Matchers.eq(General.sortDirectionByAsc)))
        .toReturn(users)

      RoleFacade.getForPermissionStub(PermissionType.STUDENT)

      CourseStorage.getStub(courseId, firstUser.getUserId.toInt)

      UserFacade
    }

    def allVerify() = {
      Mockito
        .verify(userFacade)
        .all(
          Matchers.eq(courseId),
          Matchers.eq(General.page),
          Matchers.eq(General.count),
          Matchers.eq(General.filter),
          Matchers.eq(General.sortDirectionByAsc))

      CourseStorage.getVerify(courseId, firstUser.getUserId.toInt)

      UserFacade
    }
  }

}

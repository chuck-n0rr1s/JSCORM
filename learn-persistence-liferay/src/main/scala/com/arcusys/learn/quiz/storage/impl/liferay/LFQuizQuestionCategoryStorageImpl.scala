package com.arcusys.learn.quiz.storage.impl.liferay

import com.arcusys.learn.storage.impl.KeyedEntityStorage
import com.arcusys.learn.persistence.liferay.service.{LFQuizQuestionLocalServiceUtil, LFQuizQuestionCategoryLocalServiceUtil}
import com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory
import com.liferay.portal.kernel.dao.orm.QueryUtil._
import com.arcusys.learn.quiz.model.QuizQuestionCategory
import scala.collection.JavaConverters._
import com.arcusys.learn.storage.impl.liferay.LiferayCommon._

/**
 * User: dkudinov
 * Date: 15.3.2013
 */
trait LFQuizQuestionCategoryStorageImpl extends KeyedEntityStorage[QuizQuestionCategory] {
  protected def doRenew() {
    LFQuizQuestionCategoryLocalServiceUtil.removeAll()
  }

  def getOne(parameters: (String, Any)*) = throw new UnsupportedOperationException

  def getAll(parameters: (String, Any)*) = {
    parameters match {
      case Seq(("quizID", quizId: Int), ("parentID", parentId: Int)) =>
        val parentIdForSearch = if (parentId == -1) (nullInteger) else (new Integer(parentId))
        LFQuizQuestionCategoryLocalServiceUtil.findByQuizIdAndParentId(quizId, parentIdForSearch).asScala.map {
          extract
        }
      case Seq(("parentID", parentId: Int)) =>
        val parentIdForSearch = if (parentId == -1) (nullInteger) else (new Integer(parentId))
        LFQuizQuestionCategoryLocalServiceUtil.findByQuizIdAndParentId(null, parentIdForSearch).asScala.map {
          extract
        }
      case _ => LFQuizQuestionCategoryLocalServiceUtil.getLFQuizQuestionCategories(ALL_POS, ALL_POS).asScala.map(extract)
    }
  }

  def create(parameters: (String, Any)*) {
    throw new UnsupportedOperationException
  }

  def execute(sqlKey: String, parameters: (String, Any)*) {
    throw new UnsupportedOperationException
  }

  def getAll(sqlKey: String, parameters: (String, Any)*) = throw new UnsupportedOperationException

  def getOne(sqlKey: String, parameters: (String, Any)*) = throw new UnsupportedOperationException

  def modify(sqlKey: String, parameters: (String, Any)*) {
    throw new UnsupportedOperationException
  }

  def delete(parameters: (String, Any)*) {
    idParam(parameters: _*).foreach( id => {
      val category = LFQuizQuestionCategoryLocalServiceUtil.getLFQuizQuestionCategory(id)
      if (category != null) {
        val quizID = category.getQuizId
        val questions = LFQuizQuestionLocalServiceUtil.findByQuizAndCategory(quizID, category.getId.toInt).asScala
        questions.foreach(q => LFQuizQuestionLocalServiceUtil.deleteLFQuizQuestion(q.getId))
        val children = LFQuizQuestionCategoryLocalServiceUtil.findByQuizIdAndParentId(quizID, id).asScala
        children.foreach(c => delete("id"->c.getId.toInt))
      }
      LFQuizQuestionCategoryLocalServiceUtil.deleteLFQuizQuestionCategory(id)
    })
  }

  def modify(parameters: (String, Any)*) {
    idParam(parameters: _*).flatMap {
      getLFEntityById(_)
    }.foreach {
      lfEntity => doUpdateEntity(null, lfEntity, LFQuizQuestionCategoryLocalServiceUtil.updateLFQuizQuestionCategory(_), parameters: _*)
    }
  }

  private def getLFEntityById(id: Int) = Option(LFQuizQuestionCategoryLocalServiceUtil.getLFQuizQuestionCategory(id))

  def createAndGetID(entity: QuizQuestionCategory, parameters: (String, Any)*) = {
    doCreate(entity, parameters: _*).getId.toInt
  }

  private def doCreate(entity: QuizQuestionCategory, parameters: (String, Any)*) = {
    doUpdateEntity(entity, LFQuizQuestionCategoryLocalServiceUtil.createLFQuizQuestionCategory(), LFQuizQuestionCategoryLocalServiceUtil.addLFQuizQuestionCategory(_), parameters: _*)
  }

  private def doUpdateEntity(entity: QuizQuestionCategory, lfEntity: LFQuizQuestionCategory,
                             update: (LFQuizQuestionCategory) => LFQuizQuestionCategory,
                             parameters: (String, Any)*): LFQuizQuestionCategory = {
    (entity, parameters) match {
      case (entity: QuizQuestionCategory, params: Seq[(String, Any)]) => {
        //title: String, description: String, quizID: Int, parentID: Option[Int]
        lfEntity.setTitle(entity.title)
        lfEntity.setDescription(entity.description)
        lfEntity.setQuizId(entity.quizID)

        params.foreach(param => param match {
          case ("parentID", parentId: Option[Int]) => lfEntity.setParentId(parentId)
          case ("arrangementIndex", arrangementIndex: Int) => lfEntity.setArrangementIndex(arrangementIndex)
        }
        )
        update(lfEntity)
      }

      case (null, params: Seq[(String, Any)]) => {
        params.foreach {
          param => param match {
            case ("id", _) => () // skip
            case ("title", title: String) => lfEntity.setTitle(title)
            case ("description", description: String) => lfEntity.setDescription(description)
            case ("parentID", parentId: Option[Int]) =>
              parentId.foreach {
                lfEntity.setParentId(_)
              }
            case ("arrangementIndex", arrangementIndex: Int) => lfEntity.setArrangementIndex(arrangementIndex)
          }
        }
        update(lfEntity)
      }
    }
  }

  def createAndGetID(parameters: (String, Any)*) = throw new UnsupportedOperationException

  def getByID(id: Int, parameters: (String, Any)*) = getLFEntityById(id) map {
    extract
  }

  def create(entity: QuizQuestionCategory, parameters: (String, Any)*) {
    throw new UnsupportedOperationException
  }

  def modify(entity: QuizQuestionCategory, parameters: (String, Any)*) {
    throw new UnsupportedOperationException
  }

  def extract(lfentity: LFQuizQuestionCategory) = QuizQuestionCategory(
    lfentity.getId.toInt,
    lfentity.getTitle,
    lfentity.getDescription,
    lfentity.getQuizId.toInt,
    Option(lfentity.getParentId).map(_.toInt),
    lfentity.getArrangementIndex
  )

  // for some reason, compiler gives an error in Maven build if this function is moved to package object
  def idParam(parameters: (String, Any)*): Option[Int] = {
    parameters find {
      _._1 == "id"
    } map {
      _._2.asInstanceOf[Int]
    }
  }

}

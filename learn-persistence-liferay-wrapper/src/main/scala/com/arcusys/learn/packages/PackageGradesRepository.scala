package com.arcusys.learn.packages

import com.arcusys.learn.persistence.liferay.service._
import com.arcusys.learn.persistence.liferay.NoSuchLFPackageGradeStorageException
import com.arcusys.learn.scorm.manifest.model.PackageGrade
import com.arcusys.learn.persistence.liferay.service.persistence.LFPackageGradeStoragePK
import com.arcusys.learn.MutableEntityRepository

/**
 * Created by Iliya Tryapitsin on 28.04.2014.
 */
class PackageGradesRepository extends MutableEntityRepository[PackageGrade] {
  override def get(keys: (String, Any)*): PackageGrade = keys match {
    case Seq(("userId", userId: Long), ("packageId", packageId: Long)) => {
      val result = LFPackageGradeStorageLocalServiceUtil.findGrade(userId, packageId)
      PackageGrade(userId, packageId, result.getGrade, result.getComment)
    }

    case Seq(("userId", userId: Int), ("packageId", packageId: Int)) => {
      val result = LFPackageGradeStorageLocalServiceUtil.findGrade(userId, packageId)
      PackageGrade(userId, packageId, result.getGrade, result.getComment)
    }

    case _ => throw new NoSuchLFPackageGradeStorageException()
  }

  override def delete(parameters: (String, Any)*): Unit = parameters match {
    case Seq(("userId", userId: Long), ("packageId", packageId: Long)) => {
      val result = LFPackageGradeStorageLocalServiceUtil.findGrade(userId, packageId)
      LFPackageGradeStorageLocalServiceUtil.deleteLFPackageGradeStorage(result)
    }

    case _ => throw new NoSuchLFPackageGradeStorageException()
  }

  override def modify(entity: PackageGrade): PackageGrade = {
    val storageEntity = LFPackageGradeStorageLocalServiceUtil.findGrade(entity.userId, entity.packageId)

    storageEntity.setComment(entity.comment)
    storageEntity.setGrade(entity.grade)

    LFPackageGradeStorageLocalServiceUtil.updateLFPackageGradeStorage(storageEntity)

    PackageGrade(
      storageEntity.getUserId,
      storageEntity.getPackageId,
      storageEntity.getGrade,
      storageEntity.getComment)
  }

  override def create(entity: PackageGrade): PackageGrade = {
    val packageGradeStorage = LFPackageGradeStorageLocalServiceUtil
      .createLFPackageGradeStorage(new LFPackageGradeStoragePK(entity.userId, entity.packageId))

    packageGradeStorage.setComment(entity.comment)
    packageGradeStorage.setGrade(entity.grade)

    val addedPackageGradeStorage = LFPackageGradeStorageLocalServiceUtil.addLFPackageGradeStorage(packageGradeStorage)
    PackageGrade(
      addedPackageGradeStorage.getUserId,
      addedPackageGradeStorage.getPackageId,
      addedPackageGradeStorage.getGrade,
      addedPackageGradeStorage.getComment)
  }
}

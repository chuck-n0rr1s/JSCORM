package com.arcusys.learn.controllers.oauth

import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import com.arcusys.learn.test.mocks.MockInjectableFactory
import org.mockito.{ Matchers, Mockito }
import com.arcusys.learn.service.util.SessionHandlerContract
import org.apache.oltu.oauth2.common.OAuth
import org.apache.oltu.oauth2.common.message.types.ResponseType
import javax.servlet.http.{ HttpServletResponse }
import com.arcusys.scorm.lms.ClientApiStoreManagerContract
import com.arcusys.learn.controllers.oauth.utils.HMACSHA1Generator
import com.arcusys.learn.tincan.model.LrsScope
import com.arcusys.learn.tincan.model.lrsServer.ClientApiModel
import java.net._
import org.scalamock.proxy.ProxyMockFactory
import org.scalatra.test.scalatest.{ ScalatraSuite, ScalatraFlatSpec }
import org.junit.Ignore
import org.scalatest.FlatSpec

//
// Created by iliya.tryapitsin on 13.02.14.
//
@RunWith(classOf[org.scalatest.junit.JUnitRunner])
class InitializeControllerTest extends FlatSpec with ScalatraSuite with MockFactory with ProxyMockFactory with MockInjectableFactory {
  val clientApiStoreManager = Mockito.mock(classOf[ClientApiStoreManagerContract])
  val sessionHandlerContract = Mockito.mock(classOf[SessionHandlerContract])

  bindingModule.modifyBindings(module => {
    module.bind[ClientApiStoreManagerContract] toSingle clientApiStoreManager
    module.bind[SessionHandlerContract] toSingle sessionHandlerContract

    addServlet(new InitializeApiController(module), "/*")
  })

  ignore should "take fetch token" in {
    val signatureMethodName = "HMAC-SHA1"
    val clientId: Long = 123
    val clientName = "test client"
    val clientSecret = "CLIENT_SECRET"
    val clientIssuedAt = "ISSUED_AT"
    val expiredIn = 123456

    Mockito
      .when(clientApiStoreManager.isExistClient(clientId))
      .thenReturn(true)

    Mockito
      .when(clientApiStoreManager.getClientById(clientId))
      .thenReturn(new ClientApiModel(
        clientId.toString,
        clientSecret,
        clientName,
        clientIssuedAt,
        expiredIn,
        LrsScope.All))

    val params = Map[String, String](
      OAuth.OAUTH_CLIENT_ID -> clientId.toString,
      OAuth.OAUTH_RESPONSE_TYPE -> ResponseType.CODE.toString(),
      OAuth.OAUTH_VERSION_DIFFER -> signatureMethodName)
    val headers = Map[String, String]()

    val generator = new HMACSHA1Generator()
    val p = params
      .map({
        case (key, value) => "%s=%s".format(key, value)
      })
      .toSeq
      .sortBy((k) => k)
      .mkString("", "&", "")

    val localhost = InetAddress.getLocalHost

    val baseString = "%s&%s&%s".format(
      "get".toUpperCase(),
      URLEncoder.encode("http://" + localhost.getHostName + "/".toLowerCase(), "UTF-8"),
      URLEncoder.encode(p, "UTF-8"))

    val signature = generator.generateValue(baseString, clientSecret)

    get("/", params = (params ++ Map("oauth_signature" -> signature)).toSeq.sortBy((k) => k), headers = headers) {
      Mockito
        .verify(clientApiStoreManager)
        .getClientById(clientId)

      status should equal(HttpServletResponse.SC_FOUND)

      body should not be ""

      val b = body
      /*val resp = Json.toObject(b)

        val token: String = if (resp.isInstanceOf[Map[String, Any]])
          resp.asInstanceOf[Map[String, Any]]("code").toString
        else ""

        Mockito
          .verify(clientApiStoreManager)
          .setFetchToken(
            Matchers.eq(clientId),
            Matchers.eq(""))*/
    }
  }
}


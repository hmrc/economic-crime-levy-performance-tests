/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.ecl.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.Predef.{http, status}
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.ecl.Configuration

object AuthRequests extends Configuration {

  val registerAuthWizardUrl: String            = s"$registrationUrl/register-for-economic-crime-levy/"
  val authWizardRegisterRedirectionUrl: String = s"$registerAuthWizardUrl/aml-regulated-activity-question"
  val returnAuthWizardUrl: String              = s"$returnsUrl/submit-economic-crime-levy-return/"
  val eclBetaAccessUrl: String =
    s"$registerAuthWizardUrl/ecl-beta-access?continueUrl=http%3A%2F%2Flocalhost%3A14000%2Fregister-for-economic-crime-levy%2Faml-regulated-activity-question"
  val enrolmentAuthWizardUrl: String = s"$enrolmentUrl/add-economic-crime-levy/do-you-have-an-ecl-reference-number/"

  val navigateToRegisterAuthWizard: HttpRequestBuilder =
    http("Navigate to Register AuthWizard Page")
      .get(registerAuthWizardUrl)
      .check(status.is(200))

  val navigateToAuthWizardRedirectionUrl: HttpRequestBuilder =
    http("Navigate to auth wizard redirection url")
      .get(authWizardRegisterRedirectionUrl)
      .check(status.is(303))

  def submitRegisterAuthWizardForm(): HttpRequestBuilder =
    http("Log in with redirection url")
      .post(authWizardUrl)
      .formParam("authorityId", "")
      .formParam("gatewayToken", "")
      .formParam("redirectionUrl", authWizardRegisterRedirectionUrl)
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("additionalInfo.emailVerified", "N/A")
      .formParam("presets-dropdown", "IR-SA")
      .formParam("enrolment[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].value", "")
      .formParam("enrolment[0].state", "Activated")
      .formParam("enrolment[1].name", "")
      .formParam("enrolment[1].taxIdentifier[0].name", "")
      .formParam("enrolment[1].taxIdentifier[0].value", "")
      .formParam("enrolment[1].state", "Activated")
      .formParam("enrolment[2].name", "")
      .formParam("enrolment[2].taxIdentifier[0].name", "")
      .formParam("enrolment[2].taxIdentifier[0].value", "")
      .formParam("enrolment[2].state", "Activated")
      .formParam("enrolment[3].name", "")
      .formParam("enrolment[3].taxIdentifier[0].name", "")
      .formParam("enrolment[3].taxIdentifier[0].value", "")
      .formParam("enrolment[3].state", "Activated")
      .check(status.is(303))
      .check(header("Location").is(authWizardRegisterRedirectionUrl))

  val navigateToEclBetaAccess: HttpRequestBuilder =
    http("Navigate to /ecl Beta Access")
      .get(eclBetaAccessUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitEclBetaAccess(accessCode: String): HttpRequestBuilder =
    http("ECL Access code: " + accessCode)
      .post(eclBetaAccessUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", accessCode)
      .check(status.is(303))

  val navigateToReturnAuthWizard: HttpRequestBuilder =
    http("Navigate to auth wizard return redirection url")
      .get(returnAuthWizardUrl+"/period/22XY")
      .check(status.is(303))

  def submitReturnsAuthWizardForm(
    enrolmentKey: String = "HMRC-ECL-ORG",
    identifierName: String = "EclRegistrationReference",
    identifierValue: String = "XMECL0000000001"
  ): HttpRequestBuilder =
    http("Log in with redirection url")
      .post(authWizardUrl)
      .formParam("authorityId", "")
      .formParam("gatewayToken", "")
      .formParam("redirectionUrl", returnAuthWizardUrl+"/period/22XY")
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("oauthTokens.accessToken", "")
      .formParam("oauthTokens.refreshToken", "")
      .formParam("oauthTokens.idToken", "")
      .formParam("additionalInfo.emailVerified", "N/A")
      .formParam("presets-dropdown", "IR-SA")
      .formParam("enrolment[0].name", enrolmentKey)
      .formParam("enrolment[0].taxIdentifier[0].name", identifierName)
      .formParam("enrolment[0].taxIdentifier[0].value", identifierValue)
      .formParam("enrolment[0].state", "Activated")
      .formParam("enrolment[1].name", "")
      .formParam("enrolment[1].taxIdentifier[0].name", "")
      .formParam("enrolment[1].taxIdentifier[0].value", "")
      .formParam("enrolment[1].state", "Activated")
      .formParam("enrolment[2].name", "")
      .formParam("enrolment[2].taxIdentifier[0].name", "")
      .formParam("enrolment[2].taxIdentifier[0].value", "")
      .formParam("enrolment[2].state", "Activated")
      .formParam("enrolment[3].name", "")
      .formParam("enrolment[3].taxIdentifier[0].name", "")
      .formParam("enrolment[3].taxIdentifier[0].value", "")
      .formParam("enrolment[3].state", "Activated")
      .check(status.is(303))
      .check(header("Location").is(returnAuthWizardUrl+"/period/22XY"))

  val navigateToEnrolmentAuthWizard: HttpRequestBuilder =
    http("Navigate to auth wizard enrolment redirection url")
      .get(enrolmentAuthWizardUrl)
      .check(status.is(303))

  def submitEnrolmentAuthWizardForm(): HttpRequestBuilder =
    http("Enrolment redirection url")
      .post(authWizardUrl)
      .formParam("authorityId", "")
      .formParam("gatewayToken", "")
      .formParam("redirectionUrl", enrolmentAuthWizardUrl)
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("additionalInfo.emailVerified", "N/A")
      .formParam("presets-dropdown", "IR-SA")
      .formParam("enrolment[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].value", "")
      .formParam("enrolment[0].state", "Activated")
      .formParam("enrolment[1].name", "")
      .formParam("enrolment[1].taxIdentifier[0].name", "")
      .formParam("enrolment[1].taxIdentifier[0].value", "")
      .formParam("enrolment[1].state", "Activated")
      .formParam("enrolment[2].name", "")
      .formParam("enrolment[2].taxIdentifier[0].name", "")
      .formParam("enrolment[2].taxIdentifier[0].value", "")
      .formParam("enrolment[2].state", "Activated")
      .formParam("enrolment[3].name", "")
      .formParam("enrolment[3].taxIdentifier[0].name", "")
      .formParam("enrolment[3].taxIdentifier[0].value", "")
      .formParam("enrolment[3].state", "Activated")
      .check(status.is(303))
      .check(header("Location").is(enrolmentAuthWizardUrl))
}

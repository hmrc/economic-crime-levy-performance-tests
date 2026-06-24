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
import io.gatling.core.session.el._

object AuthRequests extends Configuration {

  val registerAuthWizardUrl: String            = s"$registrationUrl/register-for-economic-crime-levy/"
  val authWizardRegisterRedirectionUrl: String = s"$registerAuthWizardUrl/aml-regulated-activity-question"
  val returnAuthWizardUrl: String              = s"$returnsUrl/submit-economic-crime-levy-return/"
  val enrolmentAuthWizardUrl: String           = s"$enrolmentUrl/add-economic-crime-levy/do-you-have-an-ecl-reference-number/"
  val accountAuthWizardUrl: String             = s"$accountUrl/economic-crime-levy-account/"

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
      .formParam("authorityId", "".el[String])
      .formParam("gatewayToken", "".el[String])
      .formParam("redirectionUrl", authWizardRegisterRedirectionUrl.el[String])
      .formParam("credentialStrength", "strong".el[String])
      .formParam("confidenceLevel", "50".el[String])
      .formParam("affinityGroup", "Individual".el[String])
      .formParam("email", "user@test.com".el[String])
      .formParam("credentialRole", "User".el[String])
      .formParam("additionalInfo.emailVerified", "N/A".el[String])
      .formParam("presets-dropdown", "IR-SA".el[String])
      .formParam("enrolment[0].name", "".el[String])
      .formParam("enrolment[0].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[0].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[0].state", "Activated".el[String])
      .formParam("enrolment[1].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[1].state", "Activated".el[String])
      .formParam("enrolment[2].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[2].state", "Activated".el[String])
      .formParam("enrolment[3].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[3].state", "Activated".el[String])
      .formParam("csrfToken", "#{csrfToken}".el[String])
      .check(status.is(303))
      .check(header("Location".el[String]).is(authWizardRegisterRedirectionUrl))

  val navigateToReturnAuthWizard: HttpRequestBuilder =
    http("Navigate to auth wizard return redirection url")
      .get(returnAuthWizardUrl + "/period/23XY")
      .check(status.is(303))

  def submitReturnsAuthWizardForm(
    enrolmentKey: String = "HMRC-ECL-ORG",
    identifierName: String = "EclRegistrationReference",
    identifierValue: String = "XMECL0000000001"
  ): HttpRequestBuilder =
    http("Log in with redirection url")
      .post(authWizardUrl)
      .formParam("authorityId", "".el[String])
      .formParam("gatewayToken", "".el[String])
      .formParam("redirectionUrl", (returnAuthWizardUrl + "/period/23XY").el[String])
      .formParam("credentialStrength", "strong".el[String])
      .formParam("confidenceLevel", "50".el[String])
      .formParam("affinityGroup", "Individual".el[String])
      .formParam("email", "user@test.com".el[String])
      .formParam("credentialRole", "User".el[String])
      .formParam("oauthTokens.accessToken", "".el[String])
      .formParam("oauthTokens.refreshToken", "".el[String])
      .formParam("oauthTokens.idToken", "".el[String])
      .formParam("additionalInfo.emailVerified", "N/A".el[String])
      .formParam("presets-dropdown", "IR-SA".el[String])
      .formParam("enrolment[0].name", enrolmentKey.el[String])
      .formParam("enrolment[0].taxIdentifier[0].name", identifierName.el[String])
      .formParam("enrolment[0].taxIdentifier[0].value", identifierValue.el[String])
      .formParam("enrolment[0].state", "Activated".el[String])
      .formParam("enrolment[1].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[1].state", "Activated".el[String])
      .formParam("enrolment[2].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[2].state", "Activated".el[String])
      .formParam("enrolment[3].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[3].state", "Activated".el[String])
      .formParam("csrfToken", "#{csrfToken}".el[String])
      .check(status.is(303))
      .check(header("Location".el[String]).is(returnAuthWizardUrl + "/period/23XY"))

  val navigateToEnrolmentAuthWizard: HttpRequestBuilder =
    http("Navigate to auth wizard enrolment redirection url")
      .get(enrolmentAuthWizardUrl)
      .check(status.is(303))

  def submitEnrolmentAuthWizardForm(): HttpRequestBuilder =
    http("Enrolment redirection url")
      .post(authWizardUrl)
      .formParam("authorityId", "".el[String])
      .formParam("gatewayToken", "".el[String])
      .formParam("redirectionUrl", enrolmentAuthWizardUrl.el[String])
      .formParam("credentialStrength", "strong".el[String])
      .formParam("confidenceLevel", "50".el[String])
      .formParam("affinityGroup", "Individual".el[String])
      .formParam("email", "user@test.com".el[String])
      .formParam("credentialRole", "User".el[String])
      .formParam("additionalInfo.emailVerified", "N/A".el[String])
      .formParam("presets-dropdown", "IR-SA".el[String])
      .formParam("enrolment[0].name", "".el[String])
      .formParam("enrolment[0].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[0].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[0].state", "Activated".el[String])
      .formParam("enrolment[1].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[1].state", "Activated".el[String])
      .formParam("enrolment[2].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[2].state", "Activated".el[String])
      .formParam("enrolment[3].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[3].state", "Activated".el[String])
      .formParam("csrfToken", "#{csrfToken}".el[String])
      .check(status.is(303))
      .check(header("Location".el[String]).is(enrolmentAuthWizardUrl))

  val navigateToAccountAuthWizard: HttpRequestBuilder =
    http("Navigate to auth wizard account redirection url")
      .get(accountAuthWizardUrl)
      .check(status.is(303))

  def submitAccountAuthWizardForm(
    enrolmentKey: String,
    identifierName: String,
    identifierValue: String
  ): HttpRequestBuilder =
    http("Log in with account dashboard redirection url")
      .post(authWizardUrl)
      .formParam("authorityId", "".el[String])
      .formParam("gatewayToken", "".el[String])
      .formParam("redirectionUrl", accountAuthWizardUrl.el[String])
      .formParam("credentialStrength", "strong".el[String])
      .formParam("confidenceLevel", "50".el[String])
      .formParam("affinityGroup", "Individual".el[String])
      .formParam("email", "user@test.com".el[String])
      .formParam("credentialRole", "User".el[String])
      .formParam("oauthTokens.accessToken", "".el[String])
      .formParam("oauthTokens.refreshToken", "".el[String])
      .formParam("oauthTokens.idToken", "".el[String])
      .formParam("additionalInfo.emailVerified", "N/A".el[String])
      .formParam("presets-dropdown", "IR-SA".el[String])
      .formParam("enrolment[0].name", enrolmentKey.el[String])
      .formParam("enrolment[0].taxIdentifier[0].name", identifierName.el[String])
      .formParam("enrolment[0].taxIdentifier[0].value", identifierValue.el[String])
      .formParam("enrolment[0].state", "Activated".el[String])
      .formParam("enrolment[1].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[1].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[1].state", "Activated".el[String])
      .formParam("enrolment[2].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[2].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[2].state", "Activated".el[String])
      .formParam("enrolment[3].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].name", "".el[String])
      .formParam("enrolment[3].taxIdentifier[0].value", "".el[String])
      .formParam("enrolment[3].state", "Activated".el[String])
      .formParam("csrfToken", "#{csrfToken}".el[String])
      .check(status.is(303))
      .check(header("Location".el[String]).is(accountAuthWizardUrl))

}

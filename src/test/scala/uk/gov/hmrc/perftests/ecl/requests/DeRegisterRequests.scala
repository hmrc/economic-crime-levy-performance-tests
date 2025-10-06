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
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.ecl.Configuration
import uk.gov.hmrc.perftests.ecl.requests.AuthRequests._

object DeRegisterRequests extends Configuration {

  val requestToDeregisterUrl: String          = s"$registerAuthWizardUrl/deregister-start"
  val deregisterReasonUrl: String             = s"$registerAuthWizardUrl/deregister-reason"
  val dateNoLongerLiableForEclUrl: String     = s"$registerAuthWizardUrl/deregister-date-no-longer-liable"
  val deregisterContactNameUrl: String        = s"$registerAuthWizardUrl/deregister-contact-name"
  val deregisterContactRoleUrl: String        = s"$registerAuthWizardUrl/deregister-contact-role"
  val deregisterContactEmailUrl: String       = s"$registerAuthWizardUrl/deregister-contact-email-address"
  val deregisterContactTelephoneUrl: String   = s"$registerAuthWizardUrl/deregister-contact-telephone"
  val deregisterCheckYourAnswersUrl: String   = s"$registerAuthWizardUrl/deregister-check-your-answers"
  val deregisterSubmittedUrl: String          = s"$registerAuthWizardUrl/deregister-deregistration-requested"

  val navigateToSubmitRequestToDeregister: HttpRequestBuilder =
    http(s"Navigate to /deregister-start")
      .get(requestToDeregisterUrl)
      .check(status.is(200))

  val navigateToDeregisterReason: HttpRequestBuilder =
    http("Navigate to /deregister-reason")
      .get(deregisterReasonUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReasonForDeregister(reason: String): HttpRequestBuilder =
    http("Submit reason for Deregister: " + reason)
      .post(deregisterReasonUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", reason)
      .check(status.is(303))

  val navigateToDateNoLongerLiableForEcl: HttpRequestBuilder =
    http("Navigate to amend /deregister-date-no-longer-liable")
      .get(dateNoLongerLiableForEclUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDeregisterDateForNoLongerLiable(day: String, month: String, year: String): HttpRequestBuilder =
    http("Submit Deregister date: ")
      .post(dateNoLongerLiableForEclUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value.day", day)
      .formParam("value.month", month)
      .formParam("value.year", year)
      .check(status.is(303))

  val navigateToDeregisterContactName: HttpRequestBuilder =
    http("Navigate to /deregister-contact-name")
      .get(deregisterContactNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDeregisterContactName(contactName: String): HttpRequestBuilder =
    http("Submit deregister contact person's Name: " + contactName)
      .post(deregisterContactNameUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToDeregisterContactRole: HttpRequestBuilder =
    http("Navigate to /deregister-contact-role")
      .get(deregisterContactRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDeregisterContactRole(contactRole: String): HttpRequestBuilder =
    http("Submit deregister contact person's Role: " + contactRole)
      .post(deregisterContactRoleUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToDeregisterContactEmail: HttpRequestBuilder =
    http("Navigate to /deregister-contact-email-address")
      .get(deregisterContactEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDeregisterContactEmail(emailAddress: String): HttpRequestBuilder =
    http("Submit deregister contact person's Email address: " + emailAddress)
      .post(deregisterContactEmailUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", emailAddress)
      .check(status.is(303))

  val navigateToDeregisterContactTelephone: HttpRequestBuilder =
    http("Navigate to /deregister-contact-Telephone")
      .get(deregisterContactTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDeregisterContactTelephone(contactNumber: String): HttpRequestBuilder =
    http("Submit deregister contact person's Telephone: " + contactNumber)
      .post(deregisterContactTelephoneUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", contactNumber)
      .check(status.is(303))

  val navigateToDeregisterSubmitCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to /deregister-check-your-answers")
      .get(deregisterCheckYourAnswersUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDeregisterCheckYourAnswers(): HttpRequestBuilder =
    http("Submit check your answers")
      .post(deregisterCheckYourAnswersUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .check(status.is(303))

  val navigateToDeRegistrationRequestSubmitted: HttpRequestBuilder =
    http("Navigate to /deregister-registration-submitted")
      .get(deregisterSubmittedUrl)
      .check(status.is(200))
}

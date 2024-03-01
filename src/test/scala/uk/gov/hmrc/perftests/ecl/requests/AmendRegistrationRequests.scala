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

object AmendRegistrationRequests extends Configuration {

  val amendEclRegistrationUrl: String       = s"$registerAuthWizardUrl/amend-economic-crime-levy-registration/XMECL0000000002"
  val amendRegistrationSubmittedUrl: String =
    s"$registerAuthWizardUrl/amend-economic-crime-levy-registration/success/confirmation"
  val ReasonForAmendRegistrationUrl: String =
    s"$registerAuthWizardUrl/change-answer/why-are-you-amending-your-registration"

  val navigateToEclAccount: HttpRequestBuilder =
    http(s"Navigate to /amend-economic-crime-levy-registration/XMECL0000000002")
      .get(accountAuthWizardUrl)
      .check(status.is(200))

  val navigateToSubmitAmendRegistration: HttpRequestBuilder =
    http(s"Navigate to /amend-economic-crime-levy-registration/XMECL0000000002")
      .get(amendEclRegistrationUrl)
      .check(status.is(303))

  val navigateToReasonForAmendRegistration: HttpRequestBuilder =
    http("Navigate to Reason for Amend return start page")
      .get(ReasonForAmendRegistrationUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReasonForAmendRegistration(reason: String): HttpRequestBuilder =
    http("Submit reason for amend registration: " + reason)
      .post(ReasonForAmendRegistrationUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", reason)
      .check(status.is(303))

  val navigateToAmendSubmitCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to amend /check-your-answers")
      .get(RegistrationRequests.submitCheckYourAnswersUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendCheckYourAnswers(): HttpRequestBuilder =
    http("Amend Submit check your answers")
      .post(RegistrationRequests.submitCheckYourAnswersUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToAmendRegistrationSubmitted: HttpRequestBuilder =
    http("Navigate to /amend-registration-submitted")
      .get(amendRegistrationSubmittedUrl)
      .check(status.is(200))

}

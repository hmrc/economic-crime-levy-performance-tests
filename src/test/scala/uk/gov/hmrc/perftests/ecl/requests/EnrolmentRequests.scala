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
import uk.gov.hmrc.perftests.ecl.requests.AuthRequests.enrolmentAuthWizardUrl

object EnrolmentRequests extends Configuration {

  val yourEclReferenceNumberUrl: String  = s"$enrolmentUrl/add-economic-crime-levy/your-ecl-reference-number/"
  val yourEclRegistrationDateUrl: String = s"$enrolmentUrl/add-economic-crime-levy/ecl-registration-date/"

  val navigateToDoYouHaveAnEclReferenceNumber: HttpRequestBuilder =
    http("Navigate to /do-you-have-an-ecl-reference-number")
      .get(enrolmentAuthWizardUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDoYouHaveAnEclReferenceNumber(answer: String): HttpRequestBuilder =
    http("Do you have ECL reference number: " + answer)
      .post(enrolmentAuthWizardUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToYourEclReferenceNumber: HttpRequestBuilder =
    http("Navigate to /your-ecl-reference-number")
      .get(yourEclReferenceNumberUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYourEclReferenceNumber(eclReferenceNumber: String): HttpRequestBuilder =
    http("Your ECL reference number: " + eclReferenceNumber)
      .post(yourEclReferenceNumberUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value", eclReferenceNumber)
      .check(status.is(303))

  val navigateToEclRegistrationDate: HttpRequestBuilder =
    http("Navigate to /ecl-registration-date")
      .get(yourEclRegistrationDateUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYourEclRegistrationDate(
    eclRegistrationDate: String,
    eclRegistrationMonth: String,
    eclRegistrationYear: String
  ): HttpRequestBuilder =
    http("Your ECL registration date: " + eclRegistrationDate + "/" + eclRegistrationMonth + "/" + eclRegistrationYear)
      .post(yourEclRegistrationDateUrl)
      .formParam("csrfToken", "#{csrfToken}")
      .formParam("value.day", eclRegistrationDate)
      .formParam("value.month", eclRegistrationMonth)
      .formParam("value.year", eclRegistrationYear)
      .check(status.is(303))
}

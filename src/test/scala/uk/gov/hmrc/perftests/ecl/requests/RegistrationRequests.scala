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

object RegistrationRequests extends Configuration {

  val whatWasYourUkRevenueUrl: String         = s"$registerAuthWizardUrl/what-was-your-uk-revenue"
  val whoIsYourAmlSupervisorUrl: String       = s"$registerAuthWizardUrl/who-is-your-aml-supervisor"
  val whatIsYourEntityType: String            = s"$registerAuthWizardUrl/what-is-your-entity-type"
  val subGrsJourneyDataUrl: String            = s"$registerAuthWizardUrl/test-only/stub-grs-journey-data"
  val didYouStartAmlActivityUrl: String       = s"$registerAuthWizardUrl/did-you-start-aml-activity-in-current-year"
  val amlActivityStartDateUrl: String         = s"$registerAuthWizardUrl/aml-activity-start-date"
  val whatIsYourBusinessSectorUrl: String     = s"$registerAuthWizardUrl/what-is-your-business-sector"


  val navigateToSelectUkRevenue: HttpRequestBuilder =
    http("Navigate to /what-is-your-UK-revenue")
      .get(whatWasYourUkRevenueUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSelectUkRevenue(answer: String): HttpRequestBuilder =
    http("Select UK Revenue")
      .post(whatWasYourUkRevenueUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmlSupervisor : HttpRequestBuilder =
    http("Navigate to /who-is-your-aml-supervisor")
      .get(whoIsYourAmlSupervisorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmlSupervisor(amlSupervisor: String): HttpRequestBuilder =
    http("Submit AML Supervisor: " + amlSupervisor)
      .post(whoIsYourAmlSupervisorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", amlSupervisor)
      .formParam("otherProfessionalBody", "")
      .check(status.is(303))

  val navigateToEntityType: HttpRequestBuilder =
    http("Navigate to /what-is-your-entity-type")
      .get(whatIsYourEntityType)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitEntityType(entityType: String): HttpRequestBuilder =
    http("Submit Entity Type: " + entityType)
      .post(whatIsYourEntityType)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", entityType)
      .check(status.is(303))

  val navigateToStubGrsJourneyData: HttpRequestBuilder =
    http("Navigate to /test-only/stub-grs-journey-data")
      .get(subGrsJourneyDataUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitStubGrsJourneyData(journeyId: String, businessPartnerId: String): HttpRequestBuilder =
    http("Stub GRS Journey Data")
      .post(subGrsJourneyDataUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("journeyId", journeyId)
      .formParam("businessPartnerId", businessPartnerId)
      .check(status.is(303))

  val navigateToWhetherOrNotAmlActivityStartedInCurrentYear: HttpRequestBuilder =
    http("Navigate to /test-only/stub-grs-journey-data")
      .get(didYouStartAmlActivityUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhetherOrNotAmlActivityStartedInCurrentYear(answer: String): HttpRequestBuilder =
    http("Did you start AML activity in current FY" + answer)
      .post(didYouStartAmlActivityUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmlActivityStartDate: HttpRequestBuilder =
    http("Navigate to /test-only/stub-grs-journey-data")
      .get(amlActivityStartDateUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmlActivityStartDate(day: String, month: String, year: String): HttpRequestBuilder =
    http("AML activity start date " + day + "/" + month + "/" + year)
      .post(amlActivityStartDateUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value.day", day)
      .formParam("value.month", month)
      .formParam("value.year", year)
      .check(status.is(303))

  val navigateToWhatIsYourBusinessSector: HttpRequestBuilder =
    http("Navigate to /test-only/stub-grs-journey-data")
      .get(whatIsYourBusinessSectorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitWhatIsYourBusinessSector(businessSector: String): HttpRequestBuilder =
    http("Submit Business sector: " + businessSector)
      .post(whatIsYourBusinessSectorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", businessSector)
      .check(status.is(303))
}

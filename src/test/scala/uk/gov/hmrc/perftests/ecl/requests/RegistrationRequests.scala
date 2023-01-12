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

  val whatWasYourUkRevenueUrl: String   = s"$registerAuthWizardUrl/what-was-your-uk-revenue"
  val whoIsYourAmlSupervisorUrl: String = s"$registerAuthWizardUrl/who-is-your-aml-supervisor"
  val whatIsYourEntityType: String      = s"$registerAuthWizardUrl/what-is-your-entity-type"
  val subGrsJourneyDataUrl: String      = s"$registerAuthWizardUrl/test-only/stub-grs-journey-data"

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

  val navigateToSelectAmlSupervisor : HttpRequestBuilder =
    http("Navigate to /who-is-your-aml-supervisor")
      .get(whoIsYourAmlSupervisorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSelectAmlSupervisor(amlSupervisor: String): HttpRequestBuilder =
    http("Select AML Supervisor")
      .post(whoIsYourAmlSupervisorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", amlSupervisor)
      .formParam("otherProfessionalBody", "")
      .check(status.is(303))

  val navigateToSelectEntityType: HttpRequestBuilder =
    http("Navigate to /what-is-your-entity-type")
      .get(whatIsYourEntityType)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSelectEntityType(entityType: String): HttpRequestBuilder =
    http("Select Entity Type")
      .post(whatIsYourEntityType)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", entityType)
      .check(status.is(303))

  val navigateToStubGrsJourneyData: HttpRequestBuilder =
    http("Navigate to /test-only/stub-grs-journey-data")
      .get(subGrsJourneyDataUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def stubGrsJourneyData(journeyId: String, businessPartnerId: String): HttpRequestBuilder =
  http("Stub GRS Journey Data")
    .post(subGrsJourneyDataUrl)
    .formParam("csrfToken", "${csrfToken}")
    .formParam("value", journeyId)
    .formParam("value", businessPartnerId)
    .check(status.is(400))
}

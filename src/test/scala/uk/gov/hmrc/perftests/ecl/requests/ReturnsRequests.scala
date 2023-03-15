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


object ReturnsRequests extends Configuration{

  val isRelevantAccountingPeriod12MonthsUrl: String = s"$returnAuthWizardUrl/is-relevant-accounting-period-12-months"
  val ukRevenueInAccountingPeriodUrl: String = s"$returnAuthWizardUrl/uk-revenue-in-accounting-period"
  val amlRegulatedActivityUrl: String = s"$returnAuthWizardUrl/uk-revenue-in-accounting-period"

  val navigateToIsRelevantAccountingPeriod12MonthsUrl: HttpRequestBuilder =
    http("Navigate to Is relevant Accounting period 12 months")
      .get(isRelevantAccountingPeriod12MonthsUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitIsYourRelevantAccountingPeriod12Months(answer: String): HttpRequestBuilder =
    http("Is UK Revenue for the relevant accounting period 12 months" + answer)
      .post(isRelevantAccountingPeriod12MonthsUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToUkRevenueAccountingPeriod: HttpRequestBuilder =
    http("Navigate to UK Revenue period")
      .get(ukRevenueInAccountingPeriodUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitUkRevenueForTheRelevantAccountingPeriod(ukRevenue: String): HttpRequestBuilder =
    http("Is UK Revenue for the relevant accounting period " + ukRevenue)
      .post(ukRevenueInAccountingPeriodUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", ukRevenue)
      .check(status.is(303))

  val navigateToAmlRegulatedActivity: HttpRequestBuilder =
    http("Navigate to AML regulated activity")
      .get(amlRegulatedActivityUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

}

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

object ReturnsRequests extends Configuration {

  val expectedTaxYearStart = EclTaxYear.currentFyStartYear.takeRight(2)
  val taxYear = s"$expectedTaxYearStart XY".replaceAll("\\s", "")

  val startUrl: String                              = s"$returnAuthWizardUrl/period/$taxYear"
  val isRelevantAccountingPeriod12MonthsUrl: String = s"$returnAuthWizardUrl/is-relevant-accounting-period-12-months"
  val ukRevenueInAccountingPeriodUrl: String        = s"$returnAuthWizardUrl/uk-revenue-in-accounting-period"
  val amlRegulatedActivityUrl: String               = s"$returnAuthWizardUrl/aml-regulated-activity"
  val amountDueUrl: String                          = s"$returnAuthWizardUrl/amount-due"
  val contactNameUrl: String                        = s"$returnAuthWizardUrl/contact-name"
  val contactRoleUrl: String                        = s"$returnAuthWizardUrl/contact-role"
  val contactEmailUrl: String                       = s"$returnAuthWizardUrl/contact-email-address"
  val contactTelephoneUrl: String                   = s"$returnAuthWizardUrl/contact-telephone"
  val checkYourAnswersUrl: String                   = s"$returnAuthWizardUrl/check-your-answers"

  val navigateToStartReturn: HttpRequestBuilder =
    http("Navigate to Return start page")
      .get(startUrl)
      .check(status.is(200))

  val navigateToIsRelevantAccountingPeriod12MonthsUrl: HttpRequestBuilder =
    http("Navigate to Is relevant Accounting period 12 months")
      .get(isRelevantAccountingPeriod12MonthsUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitIsYourRelevantAccountingPeriod12Months(answer: String): HttpRequestBuilder =
    http("Is UK Revenue for the relevant accounting period 12 months: " + answer)
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
    http("Is UK Revenue for the relevant accounting period: " + ukRevenue)
      .post(ukRevenueInAccountingPeriodUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", ukRevenue)
      .check(status.is(303))

  val navigateToAmlRegulatedActivity: HttpRequestBuilder             =
    http("Navigate to AML regulated activity")
      .get(amlRegulatedActivityUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitAmlRegulatedActivity(answer: String): HttpRequestBuilder =
    http("Did you carry out AML-regulated activity for the full financial year?: " + answer)
      .post(amlRegulatedActivityUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmountDue: HttpRequestBuilder =
    http("Navigate to AML Amount Due")
      .get(amountDueUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmountDue(): HttpRequestBuilder =
    http("Amount Due ")
      .post(amountDueUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToContactName: HttpRequestBuilder =
    http("Navigate to Contact Name")
      .get(contactNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContactName(contactName: String): HttpRequestBuilder =
    http("Contact Name: " + contactName)
      .post(contactNameUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToContactRole: HttpRequestBuilder =
    http("Navigate to Contact Role")
      .get(contactRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContactRole(contactRole: String): HttpRequestBuilder =
    http("Contact Role: " + contactRole)
      .post(contactRoleUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToContactEmailAddress: HttpRequestBuilder =
    http("Navigate to Contact Email")
      .get(contactEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContactEmailAddress(contactEmail: String): HttpRequestBuilder =
    http("Contact Email: " + contactEmail)
      .post(contactEmailUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactEmail)
      .check(status.is(303))

  val navigateToContactTelephone: HttpRequestBuilder =
    http("Navigate to Contact Telephone")
      .get(contactTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContactTelephone(contactTelephone: String): HttpRequestBuilder =
    http("Contact Number: " + contactTelephone)
      .post(contactTelephoneUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactTelephone)
      .check(status.is(303))

  val navigateToCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to Check your answers")
      .get(checkYourAnswersUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitCheckYourAnswersForReturns(): HttpRequestBuilder =
    http("Check Your Answers")
      .post(checkYourAnswersUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

}

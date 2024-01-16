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

object AmendReturnsRequests extends Configuration {

  val startAmendReturnUrl: String          = s"$returnAuthWizardUrl/amend/22XY/XMECL0000000006"
  val amendReturnSubmittedUrl: String      = s"$returnAuthWizardUrl/amend/confirmation"
  val ReasonForAmendReturnUrl: String      = s"$returnAuthWizardUrl/can-you-provide-more-detail"

  val navigateToAmendStartReturn: HttpRequestBuilder =
    http("Navigate to Amend return start page")
      .get(startAmendReturnUrl)
      .check(status.is(200))

  val navigateToReasonForAmendReturn: HttpRequestBuilder =
    http("Navigate to Reason for Amend return start page")
      .get(ReasonForAmendReturnUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReasonForAmendReturns(reason: String): HttpRequestBuilder =
    http("Submit reason for amend registration: " + reason)
      .post(ReasonForAmendReturnUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", reason)
      .check(status.is(303))

  val navigateToAmendIsRelevantAccountingPeriod12MonthsUrl: HttpRequestBuilder =
    http("Navigate to amend /is-relevant-accounting-period-12-months")
      .get(ReturnsRequests.isRelevantAccountingPeriod12MonthsUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendIsYourRelevantAccountingPeriod12Months(answer: String): HttpRequestBuilder =
    http("Amend is UK Revenue for the relevant accounting period 12 months: " + answer)
      .post(ReturnsRequests.isRelevantAccountingPeriod12MonthsUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmendUkRevenueAccountingPeriod: HttpRequestBuilder =
    http("Navigate to amend /uk-revenue-in-accounting-period")
      .get(ReturnsRequests.ukRevenueInAccountingPeriodUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendUkRevenueForTheRelevantAccountingPeriod(ukRevenue: String): HttpRequestBuilder =
    http("Amend Is UK Revenue for the relevant accounting period: " + ukRevenue)
      .post(ReturnsRequests.ukRevenueInAccountingPeriodUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", ukRevenue)
      .check(status.is(303))

  val navigateToAmendAmlRegulatedActivity: HttpRequestBuilder             =
    http("Navigate to amend /aml-regulated-activity")
      .get(ReturnsRequests.amlRegulatedActivityUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitAmendAmlRegulatedActivity(answer: String): HttpRequestBuilder =
    http("Amend Did you carry out AML-regulated activity for the full financial year?: " + answer)
      .post(ReturnsRequests.amlRegulatedActivityUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmendAmountDue: HttpRequestBuilder =
    http("Navigate to amend /amount-due")
      .get(ReturnsRequests.amountDueUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendAmountDue(): HttpRequestBuilder =
    http("Amend Amount Due ")
      .post(ReturnsRequests.amountDueUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToAmendContactName: HttpRequestBuilder =
    http("Navigate to amend /contact-name")
      .get(ReturnsRequests.contactNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendContactName(contactName: String): HttpRequestBuilder =
    http("Contact Name: " + contactName)
      .post(ReturnsRequests.contactNameUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToAmendContactRole: HttpRequestBuilder =
    http("Navigate to amend /contact-role")
      .get(ReturnsRequests.contactRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendContactRole(contactRole: String): HttpRequestBuilder =
    http("Contact Role: " + contactRole)
      .post(ReturnsRequests.contactRoleUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToAmendContactEmailAddress: HttpRequestBuilder =
    http("Navigate to amend /contact-email-address")
      .get(ReturnsRequests.contactEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendContactEmailAddress(contactEmail: String): HttpRequestBuilder =
    http("Contact Email: " + contactEmail)
      .post(ReturnsRequests.contactEmailUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactEmail)
      .check(status.is(303))

  val navigateToAmendContactTelephone: HttpRequestBuilder =
    http("Navigate to amend /contact-telephone")
      .get(ReturnsRequests.contactTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendContactTelephone(contactTelephone: String): HttpRequestBuilder =
    http("Contact Number: " + contactTelephone)
      .post(ReturnsRequests.contactTelephoneUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactTelephone)
      .check(status.is(303))

  val navigateToAmendCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to amend /check-your-answers")
      .get(ReturnsRequests.checkYourAnswersUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendCheckYourAnswersForReturns(): HttpRequestBuilder =
    http("Amend Check Your Answers")
      .post(ReturnsRequests.checkYourAnswersUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToAmendReturnSubmitted: HttpRequestBuilder =
    http("Navigate to /amend-return-submitted")
      .get(amendReturnSubmittedUrl)
      .check(status.is(200))

}

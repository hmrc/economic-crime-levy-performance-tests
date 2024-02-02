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
  val ReasonForAmendReturnUrl: String      = s"$returnAuthWizardUrl/change-answer/can-you-provide-more-detail"

  val navigateToAmendStartReturn: HttpRequestBuilder =
    http("Navigate to Amend return start page")
      .get(startAmendReturnUrl)
      .check(status.is(303))

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

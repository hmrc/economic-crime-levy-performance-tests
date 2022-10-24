/*
 * Copyright 2022 HM Revenue & Customs
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


object AuthRequests extends Configuration{

  val registerAuthWizardUrl: String        = s"$authUrl/auth-login-stub//gg-sign-in?continue=http%3A%2F%2Flocalhost%3A14000%2Fregister-for-economic-crime-levy%2F"
  val returnsAuthWizardUrl: String         = s"$authUrl/auth-login-stub//gg-sign-in?continue=http%3A%2F%2Flocalhost%3A14002%2Fsubmit-economic-crime-levy-return%2F"

  val registerNavigateToAuthWizard: HttpRequestBuilder =
    http("Navigate to Register AuthWizard Page")
      .get(registerAuthWizardUrl)
      .check(status.is(200))

  val returnsNavigateToAuthWizard: HttpRequestBuilder =
    http("Navigate to Returns AuthWizard Page")
      .get(returnsAuthWizardUrl)
      .check(status.is(200))
}

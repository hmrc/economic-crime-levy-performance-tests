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

  def redirectLocation(relativeLocation: String): String =
    s"$registrationUrl/$relativeLocation"

  val eclReferenceNumber                         = AuthRequests.identifierValue
  val amendEclRegistrationUrl: String            = s"$registerAuthWizardUrl/amend-economic-crime-levy-registration/$identifierValue"
  val whoIsYourAmendAmlSupervisorUrl: String     = s"$registerAuthWizardUrl/your-aml-supervisor/Amendment?fromLiableBeforeCurrentYearPage=false"
  val contactUkAddressUrl: String                = s"$registerAuthWizardUrl/uk-address"
  val stubAlfJourneyDataUrl: String =
    s"$registerAuthWizardUrl/test-only/stub-alf-journey-data?continueUrl=normalmode"
  val amendRegistrationSubmittedUrl: String      = s"$registerAuthWizardUrl/amend-economic-crime-levy-registration/success/confirmation"


  val navigateToEclAccount: HttpRequestBuilder =
    http(s"Navigate to /amend-economic-crime-levy-registration/$identifierValue")
      .get(accountAuthWizardUrl)
      .check(status.is(200))

  val navigateToSubmitAmendRegistration: HttpRequestBuilder =
    http(s"Navigate to /amend-economic-crime-levy-registration/$identifierValue")
      .get(amendEclRegistrationUrl)
      .check(status.is(200))

  val navigateToAmendAmlSupervisor: HttpRequestBuilder =
    http("Navigate to /who-is-your-aml-supervisor/Amendment")
      .get(whoIsYourAmendAmlSupervisorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendAmlSupervisor(amlSupervisor: String): HttpRequestBuilder =
    http("Submit amend AML Supervisor: " + amlSupervisor)
      .post(whoIsYourAmendAmlSupervisorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", amlSupervisor)
      .formParam("otherProfessionalBody", "")
      .check(status.is(303))

  val navigateToYourAmendBusinessSector: HttpRequestBuilder =
    http("Navigate to amend /what-is-your-business-sector")
      .get(RegistrationRequests.whatIsYourBusinessSectorUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYourAmendBusinessSector(businessSector: String): HttpRequestBuilder =
    http("Submit amend Business sector: " + businessSector)
      .post(RegistrationRequests.whatIsYourBusinessSectorUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", businessSector)
      .check(status.is(303))

  val navigateToAmendFirstContactPersonName: HttpRequestBuilder =
    http("Navigate to amend /contact-name")
      .get(RegistrationRequests.firstContactPersonNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendFirstContactPersonName(contactName: String): HttpRequestBuilder =
    http("Submit contact person's Name: " + contactName)
      .post(RegistrationRequests.firstContactPersonNameUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToAmendFirstContactPersonRole: HttpRequestBuilder =
    http("Navigate to amend /contact-role")
      .get(RegistrationRequests.firstContactPersonRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendFirstContactPersonRole(contactRole: String): HttpRequestBuilder =
    http("Submit contact person's Role: " + contactRole)
      .post(RegistrationRequests.firstContactPersonRoleUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToAmendFirstContactPersonEmail: HttpRequestBuilder =
    http("Navigate to amend /contact-email-address")
      .get(RegistrationRequests.firstContactPersonEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendFirstContactPersonEmail(emailAddress: String): HttpRequestBuilder =
    http("Submit contact person's Email address: " + emailAddress)
      .post(RegistrationRequests.firstContactPersonEmailUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", emailAddress)
      .check(status.is(303))

  val navigateToAmendFirstContactPersonTelephone: HttpRequestBuilder =
    http("Navigate to amend /contact-Telephone")
      .get(RegistrationRequests.firstContactPersonTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendFirstContactPersonTelephone(contactNumber: String): HttpRequestBuilder =
    http("Submit contact person's Telephone: " + contactNumber)
      .post(RegistrationRequests.firstContactPersonTelephoneUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactNumber)
      .check(status.is(303))

  val navigateToAmendWouldYouLikeToAddAnotherContact: HttpRequestBuilder             =
    http("Navigate to amend /second-contact")
      .get(RegistrationRequests.wouldYouLikeToAddAnotherContactUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendWouldYouLikeToAddAnotherContact(answer: String): HttpRequestBuilder =
    http("Submit add another contact: " + answer)
      .post(RegistrationRequests.wouldYouLikeToAddAnotherContactUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToAmendSecondContactPersonName: HttpRequestBuilder =
    http("Navigate to amend /second-contact-name")
      .get(RegistrationRequests.secondContactPersonNameUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendSecondContactPersonName(contactName: String): HttpRequestBuilder =
    http("Submit second contact person's Name: " + contactName)
      .post(RegistrationRequests.secondContactPersonNameUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactName)
      .check(status.is(303))

  val navigateToAmendSecondContactPersonRole: HttpRequestBuilder =
    http("Navigate to amend /second-contact-role")
      .get(RegistrationRequests.secondContactPersonRoleUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendSecondContactPersonRole(contactRole: String): HttpRequestBuilder =
    http("Submit second contact person's Role: " + contactRole)
      .post(RegistrationRequests.secondContactPersonRoleUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactRole)
      .check(status.is(303))

  val navigateToAmendSecondContactPersonEmail: HttpRequestBuilder =
    http("Navigate to amend /second-contact-email-address")
      .get(RegistrationRequests.secondContactPersonEmailUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendSecondContactPersonEmail(emailAddress: String): HttpRequestBuilder =
    http("Submit second contact person's Email address: " + emailAddress)
      .post(RegistrationRequests.secondContactPersonEmailUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", emailAddress)
      .check(status.is(303))

  val navigateToAmendSecondContactPersonTelephone: HttpRequestBuilder =
    http("Navigate to amend /second-contact-Telephone")
      .get(RegistrationRequests.secondContactPersonTelephoneUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendSecondContactPersonTelephone(contactNumber: String): HttpRequestBuilder =
    http("Submit second contact person's Telephone: " + contactNumber)
      .post(RegistrationRequests.secondContactPersonTelephoneUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", contactNumber)
      .check(status.is(303))

  val navigateToWhetherOrNotContactAddressInUk: HttpRequestBuilder =
    http("Navigate to amend /contact-address")
      .get(contactUkAddressUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhetherOrNotContactAddressInUk(answer: String): HttpRequestBuilder =
    http("Submit registered contact address: " + answer)
      .post(contactUkAddressUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", answer)
      .check(status.is(303))

  val navigateToStubAlfJourneyData: HttpRequestBuilder =
    http("Navigate to /test-only/stub-alf-journey-data")
      .get(stubAlfJourneyDataUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitStubAlfJourneyData(): HttpRequestBuilder =
    http("Stub ALF Journey Data")
      .post(stubAlfJourneyDataUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("addressJson",
        """{
           "address" : {
           "lines" : [ "Address Line 1", "Address Line 2", "Address Line 3", "Test Town" ],
           "postcode" : "AB12 3CD",
           "country" : {
           "code" : "GB",
           "name" : "United Kingdom"
        }
      }
    }"""
      )
      .check(status.is(303))
      .check(header("Location").saveAs("alfContinueLocation"))

  val navigateToAlfContinue: HttpRequestBuilder =
    http("Navigate to /alf-continue/normalmode")
      .get(redirectLocation("${alfContinueLocation}"))
      .check(status.is(303))

    val navigateToAmendSubmitCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to amend /check-your-answers")
      .get(RegistrationRequests.submitCheckYourAnswersUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmendCheckYourAnswers(): HttpRequestBuilder =
    http("Submit amend check your answers")
      .post(RegistrationRequests.submitCheckYourAnswersUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToAmendRegistrationSubmitted: HttpRequestBuilder =
    http("Navigate to /amend-registration-submitted")
      .get(amendRegistrationSubmittedUrl)
      .check(status.is(200))

}
